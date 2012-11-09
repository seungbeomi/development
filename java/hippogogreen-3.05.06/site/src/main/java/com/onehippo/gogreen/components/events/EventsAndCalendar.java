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

package com.onehippo.gogreen.components.events;

import java.util.Calendar;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetChildNavigationBean;
import org.hippoecm.hst.content.beans.standard.facetnavigation.HippoFacetNavigation;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.EventDocument;
import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.components.ComponentUtil;
import com.onehippo.gogreen.exceptions.BeanNotFoundException;
import com.onehippo.gogreen.utils.Constants;
import com.onehippo.gogreen.utils.GoGreenUtil;
import com.onehippo.gogreen.utils.PageableCollection;

public class EventsAndCalendar extends BaseComponent {

    public static final Logger log = LoggerFactory.getLogger(EventsAndCalendar.class);
    private static final String PARAM_PAGE_SIZE = "pageSize";
    private static final int DEFAULT_PAGE_SIZE = 5;
    private static final String PARAM_CURRENT_PAGE = "pageNumber";
    private static final int DEFAULT_CURRENT_PAGE = 1;

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        //HippoFolderBean n = (HippoFolderBean) getContentNode(request);
        HippoBean eventsOverviewBean = this.getContentBean(request);
        if (eventsOverviewBean == null) {
            throw new BeanNotFoundException("Needed eventsOverviewBean bean not found. Cannot proceed");
        }
        String pageSizeParam = getParameter(PARAM_PAGE_SIZE, request);
        int pageSize = ComponentUtil.parseIntParameter(PARAM_PAGE_SIZE, pageSizeParam, DEFAULT_PAGE_SIZE, log);

        String currentPageParam = getPublicRequestParameter(request, PARAM_CURRENT_PAGE);
        int currentPage = ComponentUtil.parseIntParameter(PARAM_CURRENT_PAGE, currentPageParam, DEFAULT_CURRENT_PAGE, log);
        if (!(eventsOverviewBean instanceof HippoFacetChildNavigationBean)) {
            try {
                HstQuery query = this.getQueryManager(request).createQuery(eventsOverviewBean, EventDocument.class);
                query.addOrderByDescending("hippogogreen:date");
                HstQueryResult result = query.execute();
                request.setAttribute("documents", new PageableCollection<HippoBean>(
                        result.getHippoBeans(), pageSize, currentPage));

            } catch (QueryException qe) {
                log.error("Error while getting the documents " + qe.getMessage(), qe);
            }
        } else {
            HippoFacetChildNavigationBean facetBean = (HippoFacetChildNavigationBean) eventsOverviewBean;
            request.setAttribute("documents", new PageableCollection(facetBean.getResultSet().getDocumentIterator(EventDocument.class), facetBean.getCount().intValue(), GoGreenUtil.getIntConfigurationParameter(request,
                    Constants.PAGE_SIZE, pageSize), currentPage));
        }
        // if there is a faceted navigation at 'facetedevents' we can populate the calendar, otherwise we'll throw an exception
        HippoFacetNavigation facetedEventsBean = eventsOverviewBean.getBean("facetedevents", HippoFacetNavigation.class);
        if (facetedEventsBean == null) {
            throw new BeanNotFoundException("The EventsAndCalendar cannot be created: We expect a 'hippofacnav:facetnavigation' node at '"
                    + eventsOverviewBean.getPath() + "/facetedevents'");
        }
        String yearStr = request.getParameter("year");
        String monthStr = request.getParameter("month");
        Calendar selectedCal = Calendar.getInstance();
        if (yearStr != null && monthStr != null) {
            // if invalid format for year or month, a runtime NumberFormatException will be thrown, which will be handled by the PageErrorHandler
            int year = Integer.parseInt(yearStr);
            int month = Integer.parseInt(monthStr);
            selectedCal.set(Calendar.YEAR, year);
            selectedCal.set(Calendar.MONTH, month);
        }
        EventCalendarMonth ecm = new EventCalendarMonth(selectedCal, facetedEventsBean, request.getLocale());
        request.setAttribute("calendar", ecm);
    }
}
