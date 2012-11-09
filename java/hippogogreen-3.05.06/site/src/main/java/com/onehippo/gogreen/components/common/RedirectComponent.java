package com.onehippo.gogreen.components.common;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.components.BaseComponent;

public class RedirectComponent extends BaseComponent {

    private static final String REDIRECT_PARAM = "redirect";
    private static final Logger log = LoggerFactory.getLogger(RedirectComponent.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {

        final String redirect = getParameter(REDIRECT_PARAM, request);
        log.debug("redirect param is " + redirect);
        if (StringUtils.isEmpty(redirect)) {
            throw new HstComponentException("Parameter '" + REDIRECT_PARAM + "' is required for " + this.getClass().getName());
        }
        this.sendRedirect(redirect, request, response);
    }

}
