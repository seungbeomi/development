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

import java.util.Iterator;
import java.util.List;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.sitemenu.EditableMenu;
import org.hippoecm.hst.core.sitemenu.HstSiteMenu;
import org.hippoecm.hst.core.sitemenu.HstSiteMenuItem;
import org.hippoecm.hst.core.sitemenu.HstSiteMenus;

import com.onehippo.gogreen.beans.Document;
import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.utils.GoGreenUtil;

public class SiteMenu extends BaseComponent {

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        
        String siteMenuName = GoGreenUtil.getSiteMenuName(this, request);
        HstSiteMenu menu = request.getRequestContext().getHstSiteMenus().getSiteMenu(siteMenuName);
        menu = new WrappedSiteMenu(menu, request.isUserInRole("reseller"));
        request.setAttribute("menu", menu);

        HippoBean bean = getContentBean(request);
        boolean detailPage = false;
        if (bean instanceof Document) {
            detailPage = true;
        }
        request.setAttribute("detailPage", detailPage);
    }

    public class WrappedSiteMenu implements HstSiteMenu {

        private final HstSiteMenu siteMenu;
        private final boolean isReseller;
        
        private WrappedSiteMenu(HstSiteMenu siteMenu, boolean isReseller) {
            this.siteMenu = siteMenu;
            this.isReseller = isReseller;
        }
        
        @Override
        public String getName() {
            return siteMenu.getName();
        }

        @Override
        public boolean isExpanded() {
            return siteMenu.isExpanded();
        }

        @Override
        public HstSiteMenuItem getSelectSiteMenuItem() {
            return siteMenu.getSelectSiteMenuItem();
        }

        @Override
        public List<HstSiteMenuItem> getSiteMenuItems() {
            List<HstSiteMenuItem> items = siteMenu.getSiteMenuItems();
            if (!isReseller) {
                Iterator<HstSiteMenuItem> iter = items.iterator();
                while (iter.hasNext()) {
                    HstSiteMenuItem item = iter.next();
                    if (item.getParameter("reselleronly") != null) {
                        iter.remove();
                    }
                }
            }
            return items;
        }

        @Override
        public HstSiteMenus getHstSiteMenus() {
            return siteMenu.getHstSiteMenus();
        }

        @Override
        public HstSiteMenuItem getDeepestExpandedItem() {
            return siteMenu.getDeepestExpandedItem();
        }

        @Override
        public EditableMenu getEditableMenu() {
            return siteMenu.getEditableMenu();
        }
        
    }
}
