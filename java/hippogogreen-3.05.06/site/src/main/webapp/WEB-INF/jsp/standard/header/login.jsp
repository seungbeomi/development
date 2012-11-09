<%--

    Copyright (C) 2010-2011 Hippo B.V.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>

<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://www.hippoecm.org/jsp/hst/core" prefix='hst'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- login -->
<c:choose>
  <c:when test="${loggedin}">
    <div id="login">
      <span class="username"><fmt:message key="standard.header.login.welcome"/>, ${user.firstname}&nbsp;${user.lastname}</span>
      <hst:link var="logoutLink" path="/login/logout" />
      <span class="first"><a class="black" href="${logoutLink}"><fmt:message key="standard.header.login.logoutlink"/></a></span>
    </div>
  </c:when>
  <c:when test="${login}">
    <hst:link var="destination" path="<%=request.getPathInfo() %>"/>
    <hst:link var="loginLink" path="/login/proxy" />
    <div id="login-form">
      <form action="${loginLink}" method="post">
        <input class="login-field gray" type="text" name="username" value="<fmt:message key="standard.header.login.input.username"/>" onclick="this.value=''; this.className='login-field'" />
        <input class="login-field gray" type="password" name="password" value="<fmt:message key="standard.header.login.input.password"/>" onclick="this.value=''; this.className='login-field'" />
        <input type="hidden" name="destination" value="${destination}" />
        <input type="submit" value="<fmt:message key="standard.header.login.submit.label"/>" id="login-button" />
      </form>
      <p>
        <a class="minimize" href="?login=false">&nbsp;</a>
        <c:if test="${error}">
          <span><fmt:message key="standard.header.login.error"/></span>
        </c:if>
      </p>
    </div>
  </c:when>
  <c:otherwise>
    <div id="login">
      <span class="first"><a href="?login=true"><fmt:message key="standard.header.login.loginlink"/></a></span>
    </div>
  </c:otherwise>
</c:choose>
