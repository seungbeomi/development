package com.onehippo.gogreen.components.products;

import org.hippoecm.hst.core.parameters.DocumentLink;
import org.hippoecm.hst.core.parameters.Parameter;

public interface FeaturedProductsParamInfo {

    @Parameter(name = "product1", required = true, displayName = "Product 1")
    @DocumentLink( docType = "hippogogreen:product")
    String getProduct1();

    @Parameter(name = "product2", required = true, displayName = "Product 2")
    @DocumentLink ( docType = "hippogogreen:product")
    String getProduct2();

    @Parameter(name = "product3", required = true, displayName = "Product 3")
    @DocumentLink ( docType = "hippogogreen:product")
    String getProduct3();


}
