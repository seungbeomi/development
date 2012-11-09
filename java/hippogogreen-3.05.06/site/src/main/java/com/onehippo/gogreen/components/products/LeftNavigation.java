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

import org.hippoecm.hst.content.beans.standard.facetnavigation.HippoFacetsAvailableNavigation;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;

import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.exceptions.ComponentParameterNotFoundException;

public class LeftNavigation extends BaseComponent {

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        String facetLocation = getParameter("facet.location", request);
        if (facetLocation == null) {
            throw new ComponentParameterNotFoundException("Please configure the 'facet.location' parameter on productleftnav component");
        }

        HippoFacetsAvailableNavigation facetNavigation = getSiteContentBaseBean(request).getBean(facetLocation, HippoFacetsAvailableNavigation.class);
        if (facetNavigation != null) {
            request.setAttribute("facets", facetNavigation.getFolders());
        }
    }
}
