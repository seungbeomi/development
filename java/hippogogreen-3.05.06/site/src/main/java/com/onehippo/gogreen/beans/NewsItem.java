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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.onehippo.gogreen.beans.compound.ImageSet;
import com.onehippo.gogreen.beans.compound.ImageSetLink;
import com.onehippo.gogreen.utils.Constants;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;

@Node(jcrType=Constants.NT_NEWSITEM)
public class NewsItem extends Document{
    
    private List<ImageSet> images;
    
    public List<ImageSet> getImages() {
        initImages();
        return images;
    }

    public ImageSet getFirstImage() {
        initImages();
        return images.isEmpty() ? null : images.get(0);
    }
    
    private void initImages() {
        if (images == null) {
            List<ImageSetLink> links = getChildBeans(Constants.PROP_IMAGELINK);

            images = new ArrayList<ImageSet>(links.size());
            
            for (ImageSetLink link: links) {
                HippoBean referenced = link.getReferencedBean();
                if (referenced instanceof ImageSet) {
                    ImageSet imageSet = (ImageSet)referenced;
                    images.add(imageSet);
                }
            }
        }
    }
    
    public Calendar getDate() {
        return getProperty(Constants.PROP_DATE);
    }

    public String[] getCategories() {
        return getProperty(Constants.PROP_CATEGORIES);
    }  
}
