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
<c:set var="datePattern" value="dd-MM-yyyy"/>

<!-- NOTE: Switch on the following variable if you want to eanble Inline Editing feature in this page. -->
<c:set var="inlineEditingEnabled" value="false" /> 

<c:if test="${preview}">
    <c:if test="${inlineEditingEnabled}">
        <jsp:include page="../inc/inline-editing-head-contributions.jsp"/>
    </c:if>
</c:if>
<hippo-gogreen:title title="${document.title}"/>

<div class="yui-main">
<div id="content" class="right more-padding-left <c:if test="${preview}">editable</c:if>">
<hst:cmseditlink hippobean="${document}" />
<ol id="breadcrumbs">
	<li><fmt:message key="jobs.detail.location.label"/></li>
	<li>
      <hst:link var="home" siteMapItemRefId="home" />
      <a href="${home}"><fmt:message key="jobs.detail.location.home"/></a> &gt;
    </li>
	<li>
      <hst:link var="jobs" siteMapItemRefId="jobs" />
      <a href="${jobs}"><fmt:message key="jobs.detail.jobs"/></a> &gt;
    </li>
</ol>

<h2><c:out value="${document.title}"/></h2>

<div id="job">

  <ul>
  <li class="question"><fmt:message key="jobs.detail.employer"/> <span class="answer"><c:out value="${document.employer}"/></span></li>
  <li class="question"><fmt:message key="jobs.detail.location"/> <span class="answer"><c:out value="${document.location}"/></span></li>
  <li class="question"><fmt:message key="jobs.detail.placementdate"/> <span class="answer"> <fmt:formatDate pattern="${datePattern}" value="${document.closingDate}"/></span></li>
  </ul>

  <h3><fmt:message key="jobs.detail.jobdescription"/></h3>
  
  <div id="editable_cont" class="inline-editor-editable-container">
    <div class="yui-cssbase body">
      <span class="<c:if test="${preview}">editable</c:if> inline" id="hippogogreen:description"><hst:html hippohtml="${document.description}"/></span>
      <hippo-gogreen:copyright document="${document}"/>
    </div>
  </div>
  
</div>

</div>
</div>
<div id="right" class="yui-b">
<ul class="box-general bullet-points" id="job-search">
	<li class="title"><fmt:message key="jobs.detail.jobsearch"/></li>
	<li class="link"><a href="javascript:history.back(-1)"><fmt:message key="jobs.detail.backtoresults"/></a></li>
</ul>
<ul id="actions" class="box-general bullet-points">
    <li class="title"><fmt:message key="jobs.detail.actions"/></li>
    <li class="link"><a href="<hst:link path="jobs/faceted/employer/${document.employer}"/>"><fmt:message key="jobs.detail.allemployerjobs"/></a></li>
</ul>
<hippo-gogreen:share-block />

<c:if test="${preview && inlineEditingEnabled}">
    <jsp:include page="../inc/inline-editing-editor-form.jsp"/>
</c:if>

</div>
