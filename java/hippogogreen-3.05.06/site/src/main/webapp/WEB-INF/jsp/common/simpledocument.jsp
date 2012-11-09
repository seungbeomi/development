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
<%@include file="../includes/tags.jspf" %>

<c:choose>
  <c:when test="${not empty document}">
    <ul class="box-general box-simple <c:if test="${preview}">editable</c:if>">
      <c:if test="${preview}">
        <li><hst:cmseditlink hippobean="${document}"/></li>
      </c:if>
      <li class="title"><c:out value="${document.title}"/></li>
      <li class="subtitle"><c:out value="${document.summary}"/></li>
      <li class="content"><hst:html hippohtml="${document.description}"/></li>
    </ul>
  </c:when>
  <c:otherwise>
    <ul class="box-general">
      <li class="title"><fmt:message key="common.simpledocument.nodocfound"/></li>
    </ul>
  </c:otherwise>
</c:choose>
