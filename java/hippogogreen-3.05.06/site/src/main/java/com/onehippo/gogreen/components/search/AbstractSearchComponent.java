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

package com.onehippo.gogreen.components.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoAsset;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.content.beans.standard.HippoDocumentIterator;
import org.hippoecm.hst.content.beans.standard.HippoFacetChildNavigationBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetNavigationBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.util.SearchInputParsingUtils;
import org.hippoecm.hst.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.Document;
import com.onehippo.gogreen.beans.EventDocument;
import com.onehippo.gogreen.beans.Faq;
import com.onehippo.gogreen.beans.JobsDocument;
import com.onehippo.gogreen.beans.NewsItem;
import com.onehippo.gogreen.beans.Product;
import com.onehippo.gogreen.beans.SimpleDocument;
import com.onehippo.gogreen.components.ComponentUtil;
import com.onehippo.gogreen.components.TagComponent;
import com.onehippo.gogreen.utils.Constants;
import com.onehippo.gogreen.utils.PageableCollection;

public class AbstractSearchComponent extends TagComponent {

    private static final String PARAM_QUERY = "query";

    private static final Logger log = LoggerFactory.getLogger(AbstractSearchComponent.class);

    /**
     * 
     * @param request
     * @return the not yet parsed request parameter 'query' and <code>null</code> if there was no such parameter
     */
    protected String getQuery(HstRequest request) {
        return getPublicRequestParameter(request, PARAM_QUERY);
    }

    protected int getPageSize(HstRequest request) {
        String pageSizeParam = getParameter(Constants.PAGE_SIZE, request);
        return ComponentUtil.parseIntParameter(Constants.PAGE_SIZE, pageSizeParam, Constants.DEFAULT_PAGE_SIZE, log);
    }

    protected int getCurrentPage(HstRequest request) {
        String currentPageParam = getPublicRequestParameter(request, Constants.PAGE);
        return ComponentUtil.parseIntParameter(Constants.PAGE, currentPageParam, Constants.DEFAULT_PAGE_NUMBER, log);
    }

    protected boolean showTaggedDocuments(HstRequest request) {
        List<? extends HippoBean> taggedBeans = getRelatedBeans(request);

        if (taggedBeans.isEmpty()) {
            // no tagged documents available
            return false;
        } else {
            // only show tagged documents

            // we only want subtypes of hippogogreen:document.
            // TODO: replace this by limiting the search tag sources to 
            // hippogogreen:documents once the tagcloud plugin supports this.
            List<HippoBean> taggedDocuments = new ArrayList<HippoBean>();
            for (HippoBean bean : taggedBeans) {
                if (bean instanceof Document) {
                    taggedDocuments.add(bean);
                }
            }

            int pageSize = getPageSize(request);
            int currentPage = getCurrentPage(request);

            @SuppressWarnings("unchecked")
            PageableCollection tagged = new PageableCollection(taggedDocuments, pageSize, currentPage);
            request.setAttribute("searchResult", tagged);

            return true;
        }
    }

    protected void searchDocuments(final HstRequest request,final String query) {
        
        String parsedQuery = SearchInputParsingUtils.parse(query,false);
        
        request.setAttribute("query", StringEscapeUtils.escapeHtml(parsedQuery));
        HippoBean scope = getSiteContentBaseBean(request);
        if (scope == null) {
            log.error("Scope for search is null");
            return;
        }

        try {
            HstQueryManager manager = getQueryManager(request);

            @SuppressWarnings("unchecked")
            HstQuery hstQuery = manager.createQuery(scope, EventDocument.class, JobsDocument.class, NewsItem.class,
                    Product.class, HippoAsset.class, SimpleDocument.class, Faq.class);

            HippoBean assetScope = getAssetBaseBean(request);
            hstQuery.addScopes(Collections.singletonList(assetScope));

            if (!StringUtils.isEmpty(parsedQuery)) {
                Filter filter = hstQuery.createFilter();
                hstQuery.setFilter(filter);
                // title field
                Filter titleFilter = hstQuery.createFilter();
                titleFilter.addContains("@hippogogreen:title", parsedQuery);
                // summary field
                Filter summaryFilter = hstQuery.createFilter();
                summaryFilter.addContains("@hippogogreen:summary", parsedQuery);
                // full text
                Filter fullTextFilter = hstQuery.createFilter();
                fullTextFilter.addContains(".", parsedQuery);

                filter.addOrFilter(titleFilter);
                filter.addOrFilter(summaryFilter);
                filter.addOrFilter(fullTextFilter);
                //https://issues.onehippo.com/browse/GOGREEN-254
                hstQuery.addOrderByDescending("hippogogreen:rating");
            }else{
                hstQuery.addOrderByDescending("hippogogreen:title");
            }
            HstQueryResult result = hstQuery.execute();

            HippoBeanIterator beans = result.getHippoBeans();
            int pageSize = getPageSize(request);
            int currentPage = getCurrentPage(request);

            PageableCollection<HippoBean> results = new PageableCollection<HippoBean>(beans, pageSize, currentPage);
            request.setAttribute("searchResult", results);
        } catch (QueryException e) {
            if(log.isDebugEnabled()) {
                log.warn("Error during search: ", e);
            } else {
                log.warn("Error during search: ", e.getMessage());
            }
        }
    }

    protected boolean showFacetedDocuments(HstRequest request) {
        HippoBean bean = getContentBean(request);
        if (bean instanceof HippoFacetChildNavigationBean) {
            String query = SearchInputParsingUtils.parse(getQuery(request), false);
            HippoFacetNavigationBean facetBean = BeanUtils.getFacetNavigationBean(request, query, objectConverter);
            HippoDocumentIterator<HippoBean> facetIt = facetBean.getResultSet().getDocumentIterator(HippoBean.class);
            int facetCount = facetBean.getCount().intValue();
            int pageSize = getPageSize(request);
            int currentPage = getCurrentPage(request);
            PageableCollection<HippoBean> results = new PageableCollection<HippoBean>(facetIt, facetCount,
                    pageSize, currentPage);
            request.setAttribute("searchResult", results);
            request.setAttribute("query", StringEscapeUtils.escapeHtml(query));
            return true;
        } else {
            return false;
        }
    }

}
