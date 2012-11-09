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

package com.onehippo.gogreen.beans.compound;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoResourceBean;

import com.onehippo.gogreen.DocumentTypes;

@Node(jcrType = DocumentTypes.IMAGE_SET)
public class ImageSet extends HippoGalleryImageSet {

    public String getAlt() {
        return getProperty(DocumentTypes.ImageSet.ALT);
    }

    public HippoResourceBean getSmallThumbnail() {
        return getBean(DocumentTypes.ImageSet.SMALL_THUMBNAIL);
    }

    public HippoResourceBean getLargeThumbnail() {
        return getBean(DocumentTypes.ImageSet.LARGE_THUMBNAIL);
    }

    public HippoResourceBean getExtraLargeThumbnail() {
        return getBean(DocumentTypes.ImageSet.EXTRA_LARGE_THUMBNAIL);
    }

    public HippoResourceBean getMobileLogo() {
        return getBean(DocumentTypes.ImageSet.MOBILE_LOGO);
    }

    public HippoResourceBean getMobileThumbnail() {
        return getBean(DocumentTypes.ImageSet.MOBILE_THUMBNAIL);
    }

    public Copyright getCopyright() {
        return getBean(DocumentTypes.ImageSet.COPYRIGHT);
    }
    
}
