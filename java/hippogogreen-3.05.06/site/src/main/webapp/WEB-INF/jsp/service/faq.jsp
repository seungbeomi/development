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

<c:set var="servicefaqtitle"><fmt:message key="service.faq.title"/></c:set>
<hippo-gogreen:title title="${servicefaqtitle}"/>

<div class="yui-main">
<div id="content" class="yui-b left-and-right">

  <ul id="breadcrumbs">
    <li><fmt:message key="service.faq.location.label"/></li>
    <li>
      <hst:link var="home" siteMapItemRefId="home" />
      <a href="${home}"><fmt:message key="service.faq.location.home"/></a> &gt;
    </li>
  </ul>
        
  <h2 class="title"><fmt:message key="service.faq.title"/></h2>
        
  <div id="faq">
    <ul id="questions">
      <c:forEach items="${documents}" var="document">
        <li class="question <c:if test="${preview}">editable</c:if>">
          <hst:cmseditlink hippobean="${document}"/>
          <a class="heading"><c:out value="${document.question}"/></a>
          <div class="yui-cssbase body">
            <hst:html hippohtml="${document.answer}"/>
            <hippo-gogreen:copyright document="${document}"/>
          </div>
        </li>
      </c:forEach>
    </ul>
  </div>
 
</div>
</div>
      
