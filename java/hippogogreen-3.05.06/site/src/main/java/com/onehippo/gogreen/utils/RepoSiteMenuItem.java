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

package com.onehippo.gogreen.utils;

import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.core.sitemenu.EditableMenuItem;
import org.hippoecm.hst.core.sitemenu.EditableMenuItemImpl;
import org.hippoecm.repository.api.HippoNode;

public class RepoSiteMenuItem extends EditableMenuItemImpl {

    private int childCount;
    
    public RepoSiteMenuItem(HippoFolderBean folder, EditableMenuItem parent, HstRequest request,
            HippoBean currentContentBean) {
        super(parent);

        name = retrieveLocalizedName(folder);
        depth = parent.getDepth() - 1;
        repositoryBased = true;
        properties = folder.getProperties();

        HstRequestContext requestContext = request.getRequestContext();
        HstLinkCreator linkCreator = requestContext.getHstLinkCreator();
        hstLink = linkCreator.create(folder, requestContext);

        if (currentContentBean != null) {
            if (folder.isAncestor(currentContentBean)) {
                expanded = true;
            }
            if (folder.isSelf(currentContentBean)) {
                expanded = true;
                selected = true;
                getEditableMenu().setSelectedMenuItem(this);
            }
        }

        List<HippoFolderBean> childFolders = folder.getFolders();
        childCount = folder.getDocumentSize() + childFolders.size();

        if (depth > 0 && expanded) {
            for (HippoFolderBean childFolder : childFolders) {
                RepoSiteMenuItem childMenuItem = new RepoSiteMenuItem(childFolder, this, request, currentContentBean);
                if (childMenuItem.getChildCount() > 0) {
                    addChildMenuItem(childMenuItem);
                }
            }
        }
    }

    public int getChildCount() {
        return childCount;
    }
    
    /**
     * Retrieves the localized name of a folder. When the localized name cannot be found, the plain JCR node
     * name is returned. This method is a workaround for issue HSTTWO-1149. When that issue is fixed, this 
     * method can be replaced by folder.getLocalizedName().
     *  
     * @param folder the hippo folder to retrieve the name of
     * @return the localized name of the folder, or the JCR node name when the localized name cannot be retrieved.
     */
    public static String retrieveLocalizedName(HippoFolderBean folder) {
        String localizedName = folder.getLocalizedName();
        
        HippoNode node = (HippoNode)folder.getNode();
        
        try {
            String jcrNodeName = node.getName();
            if (jcrNodeName.equals(localizedName)) {
                // we hit issue HSTTWO-1149: try harder
                Node canonical = node.getCanonicalNode();
                if (canonical != null) {
                    return ((HippoNode)canonical).getLocalizedName();
                }
            }
        } catch (RepositoryException ignored) {
            // ignore
        }

        return localizedName;
    }    
    
}
