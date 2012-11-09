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

import java.util.List;

import javax.servlet.ServletContext;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.hippoecm.hst.core.request.ComponentConfiguration;

import com.onehippo.gogreen.FeedParamsInfo;
import com.onehippo.gogreen.utils.FeedFetcher;
import com.sun.syndication.feed.synd.SyndFeed;

@ParametersInfo(type = FeedParamsInfo.class)
public class Feeds extends BaseComponent {
    private FeedFetcher feedFetcher;

    @Override
    public void init(ServletContext servletContext, ComponentConfiguration componentConfig) {
        super.init(servletContext, componentConfig);
        feedFetcher = new FeedFetcher();
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        // get parameters
        FeedParamsInfo paramsInfo = getParametersInfo(request);
        request.setAttribute("title", paramsInfo.getTitle());
        request.setAttribute("entriesPerFeed", paramsInfo.getNumberOfItems());
        String feedURL = paramsInfo.getFeedURL();
        int updateInterval = paramsInfo.getUpdateInterval();
        int connectTimeout = paramsInfo.getConnectTimeout();

        // get feed
        List<SyndFeed> feeds = feedFetcher.retrieveFeeds(new String[]{feedURL}, updateInterval, connectTimeout);
        request.setAttribute("feeds", feeds);
        request.setAttribute("feedsUrls", new String[]{feedURL});
    }
}
