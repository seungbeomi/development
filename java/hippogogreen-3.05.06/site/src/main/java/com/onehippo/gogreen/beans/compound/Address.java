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

package com.onehippo.gogreen.beans.compound;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoItem;

/**
 * Bean mapping class for the 'hippogogreen:address' document type
 */
@Node(jcrType = "hippogogreen:address")
public class Address extends HippoItem {
    public String getStreet() {
        return getProperty("hippogogreen:street");
    }

    public String getNumber() {
        return getProperty("hippogogreen:number");
    }

    public String getCity() {
        return getProperty("hippogogreen:city");
    }

    public String getPostalCode() {
        return getProperty("hippogogreen:postalcode");
    }

    public String getProvince() {
        return getProperty("hippogogreen:province");
    }

    public String getCountry() {
        return getProperty("hippogogreen:country");
    }

    public String getTelephone() {
        return getProperty("hippogogreen:telephone");
    }

    public String getFax() {
        return getProperty("hippogogreen:fax");
    }

    public String getEmail() {
        return getProperty("hippogogreen:email");
    }

    public String getWebsite() {
        return getProperty("hippogogreen:website");
    }

    public String getOptionalText() {
        return getProperty("hippogogreen:optionalText");
    }

    public Double getLatitude() {
        return getProperty("hippogogreen:latitude");
    }

    public Double getLongitude() {
        return getProperty("hippogogreen:longitude");
    }
}
