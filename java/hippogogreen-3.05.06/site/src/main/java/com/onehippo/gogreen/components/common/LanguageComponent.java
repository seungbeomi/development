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

import java.util.ArrayList;
import java.util.List;

import com.onehippo.gogreen.components.BaseComponent;

import org.hippoecm.hst.content.beans.standard.HippoAvailableTranslationsBean;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;

public class LanguageComponent extends BaseComponent {

    static public class Translation {

        private final String language;

        private final boolean available;
        private final HstLink link;
        public Translation(String language, final HstLink link, boolean available) {
            this.language = language;
            this.available = available;
            this.link = link;
        }

        public String getLanguage() {
            return language;
        }

        public boolean isAvailable() {
            return available;
        }

        public HstLink getLink() {
            return link;
        }
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        final HippoBean baseBean = this.getSiteContentBaseBean(request);
        final HippoAvailableTranslationsBean<HippoBean> availableBaseTranslations = baseBean.getAvailableTranslationsBean();

        final HippoBean contentBean = this.getContentBean(request);
        final HippoAvailableTranslationsBean<HippoBean> availableContentTranslations = contentBean == null ? null : contentBean.getAvailableTranslationsBean();

        final String requestLocale = request.getLocale().getLanguage();
        final HstRequestContext requestContext = request.getRequestContext();

        final List<Translation> translations = new ArrayList<Translation>();

        for (String baseLocale : availableBaseTranslations.getAvailableLocales()) {
            // skip the current locale
            if (baseLocale.equals(requestLocale)) {
                continue;
            }

            Translation translation = null;

            // first try to create a direct link to the translated content
            translation = createTranslation(requestContext, availableContentTranslations, baseLocale, true);

            // second, try to create a link to the base site
            if (translation == null) {
                translation = createTranslation(requestContext, availableBaseTranslations, baseLocale, false);
            }

            if (translation != null) {
                translations.add(translation);
            }
        }

        request.setAttribute("translations", translations);
    }

    private Translation createTranslation(final HstRequestContext requestContext, final HippoAvailableTranslationsBean<HippoBean> availableTranslations, final String baseLocale, final boolean translatedContentAvailable) {
        if (availableTranslations == null || !availableTranslations.hasTranslation(baseLocale)) {
            return null;
        }

        HippoBean translationBean = availableTranslations.getTranslation(baseLocale);
        HstLink link = requestContext.getHstLinkCreator().create(translationBean.getNode(), requestContext);

        if (link.isNotFound()) {
            return null;
        }

        return new Translation(baseLocale, link, translatedContentAvailable);
    }

}
