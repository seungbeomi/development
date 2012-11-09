package com.onehippo.gogreen.components.common;

import java.util.Collection;
import java.util.HashSet;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.components.BaseComponent;

public class Login extends BaseComponent {

    private final Logger log = LoggerFactory.getLogger(Login.class);
    
    private final static String SELECT_USER_QUERY = "SELECT * FROM hipposys:user WHERE fn:name()='{}'";
    
    private final static String SELECT_GROUPS_QUERY = "SELECT * FROM hipposys:group WHERE jcr:primaryType='hipposys:group' AND hipposys:members='{}'";
    
    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        Boolean loggedin = request.getUserPrincipal() != null;
        request.setAttribute("loggedin", loggedin);
        
        if (loggedin) {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                Session session = null;
                try {
                    session = request.getRequestContext().getSession();
                    Node userNode = getUserNode(session, request);
                    if (userNode != null) {
                        user = new User(userNode, getGroupNodes(session, request));
                        request.getSession().setAttribute("user", user);
                    }
                }
                catch (RepositoryException e) {
                    log.error("Failed to retrieve user from repository", e);
                }
                finally {
                    if (session != null) {
                        session.logout();
                    }
                }
            }
            if (user != null) {
                request.setAttribute("user", user);
            }
        }
        
        Boolean login = getPublicRequestParameter(request, "login") != null && getPublicRequestParameter(request, "login").equals("true");
        request.setAttribute("login", login);
        
        Boolean error = getPublicRequestParameter(request, "error") != null && getPublicRequestParameter(request, "error").equals("true");
        request.setAttribute("error", error);

    }
    
    private static Node getUserNode(Session session, HstRequest request) throws RepositoryException {
        String selectUserStatement = SELECT_USER_QUERY.replace("{}", request.getUserPrincipal().getName());
        @SuppressWarnings("deprecation")
        Query selectUserQuery = session.getWorkspace().getQueryManager().createQuery(selectUserStatement, Query.SQL);
        NodeIterator usersIterator = selectUserQuery.execute().getNodes();
        if (usersIterator.hasNext()) {
            return usersIterator.nextNode();
        }
        return null;
    }
    
    private static NodeIterator getGroupNodes(Session session, HstRequest request) throws RepositoryException {
        String selectGroupsStatement = SELECT_GROUPS_QUERY.replace("{}", request.getUserPrincipal().getName());
        @SuppressWarnings("deprecation")
        Query selectGroupsQuery = session.getWorkspace().getQueryManager().createQuery(selectGroupsStatement, Query.SQL);
        return selectGroupsQuery.execute().getNodes();
    }
    
    public static final class User {
        
        private String firstname = "";
        private String lastname = "";
        private Collection<String> groups;
        
        private User(Node userNode, NodeIterator groupNodes) throws RepositoryException {
            if (userNode.hasProperty("hipposys:firstname")) {
                firstname = userNode.getProperty("hipposys:firstname").getString();
            }
            if (userNode.hasProperty("hipposys:lastname")) {
                lastname = userNode.getProperty("hipposys:lastname").getString();
            }
            groups = new HashSet<String>();
            while (groupNodes.hasNext()) {
                groups.add(groupNodes.nextNode().getName());
            }
        }
        
        public String getFirstname() {
            return firstname;
        }
        
        public String getLastname() {
            return lastname;
        }
        
        public Collection<String> getGroups() {
            return groups;
        }
        
        public boolean isMember(String group) {
            return groups.contains(group);
        }
    }
}
