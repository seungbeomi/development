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

<!-- NOTE: Switch on the following variable if you want to eanble Inline Editing feature in this page. -->
<c:set var="inlineEditingEnabled" value="false" /> 

<c:if test="${preview}">
    <c:if test="${inlineEditingEnabled}">
        <jsp:include page="../../inc/inline-editing-head-contributions.jsp"/>
    </c:if>
</c:if>

<hippo-gogreen:title title="${document.title}"/>

<!-- content -->
<div class="yui-main">
    <div id="content" class="yui-b left-and-right <c:if test="${preview}">editable</c:if>">

        <!-- TODO use breadcrumbs plugin -->
        <ol id="breadcrumbs">
            <li><fmt:message key="news.detail.content.location.label"/></li>
            <li>
              <hst:link var="home" siteMapItemRefId="home" />
              <a href="${home}"><fmt:message key="news.detail.content.location.home"/></a> &gt;
            </li>
            <li>
              <hst:link var="news" siteMapItemRefId="news" />
              <a href="${news}"><fmt:message key="news.detail.content.title"/></a> &gt;
            </li>
        </ol>

        <h2><c:out value="${document.title}"/></h2>
        
        <div id="article">
            <hst:cmseditlink hippobean="${document}" />
            <c:set var="image" value="${document.firstImage}"/>
            <c:if test="${image != null}">
                <hst:link var="src" hippobean="${image.largeThumbnail}"/>
                <img class="image" src="${fn:escapeXml(src)}" alt="${fn:escapeXml(image.alt)}"/>
                <hippo-gogreen:imagecopyright image="${image}"/>
            </c:if>
            <span class="date"><fmt:formatDate value="${document.date.time}" type="date" pattern="MMM d, yyyy"/></span>
            <span class="seperator"> | </span>
            <span class="comments"><a href="#">
                    <c:choose>
                        <c:when test="${commentCount gt 0}"><c:out value="${commentCount}"/> <fmt:message key="news.detail.content.commentsfound"/><c:if test="${commentCount gt 1}"><fmt:message key="news.detail.content.commentsplural"/></c:if></c:when>
                        <c:otherwise><fmt:message key="news.detail.content.nocomments"/></c:otherwise>
                    </c:choose>
                </a></span>

            <div id="editable_cont" class="inline-editor-editable-container">
                <span class="<c:if test="${preview}">editable</c:if>" id="hippogogreen:summary"><c:out value="${document.summary}"/></span>
                <div class="yui-cssbase body">
                    <span class="<c:if test="${preview}">editable</c:if> inline" id="hippogogreen:description"><hst:html hippohtml="${document.description}"/></span>
                    <hippo-gogreen:copyright document="${document}"/>
                </div>
            </div>
            
            <ul id="document-actions">
                <li><a class="comment" href="#comment"><fmt:message key="news.detail.content.comment"/></a></li>
            </ul>
        </div>

        <hst:include ref="comments"/>

    </div>
</div>

<c:if test="${preview && inlineEditingEnabled}">
    <jsp:include page="../../inc/inline-editing-editor-form.jsp"/>
</c:if>
