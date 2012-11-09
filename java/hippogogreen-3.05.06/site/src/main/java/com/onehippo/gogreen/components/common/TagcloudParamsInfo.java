package com.onehippo.gogreen.components.common;

import org.hippoecm.hst.core.parameters.Parameter;

/**
 * HST Component Parameters Info class for the Tagcloud. Used by the PageComposer.
 */
public interface TagcloudParamsInfo {
    String PARAM_TAGCLOUDLOCATION = "tagCloudLocation";

    @Parameter(name = PARAM_TAGCLOUDLOCATION, required = true, displayName = "Cloud location", defaultValue = "")
    String getTagcloudLocation();
}
