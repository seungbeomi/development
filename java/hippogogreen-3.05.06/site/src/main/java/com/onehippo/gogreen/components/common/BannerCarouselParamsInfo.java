package com.onehippo.gogreen.components.common;

import org.hippoecm.hst.core.parameters.DocumentLink;
import org.hippoecm.hst.core.parameters.Parameter;

public interface BannerCarouselParamsInfo {

    @Parameter(name = "banner1", required = true, displayName = "Banner 1")
    @DocumentLink(docType = "hippogogreen:banner")
    String getBanner1();

    @Parameter(name = "banner2", required = true, displayName = "Banner 2")
    @DocumentLink(docType = "hippogogreen:banner")
    String getBanner2();

    @Parameter(name = "banner3", required = true, displayName = "Banner 3")
    @DocumentLink(docType = "hippogogreen:banner")
    String getBanner3();

    @Parameter(name = "banner4", required = true, displayName = "Banner 4")
    @DocumentLink(docType = "hippogogreen:banner")
    String getBanner4();

    @Parameter(name = "banner5", required = true, displayName = "Banner 5")
    @DocumentLink(docType = "hippogogreen:banner")
    String getBanner5();

}
