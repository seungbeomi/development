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
	<ul class="box-general">
	  <li class="title"><fmt:message key="comments.latest.label"/></li>
	    <%--@elvariable id="items" type="java.util.List<com.onehippo.gogreen.beans.Comment>"--%>
	  <c:forEach items="${items}" var="item">
	    <li class="link <c:if test="${preview}">editable</c:if>">
	      <hst:cmseditlink hippobean="${item}" />
	      <hst:link var="link" hippobean="${item.document}"/>
	      <a href="${link}"><c:out value="${item.document.title}"/></a>
	      <c:out value="${item.name}"/>: <c:out value="${item.body}"/>
	    </li>
	  </c:forEach>
	</ul>
</c:if>