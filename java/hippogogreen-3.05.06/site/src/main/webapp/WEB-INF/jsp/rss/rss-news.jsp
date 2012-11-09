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
<%@ page pageEncoding="UTF-8" contentType="application/rss+xml; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.hippoecm.org/jsp/hst/core" prefix='hst'%>

<fmt:setLocale value="en_US"/>

<rss version="2.0">
   <channel>
      <title><fmt:message key="rss.news.title"/></title>
      <link>http://demopublic.onehippo.com/</link>
      <description><fmt:message key="rss.news.description"/></description>
      <language><fmt:message key="rss.news.language"/></language>
      <generator><fmt:message key="rss.news.generator"/></generator>
      <c:forEach var="item" items="${items}">
      <hst:link var="link" hippobean="${item}" fullyQualified="true" />
         <item>
            <title><c:out value="${item.title}" /></title>
            <link><c:out value="${link}"/></link>
            <description><c:out value="${item.summary}" /></description>
            <pubDate><fmt:formatDate value="${item.date.time}" pattern="EE, dd MMM yyyy HH:mm:ss Z"/></pubDate>
         </item>
      </c:forEach>
   </channel>
</rss>
