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

import org.apache.cxf.common.util.StringUtils;
import org.hippoecm.hst.content.beans.standard.HippoFacetChildNavigationBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetNavigationBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.ResolvedSiteMapItem;
import org.hippoecm.hst.util.PathUtils;
import org.hippoecm.hst.util.SearchInputParsingUtils;
import org.hippoecm.hst.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.components.BaseComponent;

public class FacetNavigation extends BaseComponent {

    public static final Logger log = LoggerFactory.getLogger(FacetNavigation.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        String query = this.getPublicRequestParameter(request, "query");
        if (query != null) {
            query = SearchInputParsingUtils.parse(query, false);
            request.setAttribute("query", query);
        }

        String order = this.getPublicRequestParameter(request, "order");
        if (order != null) {
            request.setAttribute("order", order);
        }

        ResolvedSiteMapItem resolvedSiteMapItem = request.getRequestContext().getResolvedSiteMapItem();
        String resolvedContentPath = PathUtils.normalizePath(resolvedSiteMapItem.getRelativeContentPath());
        HippoFacetChildNavigationBean resolvedContentBean = null;
        
        // when the resolved sitemap item is /search, resolved content path can be null...
        if (!StringUtils.isEmpty(resolvedContentPath)) {
            resolvedContentBean = getSiteContentBaseBean(request).getBean(resolvedContentPath, HippoFacetChildNavigationBean.class);
        }
        
        HippoFacetNavigationBean facNavBean = null;

        if (resolvedContentBean != null) {
            // the content bean of the resolved sitemap item already points to a facet child navigation;
            // perform a text search within that facet.
            facNavBean = BeanUtils.getFacetNavigationBean(request, resolvedContentPath, query, objectConverter);
        } else {
            // perform a free text search within the facet indicated by the component parameter 'facetnav.location'
            String facetedNavLocation = getParameter("facetnav.location", request);
            if (facetedNavLocation == null) {
                log.warn("Please configure the 'facetnav.location' component parameter.");
                return;
            }
            facNavBean = BeanUtils.getFacetNavigationBean(request, facetedNavLocation, query, getObjectConverter());
        }

        request.setAttribute("facetnav", facNavBean);

        if (facNavBean instanceof HippoFacetChildNavigationBean) {
            request.setAttribute("childNav", "true");
        }
    }
}
