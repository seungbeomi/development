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

<%@include file="../includes/tags.jspf"%>

<c:set var="servicesitemaptitle"><fmt:message key="service.sitemapmain.title"/></c:set>
<hippo-gogreen:title title="${servicesitemaptitle}"/>

<div class="yui-main">
<div id="content" class="yui-b left-and-right">

<ul id="breadcrumbs">
  <li><fmt:message key="service.sitemapmain.location.label"/></li>
  <li>
    <hst:link var="homelink" siteMapItemRefId="home" />
    <a href="${homelink}"><fmt:message key="service.sitemapmain.location.home"/></a> &gt;</li>
</ul>
        
<h2 class="title"><fmt:message key="service.sitemapmain.title"/></h2>

<ul id="sitemap">
  <hst:include ref="mainmenu"/>
  <hst:include ref="servicemenu"/>
</ul>
  
</div>
</div>
