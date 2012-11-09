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
package com.onehippo.gogreen.login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hippoecm.hst.security.TransientUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;

public class TestHstConcurrentLoginFilter {
    
    private MockServletContext servletContext;
    private HstConcurrentLoginFilter filter;
    
    @Before
    public void setUp() throws Exception {
        servletContext = new MockServletContext();
        filter = new HstConcurrentLoginFilter();
    }
    
    @Test
    public void testNonconcurrentLogin() throws Exception {
        MockFilterConfig filterConfig = new MockFilterConfig(servletContext);
        filter.init(filterConfig);
        
        MockHttpServletRequest req = new MockHttpServletRequest(servletContext);
        MockHttpServletResponse res = new MockHttpServletResponse();
        
        MockHttpSession sessionOfAnon = new MockHttpSession();
        req.setSession(sessionOfAnon);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfAnon.isInvalid());
        
        MockHttpSession sessionOfJohn = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn.isInvalid());
        
        MockHttpSession sessionOfJane = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("jane"));
        req.setSession(sessionOfJane);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJane.isInvalid());
    }
    
    @Test
    public void testConcurrentLogin() throws Exception {
        MockFilterConfig filterConfig = new MockFilterConfig(servletContext);
        filter.init(filterConfig);
        
        MockHttpServletRequest req = new MockHttpServletRequest(servletContext);
        MockHttpServletResponse res = new MockHttpServletResponse();
        
        MockHttpSession sessionOfJohn1 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn1);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn1.isInvalid());
        
        MockHttpSession sessionOfJohn2 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn2.isInvalid());
        
        MockHttpSession sessionOfJohn3 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn3);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn3.isInvalid());
        
        req.setSession(sessionOfJohn1);
        filter.doFilter(req, res, new MockFilterChain());
        assertTrue(sessionOfJohn1.isInvalid());
        
        req.setSession(sessionOfJohn2);
        filter.doFilter(req, res, new MockFilterChain());
        assertTrue(sessionOfJohn2.isInvalid());
        
        req.setSession(sessionOfJohn3);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn3.isInvalid());
    }
    
    @Test
    public void testConcurrentLoginWithAllowingAll() throws Exception {
        MockFilterConfig filterConfig = new MockFilterConfig(servletContext);
        filterConfig.addInitParameter("allowConcurrentLoginUsernames", "*");
        filter.init(filterConfig);
        
        MockHttpServletRequest req = new MockHttpServletRequest(servletContext);
        MockHttpServletResponse res = new MockHttpServletResponse();
        
        MockHttpSession sessionOfJohn1 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn1);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn1.isInvalid());
        
        MockHttpSession sessionOfJohn2 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn2.isInvalid());
        
        req.setSession(sessionOfJohn1);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn1.isInvalid());
        
        req.setSession(sessionOfJohn2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn2.isInvalid());
    }
    
    @Test
    public void testConcurrentLoginWithDisallowingAll() throws Exception {
        MockFilterConfig filterConfig = new MockFilterConfig(servletContext);
        filterConfig.addInitParameter("disallowConcurrentLoginUsernames", "*");
        filter.init(filterConfig);
        
        MockHttpServletRequest req = new MockHttpServletRequest(servletContext);
        MockHttpServletResponse res = new MockHttpServletResponse();
        
        MockHttpSession sessionOfJohn1 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn1);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn1.isInvalid());
        
        MockHttpSession sessionOfJohn2 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn2.isInvalid());
        
        req.setSession(sessionOfJohn1);
        filter.doFilter(req, res, new MockFilterChain());
        assertTrue(sessionOfJohn1.isInvalid());
        
        req.setSession(sessionOfJohn2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn2.isInvalid());
    }
    
    @Test
    public void testConcurrentLoginWithAllowingSelectedUsers() throws Exception {
        MockFilterConfig filterConfig = new MockFilterConfig(servletContext);
        filterConfig.addInitParameter("allowConcurrentLoginUsernames", "admin, editor");
        filterConfig.addInitParameter("disallowConcurrentLoginUsernames", "editor, john");
        filter.init(filterConfig);
        
        MockHttpServletRequest req = new MockHttpServletRequest(servletContext);
        MockHttpServletResponse res = new MockHttpServletResponse();
        
        MockHttpSession sessionOfAdmin1 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("admin"));
        req.setSession(sessionOfAdmin1);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfAdmin1.isInvalid());
        
        MockHttpSession sessionOfAdmin2 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("admin"));
        req.setSession(sessionOfAdmin2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfAdmin2.isInvalid());
        
        req.setSession(sessionOfAdmin1);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfAdmin1.isInvalid());
        
        req.setSession(sessionOfAdmin2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfAdmin2.isInvalid());
                
        MockHttpSession sessionOfEditor1 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("editor"));
        req.setSession(sessionOfEditor1);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfEditor1.isInvalid());
        
        MockHttpSession sessionOfEditor2 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("editor"));
        req.setSession(sessionOfEditor2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfEditor2.isInvalid());
        
        req.setSession(sessionOfEditor1);
        filter.doFilter(req, res, new MockFilterChain());
        assertTrue(sessionOfEditor1.isInvalid());
        
        req.setSession(sessionOfEditor2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfEditor2.isInvalid());
        
        MockHttpSession sessionOfJohn1 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn1);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn1.isInvalid());
        
        MockHttpSession sessionOfJohn2 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn2.isInvalid());
        
        req.setSession(sessionOfJohn1);
        filter.doFilter(req, res, new MockFilterChain());
        assertTrue(sessionOfJohn1.isInvalid());
        
        req.setSession(sessionOfJohn2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn2.isInvalid());
    }
    
    @Test
    public void testConcurrentLoginWithEarlyInvalidation() throws Exception {
        MockFilterConfig filterConfig = new MockFilterConfig(servletContext);
        filterConfig.addInitParameter("earlySessionInvalidation", "true");
        filter.init(filterConfig);
        
        MockHttpServletRequest req = new MockHttpServletRequest(servletContext);
        MockHttpServletResponse res = new MockHttpServletResponse();
        
        MockHttpSession sessionOfJohn1 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn1);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn1.isInvalid());
        
        MockHttpSession sessionOfJohn2 = new MockHttpSession(servletContext);
        req.setUserPrincipal(new TransientUser("john"));
        req.setSession(sessionOfJohn2);
        filter.doFilter(req, res, new MockFilterChain());
        assertFalse(sessionOfJohn2.isInvalid());
        
        assertTrue(sessionOfJohn1.isInvalid());
    }
    
}
