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

import java.util.List;
import java.util.Map;

import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.exceptions.MenuNotFoundException;
import com.onehippo.gogreen.utils.GoGreenUtil;

import org.hippoecm.hst.configuration.HstNodeTypes;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.core.sitemenu.EditableMenu;
import org.hippoecm.hst.core.sitemenu.EditableMenuItem;
import org.hippoecm.hst.core.sitemenu.EditableMenuItemImpl;
import org.hippoecm.hst.core.sitemenu.HstSiteMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepoFolderSiteMenu extends BaseComponent {

    public static final Logger log = LoggerFactory.getLogger(RepoFolderSiteMenu.class);
    
    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        
        String siteMenuName = GoGreenUtil.getSiteMenuName(this, request);
        
        HstSiteMenu menu = request.getRequestContext().getHstSiteMenus().getSiteMenu(siteMenuName);
        
        if (menu != null) {
            EditableMenu editableMenu = menu.getEditableMenu();
            addRepoBasedMenuItems(editableMenu, request);
            request.setAttribute("menu", editableMenu);
        } else {
            throw new MenuNotFoundException("Unknown site menu: " + siteMenuName);
        }
    }

    private void addRepoBasedMenuItems(EditableMenu editable, final HstRequest request) {
        List<EditableMenuItem> menuItems = editable.getMenuItems();
        for (EditableMenuItem item : menuItems) {
            if (item.isRepositoryBased()) {
                Map<String, Object> properties = item.getProperties();
                String root = null;
                String[] paramNames = (String[]) properties.get(HstNodeTypes.GENERAL_PROPERTY_PARAMETER_NAMES);
                String[] paramValues = (String[]) properties.get(HstNodeTypes.GENERAL_PROPERTY_PARAMETER_VALUES);
                if (paramNames.length != paramValues.length) {
                    log.warn("Parameter name array and parameter values arrays have different lengths");
                    continue;
                }
                for (int i = 0 ;i < paramNames.length; i++) {
                    String propName = paramNames[i];
                    if ("root".equals(propName)) {
                        root = paramValues[i];
                        break;
                    }
                }
                if (root == null) {
                    continue;
                }
                final HippoBean rootMenuBean = getSiteContentBaseBean(request).getBean(root);
                if (rootMenuBean != null && rootMenuBean.isHippoFolderBean()) {
                    HippoFolderBean rootFolder = (HippoFolderBean) rootMenuBean;

                    for (final HippoBean document : rootFolder.getDocuments()) {
                        EditableMenuItem repoMenuItem = new EditableMenuItemImpl(item) {

                            private HstLink hstLink;
                            private String localizedName;

                            {
                                HstRequestContext requestContext = request.getRequestContext();
                                HstLinkCreator linkCreator = requestContext.getHstLinkCreator();
                                hstLink = linkCreator.create(document, requestContext);
                                localizedName = document.getLocalizedName();
                            }

                            @Override
                            public HstLink getHstLink() {
                                return hstLink;
                            }

                            @Override
                            public String getName() {
                                return localizedName;
                            }

                            @Override
                            public boolean isRepositoryBased() {
                                return true;
                            }
                        };
                        item.addChildMenuItem(repoMenuItem);
                    }
                }
            }
        }
    }

}
