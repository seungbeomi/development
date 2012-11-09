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

import com.onehippo.gogreen.utils.Constants;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.beans.standard.HippoMirror;
import org.onehippo.forge.easyforms.beans.FormBean;

@Node(jcrType = Constants.NT_SIMPLE_DOCUMENT)
public class SimpleDocument extends BaseDocument {

    public String getTitle() {
        return getProperty(Constants.PROP_TITLE);
    }

    public String getSummary() {
        return getProperty(Constants.PROP_SUMMARY);
    }

    public HippoHtml getDescription() {
        return getBean(Constants.PROP_DESCRIPTION);
    }

    public FormBean getForm() {
        HippoMirror mirror = getBean(Constants.PROP_FORM);
        if (mirror == null) {
            return null;
        }
        return (FormBean) mirror.getDeref();
    }
}
