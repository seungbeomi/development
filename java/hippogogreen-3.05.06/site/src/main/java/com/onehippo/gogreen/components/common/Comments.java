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

package com.onehippo.gogreen.components.common;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jcr.RepositoryException;
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
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.HippoFolder;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.util.ContentBeanUtils;
import org.hippoecm.repository.api.WorkflowException;
import org.hippoecm.repository.reviewedactions.FullReviewedActionsWorkflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.Comment;
import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.utils.Constants;
import com.onehippo.gogreen.utils.GoGreenUtil;

public class Comments extends BaseComponent {

    private static final Logger log = LoggerFactory.getLogger(Comments.class);
    private static final String DATE_PATTERN = "yyyy-MM-dd HH.mm.ss.SSS";
    private static final String PARAM_COMMENTS_FOLDER = "commentsFolder";
    private static final WorkflowCallbackHandler<FullReviewedActionsWorkflow> COMMENTS_WORKFLOW = new CommentsWorkflow();
    private static final int PATH_DEPTH = 4;
    private static final String SUCCESS = "success";
    private static final String NAME = "name";
    private static final String COMMENT = "comment";
    private static final String EMAIL = "email";
    private static final String ERRORS = "errors";

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        String commentsFolderPath = getCommentsFolderPath(request);
        log.debug("Comments folder path " + commentsFolderPath);
        HippoFolder commentsFolder = getSiteContentBaseBean(request).getBean(commentsFolderPath);
        log.debug("Comments folder " + commentsFolder);
        if (commentsFolder != null) {
            HippoDocumentBean document = (HippoDocumentBean) getContentBean(request);
            if (document != null) {
                try {
                    List<Comment> comments = new LinkedList<Comment>();

                    HstQuery incomingBeansQuery = ContentBeanUtils.createIncomingBeansQuery(document, commentsFolder, PATH_DEPTH, getObjectConverter(),
                            Comment.class, false);
                    HstQueryResult result = incomingBeansQuery.execute();

                    HippoBeanIterator beanIterator = result.getHippoBeans();
                    while (beanIterator.hasNext()) {
                        comments.add((Comment) beanIterator.nextHippoBean());
                    }
                    request.setAttribute("comments", comments);
                } catch (QueryException e) {
                    log.error("Unable to execute query to get comments", e);
                }
            }
        }
        request.setAttribute(ERRORS, request.getParameterValues(ERRORS));
        request.setAttribute(COMMENT, request.getParameter(COMMENT));
        request.setAttribute(NAME, request.getParameter(NAME));
        request.setAttribute(EMAIL, request.getParameter(EMAIL));
        request.setAttribute(SUCCESS, request.getParameter(SUCCESS));
    }

    private String getCommentsFolderPath(HstRequest request) {
        String commentsFolderPath = getParameter(PARAM_COMMENTS_FOLDER, request);

        if (commentsFolderPath == null || commentsFolderPath.trim().isEmpty()) {
            throw new HstComponentException("Missing component parameter: " + PARAM_COMMENTS_FOLDER);
        }

        return commentsFolderPath;
    }

    @Override
    public void doAction(HstRequest request, HstResponse response) {
        String name = GoGreenUtil.getEscapedParameter(request, NAME);
        String email = GoGreenUtil.getEscapedParameter(request, EMAIL);
        String comment = GoGreenUtil.getEscapedParameter(request, COMMENT);

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

        HippoBean commentFor = getContentBean(request);

        if (!(commentFor instanceof HippoDocumentBean)) {
            log.warn("Comment can only be created for a document");
            return;
        }

        HippoDocumentBean document = (HippoDocumentBean) commentFor;

        String documentUuid = document.getCanonicalHandleUUID();
        Session persistableSession = null;

        try {
            persistableSession = getPersistableSession(request);
            final WorkflowPersistenceManager wpm = getWorkflowPersistenceManager(persistableSession);
            wpm.setWorkflowCallbackHandler(COMMENTS_WORKFLOW);

            final String commentNodePath = createCommentNode(request, wpm, document);

            // update comment properties
            final Comment commentObj = (Comment) wpm.getObject(commentNodePath);
            if (commentObj != null) {
                commentObj.setName(name);
                commentObj.setEmail(email);
                commentObj.setBody(comment);
                commentObj.setDocumentUuid(documentUuid);
                // update now           `
                wpm.update(commentObj);
                response.setRenderParameter(SUCCESS, SUCCESS);
            } else {
                log.error("Failed to add comment for path: {}", commentNodePath);
                GoGreenUtil.refreshWorkflowManager(wpm);
            }

        } catch (Exception e) {
            log.warn("Failed to create comment", e);

        } finally {
            if (persistableSession != null) {
                persistableSession.logout();
            }
        }
    }

    /**
     * Creates a JCR node that stores an empty comment on a document.
     *
     * @param request the HST request
     * @param wpm the workflow persistence manager for creating the comment
     * @param document the document that was commented on
     * @return the absolute path to the JCR node that stores the created empty comment
     * @throws ObjectBeanPersistenceException when creating the empty comment failed
     */
    private String createCommentNode(HstRequest request, WorkflowPersistenceManager wpm, HippoDocumentBean document) throws ObjectBeanPersistenceException {
        final String commentNodeName = createCommentNodeName(document);
        final String commentFolderPath = createCommentFolderPath(request, document);

        return wpm.createAndReturn(commentFolderPath, Constants.NT_COMMENT, commentNodeName, true);
    }

    private String createCommentNodeName(final HippoDocumentBean document) {
        final StringBuilder builder = new StringBuilder("Comment on ");
        builder.append(document.getLocalizedName());

        final Date now = new Date();
        final String timestamp = new SimpleDateFormat(DATE_PATTERN).format(now);
        builder.append(' ');
        builder.append(timestamp);

        return builder.toString();
    }

    private String createCommentFolderPath(final HstRequest request, final HippoDocumentBean document) {
        final StringBuilder builder = new StringBuilder();

        final String siteCanonicalBasePath = request.getRequestContext().getResolvedMount().getMount().getCanonicalContentPath();
        builder.append(siteCanonicalBasePath);

        builder.append('/');

        final String folderPath = getCommentsFolderPath(request);
        builder.append(folderPath);

        if (!folderPath.endsWith("/")) {
            builder.append('/');
        }

        builder.append(document.getName());

        return builder.toString();
    }

    private static class CommentsWorkflow implements WorkflowCallbackHandler<FullReviewedActionsWorkflow> {

        @Override
        public void processWorkflow(FullReviewedActionsWorkflow workflow) throws RemoteException, RepositoryException,
                WorkflowException {
            workflow.requestPublication();
        }

    }

}
