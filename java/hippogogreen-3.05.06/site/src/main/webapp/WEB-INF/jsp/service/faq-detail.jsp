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

<hippo-gogreen:title title="${document.question}"/>

<div class="yui-main">
  <div id="content" class="yui-b left-and-right">

    <ul id="breadcrumbs">
      <li><fmt:message key="service.faqdetail.location.label"/></li>
      <hst:link var="homelink" siteMapItemRefId="home" />
      <li><a href="${homelink}"><fmt:message key="service.faqdetail.location.home"/></a> &gt;</li>
      <hst:link var="faqlink" path="/faq"/>
      <li><a href="${faqlink}"><fmt:message key="service.faqdetail.location.faq"/></a> &gt;</li>
    </ul>

    <div class="editable">
      <hst:cmseditlink hippobean="${document}" />

      <h2 class="title"><c:out value="${document.question}"/></h2>
    
      <div id="faq" class="yui-cssbase body question">
        <hst:html hippohtml="${document.answer}"/>
        <hippo-gogreen:copyright document="${document}"/>
      </div>
    </div>
  </div>
</div>
