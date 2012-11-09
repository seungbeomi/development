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

package com.onehippo.gogreen.components.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.BaseDocument;
import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.components.ComponentUtil;
import com.onehippo.gogreen.utils.Constants;

/**
 * LatestItems Component.
 * <p/>
 * This component needs the parameters limit, nodetype, scope, orderBy, and sortOrder (hst component parameters). limit:
 * number of items - defaults to 5 if not available nodetype: node type of the latest item nodes to look for (eg:
 * hippogogreen:review) scope: The folder from which the nodes need to be retrieved from. (reviews) orderBy: property to
 * order the results on (default: Constants.PROP_DATE) sortOrder: "ascending" or "descending" (default: descending)
 * constraint: "upcoming" or "past" (optional, defaults to no constraint)
 * <p/>
 * Used in the Home Page.
 */
@ParametersInfo(type = LatestItemsParamsInfo.class)
public class LatestItems extends BaseComponent {

    private static final String CONSTRAINT_UPCOMING = "upcoming";
    private static final String CONSTRAINT_PAST = "past";
    private static final String PARAM_NODE_TYPE = "nodetype";
    private static final String PARAM_SCOPE = "scope";
    private static final String PARAM_ORDER_BY = "orderBy";
    private static final String PARAM_SORT_ORDER = "sortOrder";
    private static final String PARAM_CONSTRAINT = "constraint";

    private static final Logger log = LoggerFactory.getLogger(LatestItems.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        String itemsScope = getParameter(PARAM_SCOPE, request);
        String orderBy = getParameter(PARAM_ORDER_BY, request);
        String sortOrderParam = getParameter(PARAM_SORT_ORDER, request);
        String constraint = getParameter(PARAM_CONSTRAINT, request);
        String nodeType = getParameter(PARAM_NODE_TYPE, request);

        int limit = ((LatestItemsParamsInfo) getParametersInfo(request)).getLimit();
        request.setAttribute("limit", limit);

        if (orderBy == null) {
            orderBy = Constants.PROP_DATE;
        }

        boolean ascending = ComponentUtil.parseAscendingParameter(PARAM_SORT_ORDER, sortOrderParam, false, log);

        HippoBean folderBean = getSiteContentBaseBean(request);
        if (itemsScope != null) {
            folderBean = folderBean.getBean(itemsScope);
            if (folderBean == null) {
                log.warn("Scope not found: '{}'. Latest items of type '{}' will not be shown", itemsScope, nodeType);
                return;
            }
        }

        final HstQueryManager hstQueryManager = getQueryManager(request);
        final HstQuery hstQuery;

        final List<HippoBean> results = new ArrayList<HippoBean>();
        try {
            if (StringUtils.isEmpty(nodeType)) {
                hstQuery = hstQueryManager.createQuery(folderBean, BaseDocument.class, true);
            } else {
                hstQuery = hstQueryManager.createQuery(folderBean, getObjectConverter().getAnnotatedClassFor(nodeType), false);
            }

            if (ascending) {
                hstQuery.addOrderByAscending(orderBy);
            } else {
                hstQuery.addOrderByDescending(orderBy);
            }

            hstQuery.setLimit(limit);

            if (CONSTRAINT_UPCOMING.equals(constraint)) {
                Filter filter = hstQuery.createFilter();
                filter.addGreaterOrEqualThan(Constants.PROP_DATE, new Date());
                hstQuery.setFilter(filter);
            } else if (CONSTRAINT_PAST.equals(constraint)) {
                Filter filter = hstQuery.createFilter();
                filter.addLessThan(Constants.PROP_DATE, new Date());
                hstQuery.setFilter(filter);
            }

            final HstQueryResult queryResult = hstQuery.execute();
            request.setAttribute("count", queryResult.getSize());

            final HippoBeanIterator beanIterator = queryResult.getHippoBeans();
            while (beanIterator.hasNext()) {
                HippoBean bean = beanIterator.nextHippoBean();
                results.add(bean);
            }
        } catch (QueryException e) {
            log.error("Unable to execute the query for latest items", e);
        } finally {
            request.setAttribute("items", results);
        }
    }
}
