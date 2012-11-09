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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.manager.ObjectBeanManager;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.facetnavigation.HippoFacetResult;
import org.hippoecm.hst.content.beans.standard.facetnavigation.HippoFacetSearch;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.forge.tcmp.beans.AbstractTagBean;
import org.onehippo.forge.tcmp.beans.CustomTagBean;
import org.onehippo.forge.tcmp.beans.ECMTagBean;
import org.onehippo.forge.tcmp.beans.RelatedDocs;
import org.onehippo.forge.tcmp.beans.TagCloudBean;
import org.onehippo.forge.tcmp.model.AbstractTag;
import org.onehippo.forge.tcmp.model.CustomTag;
import org.onehippo.forge.tcmp.model.ECMTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TagComponent extends BaseComponent {

    private static final Logger log = LoggerFactory.getLogger(TagComponent.class);

    private static final List<Class<? extends HippoBean>> CLASSES = new ArrayList<Class<? extends HippoBean>>();

    static {
        CLASSES.add(AbstractTagBean.class);
        CLASSES.add(TagCloudBean.class);
        CLASSES.add(ECMTagBean.class);
        CLASSES.add(CustomTagBean.class);
        CLASSES.add(RelatedDocs.class);
        CLASSES.add(HippoFacetSearch.class);
        CLASSES.add(HippoFacetResult.class);
    }


    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
    }

    public List<? extends HippoBean> getRelatedBeans(final HstRequest request) {

        HippoBean bean = getContentBean(request);
        AbstractTag tag = null;

        if (bean instanceof CustomTagBean) {
            tag = new CustomTag((CustomTagBean) bean);

        } else if (bean instanceof ECMTagBean) {
            tag = new ECMTag((ECMTagBean) bean);
            ECMTag eTag = (ECMTag) tag;
            ObjectBeanManager obm = this.getObjectBeanManager(request);
            //TODO check if it is facetresult or navigation thingie STRATEGIZE

            try {
                //TODO
                //This should be fixed with new version of Tag cloud plug-in
                HippoFacetResult result = (HippoFacetResult) obm.getObject(eTag.getReference() + "/hippo:resultset");
                List<HippoDocumentBean> documentList = result.getDocuments();
                eTag.setRelatedBeans(documentList);
            } catch (ObjectBeanManagerException e) {
                log.debug("ObjectBeanManagerException", e);
            }

        }
        if (tag != null) {
            request.setAttribute("tag", tag);
            return tag.getRelatedBeans();
        }
        return Collections.emptyList();
    }

    @Override
    protected List<Class<? extends HippoBean>> getLocalAnnotatedClasses() {
        return CLASSES;
    }

}

