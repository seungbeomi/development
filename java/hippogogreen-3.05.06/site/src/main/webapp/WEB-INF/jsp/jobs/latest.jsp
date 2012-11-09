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
	<ul class="box-general <c:if test="${preview}">editable</c:if>">
	    <li class="title"><fmt:message key="jobs.latest.label"/></li>
	    <c:forEach var="item" items="${items}">
	        <li class="subtitle">
            <hst:cmseditlink hippobean="${item}"/>
            <a href="<hst:link hippobean="${item}"/>"><c:out value="${item.title}"/></a>
          </li>
	        <li class="content"><c:out value="${item.summary}"/></li>
	    </c:forEach>
	    <li class="more">
	      <hst:link var="allJobs" siteMapItemRefId="jobs" />
	      <a href="${allJobs}"><fmt:message key="jobs.latest.alljobs"/></a>
	    </li>
	</ul>
</c:if>