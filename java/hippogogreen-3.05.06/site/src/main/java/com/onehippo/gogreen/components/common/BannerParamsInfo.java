package com.onehippo.gogreen.components.common;

import org.hippoecm.hst.core.parameters.DocumentLink;
import org.hippoecm.hst.core.parameters.Parameter;

/**
 * HST Component Parameters Info class for the Banner. Used by the PageComposer.
 */
public interface BannerParamsInfo {
    String PARAM_BANNERLOCATION = "bannerlocation";

    @Parameter(name = PARAM_BANNERLOCATION, required = true, displayName = "Banner")
    @DocumentLink(docType="hippogogreen:banner")
    String getBannerLocation();
}
