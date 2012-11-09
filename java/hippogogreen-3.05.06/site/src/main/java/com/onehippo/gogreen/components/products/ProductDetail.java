/**
 * Copyright (C) 2011 Hippo B.V.
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jcr.Session;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.ObjectBeanPersistenceException;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowCallbackHandler;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.content.beans.standard.HippoFolder;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.hippoecm.hst.util.ContentBeanUtils;
import org.hippoecm.repository.reviewedactions.FullReviewedActionsWorkflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.Product;
import com.onehippo.gogreen.beans.Review;
import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.utils.Constants;
import com.onehippo.gogreen.utils.GoGreenUtil;

@ParametersInfo(type = ProductDetailParamInfo.class)
public class ProductDetail extends BaseComponent {

    private static final Logger log = LoggerFactory.getLogger(ProductDetail.class);

    private static final String DATE_PATTERN = "yyyy-MM-dd HH.mm.ss.SSS";
    private static final String NAME = "name";
    private static final String COMMENT = "comment";
    private static final String EMAIL = "email";
    private static final String SUCCESS = "success";
    private static final String ERRORS = "errors";

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {

        super.doBeforeRender(request, response);
        ProductDetailParamInfo paramInfo = getParametersInfo(request);
        String reviewsFolderName = paramInfo.getReviewsFolder();

        Product document = (Product) getContentBean(request);

        if (document == null) {
            redirectToNotFoundPage(response);
            return;
        }
        request.setAttribute("document", document);

        HippoBean siteContentBase = getSiteContentBaseBean(request);
        HippoFolder reviewsFolder = siteContentBase.getBean(reviewsFolderName);
        if (reviewsFolder == null) {
            log.warn("Product reviews folder not found: '{}/{}'. No product reviews will be shown.", siteContentBase.getPath(), reviewsFolderName);
        } else {
            try {
                List<Review> reviews = new ArrayList<Review>();

                final HstQuery incomingBeansQuery = ContentBeanUtils.createIncomingBeansQuery(document, reviewsFolder, 4, getObjectConverter(), Review.class, false);
                final HstQueryResult result = incomingBeansQuery.execute();
                final HippoBeanIterator beanIterator = result.getHippoBeans();
                int count = 0;
                while (beanIterator.hasNext()) {
                    Review review = (Review) beanIterator.nextHippoBean();
                    reviews.add(review);
                    count++;
                }
                request.setAttribute("reviews", reviews);
                request.setAttribute("votes", count);
            } catch (QueryException e) {
                log.error("Unable to execute query to get the reviews :" + e.getMessage(), e);
            }
        }
        request.setAttribute(ERRORS, request.getParameterValues(ERRORS));
        request.setAttribute(COMMENT, request.getParameter(COMMENT));
        request.setAttribute(NAME, request.getParameter(NAME));
        request.setAttribute(EMAIL, request.getParameter(EMAIL));
        request.setAttribute(SUCCESS, request.getParameter(SUCCESS));

        Boolean isReseller = request.isUserInRole("reseller");
        request.setAttribute("reseller", isReseller);
    }

    @Override
    public void doAction(HstRequest request, HstResponse response) {
        String name = GoGreenUtil.getEscapedParameter(request, NAME);
        String email = GoGreenUtil.getEscapedParameter(request, EMAIL);
        String comment = GoGreenUtil.getEscapedParameter(request, COMMENT);

        Long rating = Long.valueOf(request.getParameter("rating"));

        List<String> errors = new ArrayList<String>();

        if (StringUtils.isEmpty(name)) {
            errors.add("invalid.name-label");
        }
        if (StringUtils.isEmpty(comment)) {
            errors.add("invalid.comment-label");
        }
        if (StringUtils.isEmpty(email) || email.indexOf('@')== -1) {
            errors.add("invalid.email-label");
        }
        if (errors.size() > 0) {
            response.setRenderParameter(ERRORS, errors.toArray(new String[errors.size()]));
            response.setRenderParameter(NAME, name);
            response.setRenderParameter(COMMENT, comment);
            response.setRenderParameter(EMAIL, email);
            return;
        }

        HippoBean reviewFor = this.getContentBean(request);

        if (!(reviewFor instanceof Product)) {
            log.warn("Reviews can only be created for a product document");
            return;
        }

        Product product = (Product) this.getContentBean(request);
        String productUuid = product.getCanonicalHandleUUID();
        Session persistableSession = null;
        WorkflowPersistenceManager wpm;

        try {
            persistableSession = getPersistableSession(request);
            wpm = getWorkflowPersistenceManager(persistableSession);
            wpm.setWorkflowCallbackHandler(new FullReviewedWorkflowCallbackHandler());

            final String reviewFolderPath = createReviewFolderPath(request, product);
            final String reviewNodeName = createReviewNodeName(product);

            final String reviewPath = wpm.createAndReturn(reviewFolderPath, Constants.NT_REVIEW, reviewNodeName, true);

            Review review = (Review) wpm.getObject(reviewPath);
            // update content properties
            if (review != null) {
                review.setName(name);
                review.setComment(comment);
                review.setRating(rating);
                review.setEmail(email);
                review.setProductUuid(productUuid);

                // update now           `
                wpm.update(review);
                response.setRenderParameter(SUCCESS, SUCCESS);
            } else {
                log.warn("Failed to add review for product '{}': could not retrieve Review bean for node '{}'.", product.getName(), reviewPath);
                GoGreenUtil.refreshWorkflowManager(wpm);
            }
        } catch (Exception e) {
            log.warn("Failed to create a review for product '" + product.getName() + "'", e);
        } finally {
            if (persistableSession != null) {
                persistableSession.logout();
            }
        }
    }

    private String createReviewFolderPath(HstRequest request, Product product) {
        StringBuilder builder = new StringBuilder();

        builder.append(request.getRequestContext().getResolvedMount().getMount().getCanonicalContentPath());

        ProductDetailParamInfo paramInfo = getParametersInfo(request);
        String reviewsFolderName = paramInfo.getReviewsFolder();
        builder.append('/');
        builder.append(reviewsFolderName);
        builder.append('/');
        builder.append(product.getTitle());

        return builder.toString();
    }

    private String createReviewNodeName(Product product) {
        StringBuilder builder = new StringBuilder();

        builder.append("Review for ");
        builder.append(product.getTitle());
        builder.append(' ');

        final Date now = new Date();
        final String timestamp = new SimpleDateFormat(DATE_PATTERN).format(now);
        builder.append(timestamp);

        return builder.toString();
    }

    @Override
    public void doBeforeServeResource(HstRequest request, HstResponse response) throws HstComponentException {

        super.doBeforeServeResource(request, response);

        boolean succeeded = true;
        String errorMessage = "";

        String workflowAction = request.getParameter("workflowAction");

        String field = request.getParameter("field");

        final boolean requestPublication = "requestPublication".equals(workflowAction);
        final boolean saveDocument = ("save".equals(workflowAction) || requestPublication);

        if (saveDocument || requestPublication) {
            String documentPath = getContentBean(request).getPath();
            Session persistableSession = null;
            WorkflowPersistenceManager cpm = null;

            try {
                //NOTE: Don't need to use writable session here; use subject based session instead.
                //persistableSession = getPersistableSession(request);
                persistableSession = request.getRequestContext().getSession();
                
                cpm = getWorkflowPersistenceManager(persistableSession);
                cpm.setWorkflowCallbackHandler(new WorkflowCallbackHandler<FullReviewedActionsWorkflow>() {
                    public void processWorkflow(FullReviewedActionsWorkflow wf) throws Exception {
                        if (requestPublication) {
                            FullReviewedActionsWorkflow fraw = (FullReviewedActionsWorkflow) wf;
                            fraw.requestPublication();
                        }
                    }
                });

                Product document = (Product) cpm.getObject(documentPath);

                if (saveDocument) {
                    String content = request.getParameter("editor");

                    if ("hippogogreen:summary".equals(field)) {
                        document.setSummary(content);
                    } else if ("hippogogreen:description".equals(field)) {
                        document.setDescriptionContent(content);
                    }
                }

                // update now
                cpm.update(document);
                cpm.save();
            } catch (Exception e) {
                log.warn("Failed to create a comment: ", e);

                if (cpm != null) {
                    try {
                        cpm.refresh();
                    } catch (ObjectBeanPersistenceException e1) {
                        log.warn("Failed to refresh: ", e);
                    }
                }
            }
            // NOTE: no need to close the persistable session here because subject based session was retrieved from rc.
        }

        request.setAttribute("payload", "{\"success\": " + succeeded + ", \"message\": \"" + errorMessage + "\"}");
    }

    private static class FullReviewedWorkflowCallbackHandler implements WorkflowCallbackHandler<FullReviewedActionsWorkflow> {
        public void processWorkflow(FullReviewedActionsWorkflow wf) throws Exception {
            wf.requestPublication();
        }
    }
}
