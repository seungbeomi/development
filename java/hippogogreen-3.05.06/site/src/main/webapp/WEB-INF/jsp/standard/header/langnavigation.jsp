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

<%@include file="../../includes/tags.jspf" %>

<c:set var="requestLanguage" value="standard.header.langnav.language.${pageContext.request.locale.language}"/>

<!-- lang navigation -->
<ul id="language">
  <li class="label">
  	<fmt:message key="standard.header.langnav.language" />:
  </li>
  <li class="active">
    <fmt:message key="${requestLanguage}"/>
  </li>
  
  <c:forEach var="translation" items="${translations}" varStatus="status">
    <hst:link var="link" link="${translation.link}" />
    <c:set var="title"><fmt:message key="standard.header.langnav.language.${translation.language}"/></c:set>
    <li ${status.last ? 'class="last"' : ''}>
      <a href="${link}"><c:out value="${title}"/></a>
    </li>
  </c:forEach>
</ul>