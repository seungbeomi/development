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

package com.onehippo.gogreen.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
import com.sun.syndication.fetcher.impl.HttpClientFeedFetcher;
import com.sun.syndication.io.FeedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedFetcher {

    private static final Logger log = LoggerFactory.getLogger(FeedFetcher.class);
    
    private FeedFetcherCache feedInfoCache;
    private List<SyndFeed> cachedFeeds;
    private final Object feedUpdateLock = new Object();
    private long lastUpdate;
    private static final int MINUTE = 60 * 1000;

    public FeedFetcher() {
        feedInfoCache = HashMapFeedInfoCache.getInstance();
        lastUpdate = 0;
    }
    
    /**
     * Only fetch new feeds after at least <updateInterval> seconds have passed since the last call.
     *
     */
    public List<SyndFeed> retrieveFeeds(String[] feedUrls, int updateInterval, int connectTimeout) {
        // check if the feeds need updating
        boolean updateFeeds = false;
        int updateIntervalMillis = updateInterval * MINUTE;
        long now = System.currentTimeMillis();
        synchronized(feedUpdateLock) {
            if (now > (lastUpdate + updateIntervalMillis)) {
                updateFeeds = true;
                lastUpdate = now;
            }
        }
        
        if (updateFeeds) {
            // let one rendering thread try to update the feeds
            List<SyndFeed> newFeeds = getSyndFeeds(feedUrls, connectTimeout);
            
            synchronized(feedUpdateLock) {
                cachedFeeds = newFeeds;
            }
        }
        
        return cachedFeeds;
    }

    private List<SyndFeed> getSyndFeeds(String[] urls, int connectTimeout) {
        List<SyndFeed> feeds = new ArrayList<SyndFeed>();

        HttpClientFeedFetcher feedFetcher = new HttpClientFeedFetcher(feedInfoCache);
        feedFetcher.setConnectTimeout(connectTimeout);
        
        for (String url : urls) {
            try {
                URL feedUrl = new URL(url);
                SyndFeed feed = feedFetcher.retrieveFeed(feedUrl);
                feeds.add(feed);
            } catch (MalformedURLException e) {
                log.warn("Malformed feed url: " + url);
            } catch (IllegalArgumentException e) {
                // URL is null; ignore
            } catch (IOException e) {
                log.error("I/O error while fetching feed: " + url, e);
            } catch (FeedException e) {
                log.warn("Error while parsing feed: " + url);
            } catch (FetcherException e) {
                log.warn("Error " + e.getResponseCode() + " while fetching feed: " + url);
            }
        }

        return feeds;
    }
    
}
