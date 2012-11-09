/**
 * Copyright (C) 2011 Hippo B.V.
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.hippoecm.hst.content.beans.standard.facetnavigation.HippoFacetNavigation;
import org.hippoecm.hst.content.beans.standard.facetnavigation.HippoFacetSubNavigation;
import org.hippoecm.hst.content.beans.standard.facetnavigation.HippoFacetsAvailableNavigation;

public class EventCalendarMonth {
    /**
     * The ordered list of weeks of this month, starting with the first week
     */
    private List<Week> weeks = new ArrayList<Week>();

    private String monthName;

    /**
     * Note month numbers below are 0-based: thus January = 0
     */
    private int month;
    private int prevMonth;
    private int nextMonth;

    private int year;
    private int prevMonthYear;
    private int nextMonthYear;

    private int totalEventsInMonth;
    private static final int WEEK_LENGTH = 7;

    public EventCalendarMonth(Calendar selectedCal, HippoFacetNavigation facetedEventsBean, Locale locale) {

        selectedCal.setFirstDayOfWeek(Calendar.MONDAY);
        selectedCal.set(Calendar.DAY_OF_MONTH, 1);

        monthName = selectedCal.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
        year = selectedCal.get(Calendar.YEAR);
        month = selectedCal.get(Calendar.MONTH);

        String daysFacetPath = "year/" + year + "/month/" + month + "/day";
        
        HippoFacetsAvailableNavigation daysInCurrentMonthBean = facetedEventsBean.getBean(daysFacetPath);

        if (daysInCurrentMonthBean == null) {
            EventsAndCalendar.log.debug("No days having an event found for year = {} and month = {}", String.valueOf(year), String.valueOf(monthName));
        } else {
            totalEventsInMonth = daysInCurrentMonthBean.getCount().intValue();
        }

        Calendar prevMonthCal = (Calendar) selectedCal.clone();
        prevMonthCal.add(Calendar.MONTH, -1);
        prevMonth = prevMonthCal.get(Calendar.MONTH);
        prevMonthYear = prevMonthCal.get(Calendar.YEAR);

        Calendar nextMonthCal = (Calendar) selectedCal.clone();
        nextMonthCal.add(Calendar.MONTH, 1);
        nextMonth = nextMonthCal.get(Calendar.MONTH);
        nextMonthYear = nextMonthCal.get(Calendar.YEAR);

        int weekOfMonth = selectedCal.get(Calendar.WEEK_OF_MONTH);

        int prevWeekOfMonth = weekOfMonth;
        Week newWeek = new Week(selectedCal);
        weeks.add(newWeek);

        while (weekOfMonth >= prevWeekOfMonth) {
            if (prevWeekOfMonth != weekOfMonth) {
                // add a new week
                newWeek = new Week(selectedCal);
                weeks.add(newWeek);
            }

            // add the day to the week and check whether there are events for the day:
            HippoFacetSubNavigation eventsForDay = null;
            if (daysInCurrentMonthBean != null) {
                String selectedDay = String.valueOf(selectedCal.get(Calendar.DAY_OF_MONTH));
                eventsForDay = daysInCurrentMonthBean.getBean(selectedDay);
            }
            newWeek.addDay(new Day(selectedCal, eventsForDay, false));

            // set the previous week nr to the current in the loop
            prevWeekOfMonth = weekOfMonth;
            // goto next day
            selectedCal.add(Calendar.DAY_OF_MONTH, 1);
            weekOfMonth = selectedCal.get(Calendar.WEEK_OF_MONTH);
        }

        // We have now all the RealDay's add to the Week objects. All we need now, is to the first Week prepend
        // all the NoopDay's to complete the first week, and append NoopDay's to the last week.

        Week firstWeek = weeks.get(0);
        int missingDays = WEEK_LENGTH - firstWeek.getDays().size();
        while (missingDays > 0) {
            --missingDays;
            // prepend before the first real day
            firstWeek.getDays().add(0, new Day(null, null, true));
        }

        Week lastWeek = weeks.get(weeks.size() - 1);
        missingDays = WEEK_LENGTH - lastWeek.getDays().size();
        while (missingDays > 0) {
            --missingDays;
            // append after the last day
            lastWeek.getDays().add(new Day(null, null, true));
        }

    }

    public int getPrevMonth() {
        return prevMonth;
    }

    public int getNextMonth() {
        return nextMonth;
    }

    public int getPrevMonthYear() {
        return prevMonthYear;
    }

    public int getNextMonthYear() {
        return nextMonthYear;
    }

    public String getMonthName() {
        return monthName;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public int getTotalEventsInMonth() {
        return totalEventsInMonth;
    }

}
