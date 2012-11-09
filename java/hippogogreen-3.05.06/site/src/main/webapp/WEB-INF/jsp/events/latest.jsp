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

<c:if test="${fn:length(items) gt 0}">
	<ul class="box-general box-event <c:if test="${preview}">editable</c:if>">
	    <c:forEach items="${items}" var="item">
	        <li class="title">
            <hst:cmseditlink hippobean="${item}"/>
            <a href="<hst:link hippobean="${item}"/>"><c:out value="${item.title}"/></a>
          </li>
	        <li class="month"><fmt:formatDate value="${item.date.time}" pattern="MMM"/></li>
	        <li class="day"><fmt:formatDate value="${item.date.time}" pattern="dd"/></li>
	        <li class="content"><c:out value="${item.summary}"/></li>
	    </c:forEach>
	    <li class="more">
	        <hst:link var="overview" siteMapItemRefId="events" />
	        <a href="${overview}"><fmt:message key="events.overview.title"/></a>
	    </li>
	</ul>
</c:if>