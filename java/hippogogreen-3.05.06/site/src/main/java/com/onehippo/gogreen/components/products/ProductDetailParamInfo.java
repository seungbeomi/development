package com.onehippo.gogreen.components.products;

import org.hippoecm.hst.core.parameters.Parameter;

public interface ProductDetailParamInfo {

    @Parameter(name = "reviewsFolder", required = false, defaultValue = "reviews", displayName = "Reviews folder")
    String getReviewsFolder();
}
