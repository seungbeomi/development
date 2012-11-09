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
package com.onehippo.gogreen.login;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HstConcurrentLoginFilter implements Filter {
    
    private static Logger log = LoggerFactory.getLogger(HstConcurrentLoginFilter.class);
    
    private static final String USERNAME_SESSIONID_MAP_ATTR = HstConcurrentLoginFilter.class.getName() + ".sessionidsmap";
    
    private static final String USERNAME_ATTR = HstConcurrentLoginFilter.class.getName() + ".username";
    
    private Map<String, HttpSessionWrapper> usernameHttpSessionWrapperMap;
    
    private Set<String> disallowConcurrentLoginUsernames;
    
    private Set<String> allowConcurrentLoginUsernames;
    
    private boolean earlySessionInvalidation;

    @SuppressWarnings("unchecked")
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        usernameHttpSessionWrapperMap = (Map<String, HttpSessionWrapper>) servletContext.getAttribute(USERNAME_SESSIONID_MAP_ATTR);
        
        if (usernameHttpSessionWrapperMap == null) {
            usernameHttpSessionWrapperMap = Collections.synchronizedMap(new HashMap<String, HttpSessionWrapper>());
            servletContext.setAttribute(USERNAME_SESSIONID_MAP_ATTR, usernameHttpSessionWrapperMap);
        }
        
        String [] disallowConcurrentLoginUsernamesArray = StringUtils.split(filterConfig.getInitParameter("disallowConcurrentLoginUsernames"), " ,\t\r\n");
        
        if (disallowConcurrentLoginUsernamesArray != null && disallowConcurrentLoginUsernamesArray.length > 0) {
            disallowConcurrentLoginUsernames = new HashSet<String>(Arrays.asList(disallowConcurrentLoginUsernamesArray));
        }
        
        log.info("HstConcurrentLoginFilter's disallowConcurrentLoginUsernames: " + disallowConcurrentLoginUsernames);
        
        String [] allowConcurrentLoginUsernamesArray = StringUtils.split(filterConfig.getInitParameter("allowConcurrentLoginUsernames"), " ,\t\r\n");
        
        if (allowConcurrentLoginUsernamesArray != null && allowConcurrentLoginUsernamesArray.length > 0) {
            allowConcurrentLoginUsernames = new HashSet<String>(Arrays.asList(allowConcurrentLoginUsernamesArray));
        }
        
        earlySessionInvalidation = BooleanUtils.toBoolean(filterConfig.getInitParameter("earlySessionInvalidation"));
        
        log.info("HstConcurrentLoginFilter's allowConcurrentLoginUsernames: " + allowConcurrentLoginUsernames);
    }
    
    public void destroy() {
        if (usernameHttpSessionWrapperMap != null) {
            usernameHttpSessionWrapperMap.clear();
            usernameHttpSessionWrapperMap = null;
        }
    }
    
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;  // NOSONAR: req can always be cast to an HTTP servlet request
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            String username = (request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : null);
            
            if (!StringUtils.isBlank(username)) {
                String usernameInSession = (String) session.getAttribute(USERNAME_ATTR);
                
                if (!username.equals(usernameInSession)) {
                    registerUserSession(request, username);
                } else if (!isMySessionStillValid(session, username)) {
                    log.debug("HstConcurrentLoginFilter found another session had been logged in by {}. This session is to be invalidated.", username);
                    session.invalidate();
                }
            }
        }
        
        chain.doFilter(req, res);
    }
    
    private boolean isMySessionStillValid(HttpSession session, String username) {
        boolean disallowedConcurrentLoginForUser = false;
        
        if (disallowConcurrentLoginUsernames != null && (disallowConcurrentLoginUsernames.contains("*") || disallowConcurrentLoginUsernames.contains(username))) {
            log.debug("HstConcurrentLoginFilter disallows concurrent login for {}", username);
            disallowedConcurrentLoginForUser = true;
        }
        
        if (!disallowedConcurrentLoginForUser) {
            if (allowConcurrentLoginUsernames != null && (allowConcurrentLoginUsernames.contains("*") || allowConcurrentLoginUsernames.contains(username))) {
                log.debug("HstConcurrentLoginFilter allows concurrent login for {}", username);
                return true;
            }
        }
        
        HttpSessionWrapper sessionWrapperForUsername = (HttpSessionWrapper) usernameHttpSessionWrapperMap.get(username);
        return sessionWrapperForUsername.equalsTo(session);
    }
    
    private void registerUserSession(HttpServletRequest request, String username) {
        log.debug("HstConcurrentLoginFilter will register session for {}", username);
        HttpSession session = request.getSession();
        session.setAttribute(USERNAME_ATTR, username);
        
        ServletContext servletContext = session.getServletContext();
        @SuppressWarnings("unchecked")
        Map<String, HttpSessionWrapper> map = (Map<String, HttpSessionWrapper>) servletContext.getAttribute(USERNAME_SESSIONID_MAP_ATTR);
        
        if (map != null) {
            String newSessionId = session.getId();
            HttpSessionWrapper oldHttpSessionWrapper = map.put(username, new HttpSessionWrapper(session, earlySessionInvalidation));
            log.debug("HstConcurrentLoginFilter registered session ({}) for {}.", newSessionId, username);
            
            if (oldHttpSessionWrapper != null) {
                oldHttpSessionWrapper.invalidate();
                log.debug("HstConcurrentLoginFilter kicked out session ({}) for {}.", oldHttpSessionWrapper.getId(), username);
            }
        } else {
            log.error("HstConcurrentLoginFilter is in invalid state. The session ids map is not found.");
        }
    }
    
    static void unregisterUserSession(HttpSession session) {
        String username = (String) session.getAttribute(USERNAME_ATTR);
        log.debug("HstConcurrentLoginFilter will unregister session for {}", username);
        
        if (username == null) {
            return;
        }
        
        ServletContext servletContext = session.getServletContext();
        @SuppressWarnings("unchecked")
        Map<String, HttpSessionWrapper> map = (Map<String, HttpSessionWrapper>) servletContext.getAttribute(USERNAME_SESSIONID_MAP_ATTR);
        
        if (map != null) {
            HttpSessionWrapper oldHttpSessionWrapper = null;
            
            synchronized (map) {
                oldHttpSessionWrapper = map.get(username);
                
                if (oldHttpSessionWrapper != null) {
                    if (oldHttpSessionWrapper.equalsTo(session)) {
                        map.remove(username);
                        log.debug("HstConcurrentLoginFilter kicked out session ({}) for {}.", oldHttpSessionWrapper.getId(), username);
                    } else {
                        log.debug("HstConcurrentLoginFilter didn't kick out session ({}) for {} because it's logged on by other http session.", oldHttpSessionWrapper.getId(), username);
                    }
                }
            }
        } else {
            log.error("HstConcurrentLoginFilter is in invalid state. The session ids map is not found.");
        }
        
        session.removeAttribute(USERNAME_ATTR);
        log.debug("HstConcurrentLoginFilter removed user name session attribute: {}", username);
    }
    
    private static class HttpSessionWrapper implements Serializable {
        
        private static final long serialVersionUID = 1L;
        
        private String sessionId;
        private HttpSession session;
        
        public HttpSessionWrapper(HttpSession session, boolean keepSessionObject) {
            if (keepSessionObject) {
                this.session = session;
            } else {
                this.sessionId = session.getId();
            }
        }
        
        public boolean equalsTo(final HttpSession otherSession) {
            if (otherSession == null) {
                return false;
            }
            
            if (session != null) {
                return session.equals(otherSession);
            } else {
                return sessionId.equals(otherSession.getId());
            }
        }
        
        public String getId() {
            if (session != null) {
                return session.getId();
            } else {
                return sessionId;
            }
        }
        
        public void invalidate() {
            if (session != null) {
                session.invalidate();
            }
        }
    }
}
