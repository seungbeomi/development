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
<ul class="box-general" id="tagcloud">
  <li class="title"><c:out value="${tagcloud.title}"/></li>
  <li class="content">
  <c:forEach var="tag" items="${tagcloud.tags}">
	<c:choose>
		<c:when test="${tag.type eq 'TCMP_CUSTOMTAG'}">
			<c:choose>
				<c:when test="${tag.external}">
					<a href="${tag.url}" style="${tag.style}"><c:out value="${tag.label}"/></a>
				</c:when>
				<c:when test="${not empty tag.oneBean}">
					<a href="<hst:link hippobean="${tag.oneBean}" />" style="${tag.style}"><c:out value="${tag.label}"/></a>
				</c:when>
				<c:otherwise>
					<a href="<hst:link hippobean="${tag.bean}" />" style="${tag.style}"><c:out value="${tag.label}"/></a>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<a href="<hst:link hippobean="${tag.bean}" />" style="${tag.style}"><c:out value="${tag.label}"/></a>
		</c:otherwise>
	</c:choose>
	</c:forEach>
  </li>
</ul>
