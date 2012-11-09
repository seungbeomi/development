package com.onehippo.gogreen.components.common;

import org.hippoecm.hst.core.parameters.Parameter;

/**
 * HST Component Parameters Info class for LatestItems. Used PageComposer.
 */
public interface LatestItemsParamsInfo {
    String PARAM_LIMIT = "limit";

    @Parameter(name = PARAM_LIMIT, required = true, displayName = "Limit", defaultValue = "5")
    int getLimit();
}
