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

<hippo-gogreen:title title="${document.title}"/>

<!-- body / main -->
<div id="bd">

    <div id="content" class="detail">
        <h1><c:out value="${document.title}"/></h1>
        <p id="event-info" class="doc-info">
            <span class="location"><c:out value="${document.location.street}"/>&nbsp;<c:out value="${document.location.number}"/>,&nbsp;<c:out value="${document.location.city}"/>&nbsp;<c:out value="${document.location.postalCode}"/>&nbsp;<c:out value="${document.location.province}"/></span>
            <span class="date"><fmt:formatDate value="${document.date.time}" pattern="MMM dd, yyyy"/></span>
        </p>
        
        <div class="calendar">
            <span class="month"><fmt:formatDate value="${document.date.time}" pattern="MMM"/></span>
            <span class="day"><fmt:formatDate value="${document.date.time}" pattern="dd"/></span>
        </div>
        
        <p class="summary"><c:out value="${document.summary}"/></p>
        <div class="description">
            <hst:html hippohtml="${document.description}"/>
        </div>
        <p id="event-extra"><span><fmt:message key="mobile.events.detail.vieweventlabel"/></span>
            <c:url var="url" value="http://maps.google.com/?q=${document.location.street} ${document.location.number}, ${document.location.city} ${document.location.postalCode} ${document.location.province}"/>
            <a href="${fn:escapeXml(url)}"><img src="<hst:link path="/images/mobile/logo-gmaps.png"/>" alt="<fmt:message key="mobile.events.detail.ingoogle"/>" /></a>
            <%--<a href="#view-json-xml"><img src="<hst:link path="/images/mobile/logo-layar.png"/>" alt="<fmt:message key="mobile.events.detail.inlayar"/>"/></a>--%>
        </p>
    </div>

</div>