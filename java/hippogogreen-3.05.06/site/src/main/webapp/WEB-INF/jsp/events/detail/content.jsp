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
<%--@elvariable id="document" type="com.onehippo.gogreen.beans.EventDocument"--%>
<%@include file="../../includes/tags.jspf" %>

<!-- NOTE: Switch on the following variable if you want to eanble Inline Editing feature in this page. -->
<c:set var="inlineEditingEnabled" value="false" /> 

<c:if test="${preview}">
    <c:if test="${inlineEditingEnabled}">
        <jsp:include page="../../inc/inline-editing-head-contributions.jsp"/>
    </c:if>
</c:if>

<hippo-gogreen:title title="${document.title}"/>

<div class="yui-main">
    <div id="content" class="yui-b left-and-right <c:if test="${preview}"> editable</c:if>">

        <ol id="breadcrumbs">
            <li><fmt:message key="events.overview.location.label"/></li>
            <li>
                <hst:link var="home" siteMapItemRefId="home" />
                <a href="${home}"><fmt:message key="events.overview.location.home"/></a> &gt;
            </li>
            <li>
                <hst:link var="events" siteMapItemRefId="events" />
                <a href="${events}"><fmt:message key="events.overview.title"/></a> &gt;
            </li>
        </ol>

        <h2 class="title"><c:out value="${document.title}"/></h2>
        
        <div id="article" class="event">
            <hst:cmseditlink hippobean="${document}" />
            <c:set var="image" value="${document.firstImage}"/>
            <c:if test="${image != null}">
                <hst:link var="src" hippobean="${image.largeThumbnail}"/>
                <img class="image" src="${src}" alt="${fn:escapeXml(image.alt)}"/>
                <hippo-gogreen:imagecopyright image="${image}"/>
            </c:if>
            <span class="date">
              <fmt:formatDate value="${document.date.time}" pattern="MMM dd, yyyy"/>&nbsp;-&nbsp;
              <fmt:formatDate value="${document.endDate.time}" pattern="MMM dd, yyyy"/>
            </span>

            <div class="calendar">
                <span class="month"><fmt:formatDate value="${document.date.time}" pattern="MMM"/></span>
                <span class="day"><fmt:formatDate value="${document.date.time}" pattern="dd"/></span>
            </div>
            
            <div id="editable_cont" class="inline-editor-editable-container">
                <span class="<c:if test="${preview}">editable</c:if>" id="hippogogreen:summary"><c:out value="${document.summary}"/></span>
    
                <div class="yui-cssbase body">
                    <span class="<c:if test="${preview}">editable</c:if> inline" id="hippogogreen:description"><hst:html hippohtml="${document.description}"/></span>
                    <hippo-gogreen:copyright document="${document}"/>
                </div>
            </div>
            
            <ul id="document-actions">
                <li><a class="comment" href="#comment"><fmt:message key="events.overview.comment"/></a></li>
            </ul>
        </div>
        <c:if test="${not empty document.location}">
            <input id="address" type="hidden"
                   value="${document.location.street}&nbsp;${document.location.number},&nbsp;${document.location.city}&nbsp;${document.location.postalCode}&nbsp;${document.location.province}"/>
        </c:if>
        <hst:include ref="comments"/>

    </div>
</div>

<c:if test="${preview && inlineEditingEnabled}">
    <jsp:include page="../../inc/inline-editing-editor-form.jsp"/>
</c:if>
