package com.onehippo.gogreen.components.common;

import org.hippoecm.hst.core.parameters.Parameter;

/**
 * HST Component Parameters Info class for the Banner. Used by the PageComposer.
 */
public interface VideoParamsInfo {
    String PARAM_VIDEOURL = "videoURL";

    @Parameter(name = PARAM_VIDEOURL, required = true, displayName = "Video URL", defaultValue = "")
    String getVideoURL();
}
