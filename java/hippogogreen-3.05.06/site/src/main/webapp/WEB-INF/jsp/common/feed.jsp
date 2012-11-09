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
<div class="display-feeds">
<c:if test="${not empty feeds}">
  <ul class="box-general">
    <c:if test="${not empty title}">
     <li class="title"><h2 class="go-green"><c:out value="${title}"/></h2>
      </li>
    </c:if>
    <c:forEach items="${feeds}" var="feed" varStatus="loop">
      <c:forEach items="${feed.entries}" var="entry" end="${entriesPerFeed - 1}">
        <li>
          <ul class="feed-item">
            <c:choose>
              <c:when test="${fn:contains(feedsUrls[loop.index],'twitter')}">
                <c:set var="twitterScreenName" value="${fn:substringAfter(feedsUrls[loop.index],'screen_name=')}"/>
                  <li class="image">
                  <img src="http://api.twitter.com/1/users/profile_image/twitter.json?size=normal&amp;screen_name=${twitterScreenName}"
                       alt="Twitter"/>
                  </li>
              </c:when>
              <c:when test="${fn:contains(feedsUrls[loop.index],'rss')}">
                  <li class="image rss"></li>
              </c:when>
              <c:otherwise>
                <li class="image">
                  <c:if test="${feed.image != null and feed.image.url != null}">
                    <img src="${fn:escapeXml(feed.image.url)}" alt="Twitter"/>
                  </c:if>
                </li>
              </c:otherwise>
            </c:choose>
            <li class="subtitle">
              <fmt:formatDate value="${entry.publishedDate}" type="date" pattern="MMM d, yyyy"/>
              <c:if test="${feed.author != null and not empty feed.author}"><c:out
                      value="- ${feed.author}"/></c:if>
            </li>
            <li class="content">
              <a href="${fn:escapeXml(entry.link)}"><c:out escapeXml="true" value="${entry.title}"/></a>
            </li>

          </ul>
        </li>
      </c:forEach>
    </c:forEach>
  </ul>
</c:if>
</div>