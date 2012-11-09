package com.onehippo.gogreen;

import org.hippoecm.hst.core.parameters.Parameter;

/**
 * HST Component Parameters Info class for the Feed. Used by the PageComposer.
 */
public interface FeedParamsInfo {
    String PARAM_TITLE = "title";
    String PARAM_FEEDURL = "feedURL";
    String PARAM_NUMBEROFITEMS = "numberOfItems";
    String PARAM_UPDATEINTERVAL = "updateInterval";
    String PARAM_CONNECTTIMEOUT = "connectTimeout";

    @Parameter(name = PARAM_TITLE, required = false, displayName = "Title", defaultValue = "Feed")
    String getTitle();

    @Parameter(name = PARAM_FEEDURL, required = true, displayName = "Url", defaultValue = "")
    String getFeedURL();

    @Parameter(name = PARAM_NUMBEROFITEMS, required = true, displayName = "Nr of Items", defaultValue = "3")
    int getNumberOfItems();

    @Parameter(name = PARAM_UPDATEINTERVAL, required = true, displayName = "Update interval", defaultValue = "15")
    int getUpdateInterval();

    @Parameter(name = PARAM_CONNECTTIMEOUT, required = true, displayName = "Connect timeout (ms)", defaultValue = "2000")
    int getConnectTimeout();
}
