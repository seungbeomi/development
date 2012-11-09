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

<!-- right -->
<div id="right" class="yui-b">
  <ul class="box-general" id="search-options">
    <li class="title"><fmt:message key="search.errorright.title"/></li>
    <li class="text">
      <p><fmt:message key="search.errorright.checkaddress"/></p>
      <span><fmt:message key="search.errorright.goto"/></span>
      <ul class="bullet-points">
        <hst:link var="home" siteMapItemRefId="home" />
        <li><a href="${home}"><fmt:message key="search.errorright.homepagelink"/></a></li>
        <c:if test="${parentpage ne '' and parentpage ne 'home'}" >
          <li><a href="<hst:link path="${parentpage}"/>"><fmt:message key="search.errorright.parentlink"/></li>
        </c:if>
        <hst:link var="sitemap" siteMapItemRefId="sitemap" />
        <li><a href="${sitemap}"><fmt:message key="search.errorright.sitemaplink"/></a></li>
      </ul>
      
      <p><fmt:message key="search.errorright.startsearch"/></p>
      <hippo-gogreen:search-form/>
    </li>
  </ul>
</div>
