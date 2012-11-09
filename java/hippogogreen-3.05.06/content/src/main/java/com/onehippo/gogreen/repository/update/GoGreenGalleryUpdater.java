/*
* Copyright 2011 Hippo.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.onehippo.gogreen.repository.update;

import org.hippoecm.repository.ext.UpdaterContext;
import org.hippoecm.repository.ext.UpdaterItemVisitor;
import org.hippoecm.repository.ext.UpdaterModule;

public class GoGreenGalleryUpdater implements UpdaterModule {

    @Override
    public void register(final UpdaterContext context) {
        context.registerName("cms-gallery");
        context.registerStartTag("v18-cms-gallery");
        context.registerStartTag("v3.1.8-gogreen-gallery");
        context.registerEndTag("v3.2.0-gogreen-gallery");
        context.registerAfter("repository-upgrade-v19a");
        context.registerAfter("hippogogreen-relaxed-doctype-upgrade");
        reloadNamespace(context);
    }

    private void reloadNamespace(final UpdaterContext context) {
        context.registerVisitor(new UpdaterItemVisitor.NamespaceVisitor(context, "hippogallery", getClass().getClassLoader().getResourceAsStream("hippogallery.cnd")));
        context.registerVisitor(new UpdaterItemVisitor.NamespaceVisitor(context, "hippogogreengallery", getClass().getClassLoader().getResourceAsStream("hippo-namespaces/hippogogreen-gallery.cnd")));
    }


}


