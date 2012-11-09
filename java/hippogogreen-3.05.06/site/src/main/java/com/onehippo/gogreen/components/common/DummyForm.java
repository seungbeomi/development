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

import org.hippoecm.hst.component.support.forms.FormMap;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.forge.easyforms.hst.EmailForm;
import org.onehippo.forge.easyforms.model.Form;

/**
 * Event Registration component
 */
public class DummyForm extends EmailForm {



    @Override
    public boolean onValidationSuccess(final HstRequest request, final HstResponse response, final Form form, final FormMap map) {
        // just for a demo: not sending any emails
        return true;
    }

    @Override
    public void onProcessFail(HstRequest request, HstResponse response, Form form, FormMap map) {
        super.onProcessFail(request, response, form, map);
        request.setAttribute("success","dummysuccess");
    }
    
    @Override
    public void onProcessDone(HstRequest request, HstResponse response, Form form, FormMap map) {
        super.onProcessDone(request, response, form, map);
        request.setAttribute("success","dummysuccess");
    }
}
