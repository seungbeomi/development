/**
 * Copyright (C) 2010 Hippo B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onehippo.gogreen.components.news;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.BaseFilter;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.query.filter.PrimaryNodeTypeFilterImpl;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentIterator;
import org.hippoecm.hst.content.beans.standard.HippoFacetChildNavigationBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetNavigationBean;
import org.hippoecm.hst.content.beans.standard.HippoFolder;
import org.hippoecm.hst.content.beans.standard.HippoResultSetBean;
import org.hippoecm.hst.content.beans.standard.facetnavigation.HippoFacetNavigation;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.ResolvedSiteMapItem;
import org.hippoecm.hst.util.ContentBeanUtils;
import org.hippoecm.hst.util.SearchInputParsingUtils;
import org.hippoecm.hst.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.Comment;
import com.onehippo.gogreen.beans.NewsItem;
import com.onehippo.gogreen.components.ComponentUtil;
import com.onehippo.gogreen.components.TagComponent;
import com.onehippo.gogreen.utils.Constants;
import com.onehippo.gogreen.utils.GoGreenUtil;
import com.onehippo.gogreen.utils.PageableCollection;

/**
 * <p>
 * Fetches matching news items within the scope of the current content bean, ordered descending by date. The news items
 * are put into a {@link PageableCollection} that is available on the request as attribute 'news'.
 * </p>
 * <p>
 * The following news items are fetched:
 * </p>
 * <ol>
 * <li>If a tag is selected, the news items related to that tag.</li>
 * <li>If one or more facets are selected, all news items matching these facets. The facets are combined
 * with the free text search in the public request parameter 'query'.</li>
 * <li>Otherwise, all available news items.</li>
 * </ol>
 * <p>
 * For each news items on the current page, the number of comments is determined. These counts are put in a list
 * in the same order as the news items, and put on the request as attribute 'commentsCountList'.
 * </p>
 * <em>Component parameters:</em>
 * <ul>
 * <li>pageSize: the number of news items per page</li>
 * <li>
 * <em>Public request parameters:</em>
 * <ul>
 * <li>pageNumber: the page to show</li>
  *<li>query: the free text to combine with the facets to limit the fetched news items.</li>
 * </ul>
 */
public class NewsOverview extends TagComponent {

    public static final Logger log = LoggerFactory.getLogger(NewsOverview.class);

    private static final int DEFAULT_PAGE_SIZE = 5;
    private static final String PARAM_CURRENT_PAGE = "pageNumber";
    private static final int DEFAULT_CURRENT_PAGE = 1;

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        final HippoBean scope = getContentBean(request);
        if (scope == null) {
            ResolvedSiteMapItem resolvedSiteMapItem = request.getRequestContext().getResolvedSiteMapItem();
            log.warn("Scope bean not found; please check the relative content path for sitemap item: {}. Relative content path: {}.", 
                    resolvedSiteMapItem.getHstSiteMapItem().getId(),
                    resolvedSiteMapItem.getRelativeContentPath());
            return;
        }

        int pageSize = GoGreenUtil.getIntConfigurationParameter(request, Constants.PAGE_SIZE, DEFAULT_PAGE_SIZE);

        String currentPageParam = getPublicRequestParameter(request, PARAM_CURRENT_PAGE);
        int currentPage = ComponentUtil.parseIntParameter(PARAM_CURRENT_PAGE, currentPageParam, DEFAULT_CURRENT_PAGE, log);

        String query = this.getPublicRequestParameter(request, "query");
        query = SearchInputParsingUtils.parse(query, false);
        request.setAttribute("query", StringEscapeUtils.escapeHtml(query));

        try {
            final PageableCollection news = getNews(request, scope, pageSize, currentPage, query);
            request.setAttribute("news", news);

            updateCommentsCount(request, news);
        } catch (QueryException e) {
            throw new HstComponentException("Query error while getting news: " + e.getMessage(), e);
        }
    }

    private PageableCollection getNews(HstRequest request, HippoBean scope, int pageSize, int currentPage, String query) throws QueryException {
        List<? extends HippoBean> relatedBeans = getRelatedBeans(request);

        if (!relatedBeans.isEmpty()) {
            // only show tagged news items
            return new PageableCollection((List<HippoBean>) relatedBeans, pageSize, currentPage);
        }

        final HstQuery hstQuery = getQueryManager(request).createQuery(scope);
        final BaseFilter filter = new PrimaryNodeTypeFilterImpl("hippogogreen:newsitem");
        hstQuery.setFilter(filter);
        hstQuery.addOrderByDescending("hippogogreen:date");

        if (!StringUtils.isEmpty(query)) {
            final Filter f = hstQuery.createFilter();
            final Filter f1 = hstQuery.createFilter();
            f1.addContains(".", query);
            final Filter f2 = hstQuery.createFilter();
            f2.addContains("hippogogreen:title", query);
            f.addOrFilter(f2);
            hstQuery.setFilter(f);
        }

        if (scope instanceof HippoFacetChildNavigationBean || scope instanceof HippoFacetNavigation) {
            // only show faceted news items
            final HippoFacetNavigationBean facetBean = BeanUtils.getFacetNavigationBean(request, hstQuery, objectConverter);

            if (facetBean == null) {
                final List<HippoBean> noResults = Collections.emptyList();
                return new PageableCollection(0, noResults);
            } else {
                final HippoResultSetBean resultSet = facetBean.getResultSet();
                final HippoDocumentIterator<NewsItem> facetIt = resultSet.getDocumentIterator(NewsItem.class);
                if (hstQuery.getOffset() > 0) {
                    facetIt.skip(hstQuery.getOffset());
                }
                final int facetCount = facetBean.getCount().intValue();
                return new PageableCollection(facetIt, facetCount, pageSize, currentPage);
            }
        }

        // show all news items
        final HstQueryResult result = hstQuery.execute();
        final HippoBeanIterator iterator = result.getHippoBeans();
        return new PageableCollection<NewsItem>(iterator, pageSize, currentPage);
    }

    private void updateCommentsCount(HstRequest request, PageableCollection news) throws QueryException {
        List<Integer> commentCount = new ArrayList<Integer>();
        
        HippoBean siteContentBase = getSiteContentBaseBean(request);
        
        if (siteContentBase == null) {
            log.warn("Site content base bean is not found: {}", getSiteContentBasePath(request));
            return;
        }
        
        HippoFolder newsCommentFolder = siteContentBase.getBean("comments/news");
        
        if (newsCommentFolder == null) {
            log.warn("News comment folder is not found: {}/comments/news. So it fails to update comments count", siteContentBase.getPath());
            return;
        }
        
        for (Object newsItem : news.getItems()) {
            final HstQuery incomingBeansQuery = ContentBeanUtils.createIncomingBeansQuery((HippoDocumentBean) newsItem, newsCommentFolder, 4, getObjectConverter(), Comment.class, false);
            commentCount.add(incomingBeansQuery.execute().getSize());

        }
        request.setAttribute("commentsCountList", commentCount);

    }


}