<%--

    Copyright (C) 2010 Hippo B.V.

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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://www.hippoecm.org/jsp/hst/core" prefix='hst' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="requestLanguage" value="standard.header.langnav.language.${pageContext.request.locale.language}"/>

<ul id="language">
  <li class="active">
    <fmt:message key="standard.header.langnav.language" />:
    <fmt:message key="${requestLanguage}"/>
    <span><a href="#"><fmt:message key="mobile.standard.header.edition.change"/></a></span>
  </li>
  <c:forEach var="translation" items="${translations}">
    <c:set var="title"><fmt:message key="standard.header.langnav.language.${translation.language}"/></c:set>
    <hst:link var="link" link="${translation.link}"/>
    <c:choose>
      <c:when test="${not translation.available}">
        <li class="unavailable"><a href="${link}"><c:out value="${title}"/>*</a></li>
      </c:when>
      <c:otherwise>
        <li><a href="${link}"><c:out value="${title}"/></a></li>
      </c:otherwise>
    </c:choose>
  </c:forEach>
</ul>