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

package com.onehippo.gogreen.beans;

import java.util.Calendar;
import java.util.Date;

import org.hippoecm.hst.content.beans.Node;

/**
 * User: vivek
 * Date: Jun 29, 2010
 * Time: 11:26:35 AM
 */

@Node(jcrType = "hippogogreen:job")
public class JobsDocument extends Document {

    public Date getClosingDate() {
        Calendar cal = getProperty("hippogogreen:closingdate");
        if (cal == null) {
            return null;
        }
        return cal.getTime();
    }

    public String[] getIndustries() {
        return getProperty("hippogogreen:industries");
    }

    public Double getExperience() {
        return getProperty("hippogogreen:experience");
    }

    public String getEducation() {
        return getProperty("hippogogreen:education");
    }

    public String getLocation() {
        return getProperty("hippogogreen:location");
    }

    public Double getSalary() {
        return getProperty("hippogogreen:salary");
    }

    public String getJobtype() {
        return getProperty("hippogogreen:title");
    }
    public String getEmployer()
    {
        return getProperty("hippogogreen:employer");
    }
}
