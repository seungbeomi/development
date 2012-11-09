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

<c:set var="resttitle"><fmt:message key="restapi.documentation.title"/></c:set>

<hippo-gogreen:title title="${resttitle}"/>

<c:set var="datePattern" value="dd-MM-yyyy"/>
<div class="yui-main">
  <div id="content" class="yui-b left-and-right">
    <div>
      <ol id="breadcrumbs">
        <li><fmt:message key="restapi.main.location.label"/></li>
        <li>
          <hst:link var="home" siteMapItemRefId="home" />
          <a href="${home}"><fmt:message key="restapi.main.location.home"/></a> &gt;
        </li>
        <li>
          <hst:link var="rest" siteMapItemRefId="restapi" />
          <a href="${rest}"><fmt:message key="restapi.main.location.rest"/></a> &gt;
        </li>
        <li>
          <hst:link var="docs" siteMapItemRefId="restapi-documentation" />
          <a href="${rest}"><fmt:message key="restapi.main.location.docs"/></a> &gt;
        </li>
      </ol>
    </div>

    <div id="restapidoc">
      <h2 class="title"><c:out value="${text.title}"/></h2>
      <p><c:out value="${text.summary}"/></p>
      <c:if test="${not empty documents}">
        <c:forEach items="${documents}" var="document">
          <ul class="restapidoc-item <c:if test="${preview}">editable</c:if>">
            <hst:cmseditlink hippobean="${document}" />
            <hst:link var="link" hippobean="${document}"/>
            <li class="title"><a href="${link}"><c:out value="${document.apiPath}"/></a></li>
            <li class="description"><c:out value="${document.summary}"/></li>
          </ul>
        </c:forEach>
      </c:if>
    </div>

  </div>
</div>