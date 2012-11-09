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

<c:set var="newsoverviewtitle"><fmt:message key="news.overview.content.title"/></c:set>
<hippo-gogreen:title title="${newsoverviewtitle}"/>

<!-- content -->
<div class="yui-main">
    <div id="content" class="yui-b left-and-right">
        <c:if test="${news.total gt 0}">
          <!-- TODO use breadcrumbs plugin -->
          <ol id="breadcrumbs">
              <li><fmt:message key="news.overview.content.location.label"/></li>
              <li><a href="<hst:link siteMapItemRefId="home" />"><fmt:message key="news.overview.content.location.home"/></a> &gt; </li>
          </ol>

          <h2><fmt:message key="news.overview.content.title"/></h2>
        </c:if>
        <div id="news">
            <%--@elvariable id="news" type="java.util.List<com.onehippo.gogreen.beans.NewsItem>"--%>
            <c:forEach items="${news.items}" var="newsitem" varStatus="status">
                <ul class="news-item <c:if test="${preview}">editable</c:if>">
                    <hst:link var="link" hippobean="${newsitem}"/>
                    <li class="image">
                        <c:if test="${newsitem.firstImage != null}">
                            <hst:link var="src" hippobean="${newsitem.firstImage.smallThumbnail}"/>
                            <a href="${link}"><img src="${src}" alt="${fn:escapeXml(newsitem.firstImage.alt)}"/></a>
                        </c:if>
                    </li>
                    <li class="title">
                        <hst:cmseditlink hippobean="${newsitem}" />
                        <a href="${link}"><c:out value="${newsitem.title}"/></a>
                    </li>
                    <li class="date"><a href="${link}"><fmt:formatDate value="${newsitem.date.time}" type="date"
                                                                       pattern="MMM d, yyyy"/></a> |
                    </li>
                    <li class="comments"><a href="${link}">
                        <c:choose>
                            <c:when test="${commentsCountList[status.index] > 0}">
                                <c:out value="${commentsCountList[status.index]} "/><fmt:message key="news.overview.content.commentsfound"/><c:if test="${commentsCountList[status.index] gt 1}"><fmt:message key="news.overview.content.commentsplural"/></c:if>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="news.overview.content.nocomments"/>
                            </c:otherwise>
                        </c:choose>
                    </a></li>
                    <li class="description"><c:out value="${newsitem.summary}"/></li>
                </ul>
            </c:forEach>
        </div>
        <c:choose>
          <c:when test="${news.total eq 0}">
            <p id="results"><fmt:message key="search.results.noresults"/> '${query}'</p>
          </c:when>
          <c:otherwise>
            <hippo-gogreen:pagination pageableResult="${news}" queryName="query" queryValue="${query}"/>
          </c:otherwise>
        </c:choose>
    </div>
</div>
