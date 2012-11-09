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

import com.onehippo.gogreen.exceptions.BeanNotFoundException;
import com.onehippo.gogreen.utils.RepoSiteMenuItem;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;

public class AboutList extends BaseComponent {

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        
        HippoBean bean = getContentBean(request);
        
        if (bean == null) {
            redirectToNotFoundPage(response);
            throw new BeanNotFoundException("Cannot create document list: content bean is null");
        } else if (bean.isHippoDocumentBean()) {
            request.setAttribute("document", bean);
        } else if (bean.isHippoFolderBean()) {
            HippoFolderBean folder = (HippoFolderBean) bean;
            List<HippoDocumentBean> documents = folder.getDocuments();
            
            if (documents.size() == 1) {
                request.setAttribute("document", documents.get(0));
            } else {
                String folderName = RepoSiteMenuItem.retrieveLocalizedName(folder);
                request.setAttribute("folderName", folderName);
                request.setAttribute("documents", documents);
            }
        }
    }

}
