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

package com.onehippo.gogreen.components;

import javax.jcr.Session;

import org.hippoecm.hst.content.beans.ObjectBeanPersistenceException;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowCallbackHandler;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.HippoFolder;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.util.ContentBeanUtils;
import org.hippoecm.repository.reviewedactions.FullReviewedActionsWorkflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.Comment;
import com.onehippo.gogreen.beans.Document;
import com.onehippo.gogreen.beans.EventDocument;
import com.onehippo.gogreen.beans.NewsItem;

public class Detail extends BaseComponent {

    public static final Logger log = LoggerFactory.getLogger(Detail.class);
    private static final int PATH_DEPTH = 4;

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {

        super.doBeforeRender(request, response);
        HippoBean document = getContentBean(request);

        if (document == null) {
            redirectToNotFoundPage(response);
            return;
        }
        request.setAttribute("document", document);

        HippoFolder commentsFolder = null;
        int commentCount = 0;

        if (document instanceof NewsItem) {
            commentsFolder = getSiteContentBaseBean(request).getBean("comments/news");

        } else if (document instanceof EventDocument) {
            commentsFolder = getSiteContentBaseBean(request).getBean("comments/events");
        }

        if (commentsFolder != null) {
            try {
                HstQuery incomingBeansQuery = ContentBeanUtils.createIncomingBeansQuery((HippoDocumentBean) document, commentsFolder, PATH_DEPTH, getObjectConverter(), Comment.class, false);
                commentCount = incomingBeansQuery.execute().getSize();
            } catch (QueryException e) {
                log.error(e.getMessage());
            }

        }


        request.setAttribute("commentCount", commentCount);

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

                Document document = (Document) cpm.getObject(documentPath);

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

}
