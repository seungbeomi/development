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

<c:set var="abouttitle"><fmt:message key="about.title"/></c:set>
<hippo-gogreen:title title="${abouttitle}"/>

<div class="yui-main">
<div id="content" class="yui-b left-and-right">

  <c:choose>
    <c:when test="${documents ne null}">         
      <div id="about">
        <h2 class="title"><c:out value="${folderName}"/></h2>
        <c:forEach items="${documents}" var="document">
          <ul class="about-item <c:if test="${preview}">editable</c:if>">
            <hst:cmseditlink hippobean="${document}" />
            <hst:link var="link" hippobean="${document}"/>
            <li class="title"><a href="${link}"><c:out value="${document.title}"/></a></li>
            <li class="description"><c:out value="${document.summary}"/></li>
          </ul>
        </c:forEach>
      </div>
    </c:when>
    <c:when test="${document ne null}">
      <div id="article" class="about <c:if test="${preview}">editable</c:if>">
        <hst:cmseditlink hippobean="${document}" />
        <h2 class="title"><c:out value="${document.title}"/></h2>
        
        <p class="intro"><c:out value="${document.summary}"/></p>
               
        <div class="yui-cssbase body">
          <hst:html hippohtml="${document.description}"/>
        </div>
      </div>
    </c:when>
  </c:choose>
 
</div>
</div>
