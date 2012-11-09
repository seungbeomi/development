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

package com.onehippo.gogreen.components.search;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.ResolvedSiteMapItem;

public class ErrorSearchComponent extends AbstractSearchComponent {

    private static final String HTML_SUFFIX = ".html";
    
    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        // set 404 status
        response.setStatus(HstResponse.SC_NOT_FOUND);

        // find url that exists in the sitemap
        String workingPath = getWorkingUrl(request);

        String query = getQuery(request);

        if (query == null) {
            String path = request.getRequestContext().getResolvedSiteMapItem().getPathInfo();
            query = path.substring(path.lastIndexOf('/') + 1);
            if (query != null && query.endsWith(HTML_SUFFIX)) {
                query = query.substring(0, query.length() - HTML_SUFFIX.length());
            }

        }
        searchDocuments(request, query);

        request.setAttribute("pagenotfound", true);
        request.setAttribute("parentpage", workingPath);
    }

    /*
     * Checks if the bases of the url is something that is in the sitemap and returns this or an empty String
     */
    private String getWorkingUrl(HstRequest request){
        ResolvedSiteMapItem hstSiteMapItem = null;
        String parentUrl = request.getPathInfo();
        if(!parentUrl.equals("") && parentUrl.startsWith("/") && parentUrl.substring(1).contains("/")){
            parentUrl = parentUrl.substring(1);
            parentUrl = parentUrl.substring(0,parentUrl.indexOf("/"));
            hstSiteMapItem = request.getRequestContext().getSiteMapMatcher().match(parentUrl,
                    request.getRequestContext().getResolvedMount());
            if(hstSiteMapItem != null && hstSiteMapItem.getHstComponentConfiguration().getId().equals("hst:pages/error")){
                return "";
            }else{
                return parentUrl;
            }
        }else{
            return "";
        }
    }
}
