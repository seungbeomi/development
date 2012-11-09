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

package com.onehippo.gogreen.components.products;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentIterator;
import org.hippoecm.hst.content.beans.standard.HippoFacetChildNavigationBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetNavigationBean;
import org.hippoecm.hst.content.beans.standard.HippoResultSetBean;
import org.hippoecm.hst.content.beans.standard.facetnavigation.HippoFacetNavigation;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.hippoecm.hst.util.SearchInputParsingUtils;
import org.hippoecm.hst.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.Product;
import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.components.ComponentUtil;
import com.onehippo.gogreen.utils.Constants;
import com.onehippo.gogreen.utils.GoGreenUtil;
import com.onehippo.gogreen.utils.PageableCollection;

@ParametersInfo(type = OverviewParamInfo.class)
public class Overview extends BaseComponent {

    private static final Logger log = LoggerFactory.getLogger(Overview.class);
    
    private static final String PARAM_PAGE_SIZE = "pageSize";
    private static final int DEFAULT_PAGE_SIZE = 5;
    private static final String PARAM_CURRENT_PAGE = "pageNumber";
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int DEFAULT_SHOW_MORE = 25;
    private static final String PARAM_ORDER_BY = "orderBy";
    private static final String DEFAULT_ORDER_BY = "hippostdpubwf:lastModificationDate";

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        OverviewParamInfo paramInfo = getParametersInfo(request);

        HippoBean currentBean = this.getContentBean(request);
        if (currentBean == null) {
            return;
        }
        request.setAttribute("defaultShowMore", DEFAULT_SHOW_MORE);

        String pageSizeParam = getPublicRequestParameter(request, PARAM_PAGE_SIZE);
        if (pageSizeParam == null || "".equals(pageSizeParam)) {
            pageSizeParam = getParameter(PARAM_PAGE_SIZE, request);
        }
        int pageSize = ComponentUtil.parseIntParameter(PARAM_PAGE_SIZE, pageSizeParam, DEFAULT_PAGE_SIZE, log);
        request.setAttribute("pageSize", pageSize);

        String currentPageParam = getPublicRequestParameter(request, PARAM_CURRENT_PAGE);
        int currentPage = ComponentUtil.parseIntParameter(PARAM_CURRENT_PAGE, currentPageParam, DEFAULT_CURRENT_PAGE, log);

        String orderBy = getParameter(PARAM_ORDER_BY, request);
        if (orderBy == null || "".equals(orderBy)) {
            orderBy = DEFAULT_ORDER_BY;
        }

        String query = this.getPublicRequestParameter(request, "query");
        query = SearchInputParsingUtils.parse(query, false);
        request.setAttribute("query", StringEscapeUtils.escapeHtml(query));

        String order = this.getPublicRequestParameter(request, "order");
        request.setAttribute("order", StringEscapeUtils.escapeHtml(order));

        String from = this.getPublicRequestParameter(request, "from");
        String jsEnabled = getPublicRequestParameter(request, "jsEnabled");

        try {
            HstQuery hstQuery = this.getQueryManager(request).createQuery(getSiteContentBaseBean(request), Product.class);
            if (!StringUtils.isEmpty(query)) {
                Filter f = hstQuery.createFilter();
                Filter f1 = hstQuery.createFilter();
                f1.addContains(".", query);
                Filter f2 = hstQuery.createFilter();
                f2.addContains("hippogogreen:title", query);
                f.addOrFilter(f1);
                f.addOrFilter(f2);
                hstQuery.setFilter(f);
            } else {
                if (!StringUtils.isEmpty(order) && !"relevance".equals(order)) {
                    if ("-lastModificationDate".equals(order)) {
                        hstQuery.addOrderByDescending("hippostdpubwf:lastModificationDate");
                    } else if (order.startsWith("-")) {
                        hstQuery.addOrderByDescending("hippogogreen:" + order.substring(1));
                    } else {
                        hstQuery.addOrderByAscending("hippogogreen:" + order);
                    }
                } else {
                    hstQuery.addOrderByDescending(orderBy);
                }
            }
            if (from != null && Boolean.parseBoolean(jsEnabled)) {
                hstQuery.setOffset(Integer.valueOf(from));
            }

            PageableCollection pages;
            long resultCount = 0;

            if (!(currentBean instanceof HippoFacetChildNavigationBean || currentBean instanceof HippoFacetNavigation)) {
                final HstQueryResult result = hstQuery.execute();
                pages = new PageableCollection<HippoBean>(result.getHippoBeans(), pageSize, currentPage);
                resultCount = result.getSize();
            } else {
                final HippoFacetNavigationBean facNavBean = BeanUtils.getFacetNavigationBean(request, hstQuery, objectConverter);

                if (facNavBean == null) {
                    final List<HippoBean> noResults = Collections.emptyList();
                    pages = new PageableCollection(0, noResults);
                    resultCount = 0;
                } else {
                    final HippoResultSetBean result = facNavBean.getResultSet();
                    final HippoDocumentIterator<Product> beans = result.getDocumentIterator(Product.class);
                    if (hstQuery.getOffset() > 0) {
                        beans.skip(hstQuery.getOffset());
                    }
                    pages = new PageableCollection(beans, facNavBean.getCount().intValue(), GoGreenUtil.getIntConfigurationParameter(request,
                            Constants.PAGE_SIZE, pageSize), currentPage);
                    resultCount = result.getCount();
                }
            }
            request.setAttribute("docs", pages);
            request.setAttribute("count", resultCount);
            Boolean isReseller = request.isUserInRole("reseller");
            request.setAttribute("reseller", isReseller);
        }
        catch (QueryException qe) {
            log.error("Error while getting the documents " + qe.getMessage(), qe);
        }

    }


}
