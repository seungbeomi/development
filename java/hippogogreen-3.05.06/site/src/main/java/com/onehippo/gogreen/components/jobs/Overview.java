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

package com.onehippo.gogreen.components.jobs;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetChildNavigationBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetNavigationBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.ResolvedSiteMapItem;
import org.hippoecm.hst.util.SearchInputParsingUtils;
import org.hippoecm.hst.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.JobsDocument;
import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.components.ComponentUtil;
import com.onehippo.gogreen.utils.Constants;
import com.onehippo.gogreen.utils.GoGreenUtil;
import com.onehippo.gogreen.utils.PageableCollection;

public class Overview extends BaseComponent {


    private static final Logger log = LoggerFactory.getLogger(Overview.class);
    
    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        String currentPageParam = getPublicRequestParameter(request, Constants.PAGE);
        int pageNumber = ComponentUtil.parseIntParameter(Constants.PAGE, currentPageParam, Constants.DEFAULT_PAGE_NUMBER, log);

        String query = this.getPublicRequestParameter(request, "query");
        query = SearchInputParsingUtils.parse(query, false);
        request.setAttribute("query", StringEscapeUtils.escapeHtml(query));

        HippoBean currentBean = getContentBean(request);
        if (currentBean == null) {
            ResolvedSiteMapItem resolvedSiteMapItem = request.getRequestContext().getResolvedSiteMapItem();
            log.warn("Content bean not found; please check the relative content path for sitemap item: {}. Relative content path: {}.", 
                    resolvedSiteMapItem.getHstSiteMapItem().getId(),
                    resolvedSiteMapItem.getRelativeContentPath());
            return;
        }

        if (!(currentBean instanceof HippoFacetChildNavigationBean)) {
            try {
                @SuppressWarnings("unchecked")
                HstQuery hstQuery = this.getQueryManager(request).createQuery(currentBean, JobsDocument.class);
                if(!StringUtils.isEmpty(query)) {
                    Filter f = hstQuery.createFilter();
                    Filter f1 = hstQuery.createFilter();
                    f1.addContains(".", query);
                    Filter f2 = hstQuery.createFilter();
                    f2.addContains("hippogogreen:title", query);
                    f.addOrFilter(f1);
                    f.addOrFilter(f2);
                    hstQuery.setFilter(f);
                }

                HstQueryResult result = hstQuery.execute();
                request.setAttribute("docs", new PageableCollection<HippoBean>(
                        result.getHippoBeans(), GoGreenUtil.getIntConfigurationParameter(request,
                                Constants.PAGE_SIZE, Constants.DEFAULT_PAGE_SIZE), pageNumber));
                request.setAttribute("count", result.getSize());
            } catch (QueryException e) {
                throw new HstComponentException("Query exception while searching jobs: " + e.getMessage(), e);
            }
        } else {
            HippoFacetNavigationBean facetBean = BeanUtils.getFacetNavigationBean(request, query, objectConverter);
            if (facetBean != null) {
                request.setAttribute("docs", new PageableCollection(facetBean.getResultSet().getDocumentIterator(JobsDocument.class), facetBean.getCount().intValue(), GoGreenUtil.getIntConfigurationParameter(request,
                    Constants.PAGE_SIZE, Constants.DEFAULT_PAGE_SIZE), pageNumber));
                request.setAttribute("count", facetBean.getCount());
            } else {
                request.setAttribute("count", 0);
            }
        }
    }

}
