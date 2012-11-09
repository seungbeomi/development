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

import java.util.List;

import com.onehippo.gogreen.beans.EventDocument;

public interface IDay {
    /**
     * @return <code>true</code> when this is not a real day but just an empty placeholder day for easy rendering
     */
    boolean isDummy();

    /**
     * @return <code>true</code> when this Day is the current day
     */
    boolean isToday();

    /**
     * @return the day of the month
     */
    int getDayOfMonth();

    /**
     * @return the List of EventDocument's for this Day
     */
    List<EventDocument> getEvents();

    /**
     * @return the first event document when present
     */
    EventDocument getFirstEvent();

    /**
     * @return the number of events for this day
     */
    int getNumberOfEvents();
}