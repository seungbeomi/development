package com.onehippo.gogreen.components.common;

import org.hippoecm.hst.core.parameters.Parameter;

/**
 * HST Component Parameters Info class for the Banner. Used by the PageComposer.
 */
public interface MapParamsInfo {
    String PARAM_ADDRESS = "address";

    @Parameter(name = PARAM_ADDRESS, required = true, displayName = "Address", defaultValue = "")
    String getAddress();
}
