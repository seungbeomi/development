/**
 * Copyright (C) 2010 Hippo B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onehippo.gogreen.components.products;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Session;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowCallbackHandler;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.repository.reviewedactions.FullReviewedActionsWorkflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.Product;
import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.utils.Constants;
import com.onehippo.gogreen.utils.GoGreenUtil;

public class SuggestProduct extends BaseComponent {

    private static final Logger log = LoggerFactory.getLogger(SuggestProduct.class);
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String SUCCESS = "success";
    private static final String ERRORS = "errors";

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        request.setAttribute(ERRORS, request.getParameterValues(ERRORS));
        request.setAttribute(NAME, request.getParameter(NAME));
        request.setAttribute(PRICE, request.getParameter(PRICE));
        request.setAttribute(SUCCESS, request.getParameter(SUCCESS));

    }

    @Override
    public void doAction(HstRequest request, HstResponse response) {

        String name = request.getParameter(NAME);
        Double price = populatePrice(request);
        List<String> errors = new ArrayList<String>(2);

        if (StringUtils.isEmpty(name)) {
            errors.add("invalid.name-label");
        }
        if (price <= 0D) {
            errors.add("invalid.price-label");
        }
        if (errors.size() > 0) {
            response.setRenderParameter(ERRORS, errors.toArray(new String[errors.size()]));
            response.setRenderParameter(NAME, name);
            response.setRenderParameter(PRICE, String.valueOf(price));
            return;
        }
        String description = request.getParameter(DESCRIPTION);
        Session persistableSession = null;
        WorkflowPersistenceManager wpm;

        try {
            persistableSession = getPersistableSession(request);
            wpm = getWorkflowPersistenceManager(persistableSession);
            wpm.setWorkflowCallbackHandler(new FullReviewedWorkflowCallbackHandler());

            String siteCanonicalBasePath = request.getRequestContext().getResolvedMount().getMount().getCanonicalContentPath();

            String productsFolderPath = siteCanonicalBasePath + "/products";
            wpm.createAndReturn(productsFolderPath, Constants.NT_PRODUCT, name, true);

            Product product = (Product) wpm.getObject(productsFolderPath + "/" + name);
            if (product != null) {
                product.setTitle(name);
                product.setDescriptionContent(description);
                product.setPrice(price);
                // update now           `
                wpm.update(product);
                response.setRenderParameter(SUCCESS, SUCCESS);
            } else {
                GoGreenUtil.refreshWorkflowManager(wpm);
            }


        } catch (Exception e) {
            log.warn("Failed to create product document: " + e.getMessage(), e);
        } finally {
            if (persistableSession != null) {
                persistableSession.logout();
            }
        }

    }

    private Double populatePrice(final HstRequest request) {
        if (request.getParameter(PRICE) != null) {
            try {
                return Double.valueOf(request.getParameter(PRICE));
            } catch (NumberFormatException nfe) {
                // ginore
            }
        }
        return 0D;
    }

    private static class FullReviewedWorkflowCallbackHandler implements WorkflowCallbackHandler<FullReviewedActionsWorkflow> {
        public void processWorkflow(FullReviewedActionsWorkflow wf) throws Exception {
            wf.requestPublication();
        }
    }

}
