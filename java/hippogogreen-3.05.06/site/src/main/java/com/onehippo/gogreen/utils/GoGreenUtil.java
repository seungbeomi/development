package com.onehippo.gogreen.utils;

import org.apache.commons.lang.StringEscapeUtils;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.beans.ObjectBeanPersistenceException;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility methods
 *
 * @version $Id: GoGreenUtil.java 29489 2011-08-12 09:04:49Z aschrijvers $
 */
public final class GoGreenUtil {
    
    private static Logger log = LoggerFactory.getLogger(GoGreenUtil.class);
    public static final String PARAM_SITE_MENU = "siteMenu";


    /**
     * Returns null if parameter is empty string or  null, it escapes HTML otherwise
     *
     * @param request       hst request
     * @param parameterName name of the parameter
     * @return html escaped value
     */
    public static String getEscapedParameter(final HstRequest request, final String parameterName) {
        String value = request.getParameter(parameterName);
        if (value == null || value.trim().length() == 0) {
            return null;
        }
        return StringEscapeUtils.escapeHtml(value);
    }


    public static int getIntConfigurationParameter(final HstRequest request, final String param, final int defaultValue) {
        String paramValue = request.getParameter(param);
        if (paramValue != null) {
            try {
                return Integer.parseInt(paramValue);
            } catch (NumberFormatException nfe) {
                log.error("Error in parsing " + paramValue + " to integer for param " + param, nfe);
            }
        }
        return defaultValue;
    }

    private GoGreenUtil() {
    }

    public static void refreshWorkflowManager(final WorkflowPersistenceManager wpm) {
        if (wpm != null) {
            try {
                wpm.refresh();
            } catch (ObjectBeanPersistenceException obpe) {
                log.warn("Failed to refresh: " + obpe.getMessage(), obpe);
            }
        }
    }

    public static String getSiteMenuName(BaseHstComponent component, HstRequest request) {
        String result = component.getParameter(PARAM_SITE_MENU, request);

        if (result == null || result.trim().isEmpty()) {
            throw new HstComponentException("Missing component parameter: " + PARAM_SITE_MENU);
        }

        return result;
    }
}
