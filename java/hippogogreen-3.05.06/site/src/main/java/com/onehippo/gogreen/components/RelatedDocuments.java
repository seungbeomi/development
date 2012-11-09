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

package com.onehippo.gogreen.components;

import java.util.List;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;

import com.onehippo.gogreen.beans.compound.RelatedDocs;

public class RelatedDocuments extends BaseComponent {

    private static final String PARAM_HEADLINE = "headline";
    private static final String PARAM_PRIMARY_NODE_TYPE = "primaryNodeType";
    private static final String DEFAULT_HEADLINE = "Related";
    private static final String RELDOCS_NODE = "relateddocs:docs";

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        HippoBean bean = getContentBean(request);
        if (bean == null) {
            return;
        }

        // get the headline
        String headline = getParameter(PARAM_HEADLINE, request);
        if (headline == null) {
            headline = DEFAULT_HEADLINE;
        }
        request.setAttribute("headline", headline);
        
        // find all related documents with the given primary node type
        String primaryNodeType = getParameter(PARAM_PRIMARY_NODE_TYPE, request);
        List<HippoBean> relatedBeans = null;


        RelatedDocs relatedDocs = bean.getBean(RELDOCS_NODE);
        if (relatedDocs != null) {
            relatedBeans = relatedDocs.getRelatedDocs(primaryNodeType);
        }

        request.setAttribute("documents", relatedBeans);
    }
    
}
