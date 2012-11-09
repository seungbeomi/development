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

import com.onehippo.gogreen.components.BaseComponent;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ParametersInfo(type = BannerParamsInfo.class)
public class Banner extends BaseComponent {

    public static final Logger log = LoggerFactory.getLogger(Banner.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        BannerParamsInfo paramsInfo = getParametersInfo(request);
        String bannerLocation = paramsInfo.getBannerLocation();

        log.debug("banner location specified in hst is " + bannerLocation);

        final HippoBean document = getSiteContentBaseBean(request).getBean(bannerLocation);

        log.debug("banner document is " + document);

        if (document == null) {
            return;
        }
        request.setAttribute("document", document);
    }
}
