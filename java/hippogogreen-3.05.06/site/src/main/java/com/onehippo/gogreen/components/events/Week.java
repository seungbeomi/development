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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Week {

    private int weekOfMonth;
    private int weekOfYear;


    /**
     * The ordered list of day of this month. The days start at {@link com.onehippo.gogreen.components.events.EventCalendarMonth#FIRST_DAY_OF_WEEK}. If the first of last
     * week has days that are not part of the current month, the Day is still added as a {@link NoopDay}, to make rendering view as
     * easy as possible
     */
    private List<Day> days = new ArrayList<Day>();

    public Week(Calendar cal) {
        this.weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
        this.weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
    }

    public void addDay(Day day) {
        days.add(day);
    }

    public int getWeekOfMonth() {
        return weekOfMonth;
    }

    public int getWeekOfYear() {
        return weekOfYear;
    }

    public List<Day> getDays() {
        return days;
    }

}