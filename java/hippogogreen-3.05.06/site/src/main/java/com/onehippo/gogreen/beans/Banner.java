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

package com.onehippo.gogreen.beans;

import com.onehippo.gogreen.beans.compound.ImageSet;
import com.onehippo.gogreen.beans.compound.ImageSetLink;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoMirror;


/**
 * [hippogogreen:banner] > hippogogreen:basedocument
 * - hippogogreen:title (string)
 * + hippogogreen:image (hippogallerypicker:imagelink)
 * + hippogogreen:doclink (hippo:mirror)
 */

@Node(jcrType = "hippogogreen:banner")
public class Banner extends HippoDocument {

    public String getTitle() {
        return getProperty("hippogogreen:title");
    }

    public HippoBean getDocLink() {
        HippoMirror mirrorBean = (HippoMirror) getBean("hippogogreen:doclink");
        return mirrorBean.getReferencedBean();
    }


    public ImageSet getImage() {
        ImageSetLink imageLink = (ImageSetLink) getBean("hippogogreen:image");
        return (ImageSet) imageLink.getReferencedBean();
    }


}
