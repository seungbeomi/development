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

<c:if test="${documents != null and not empty documents}">
    <ul class="box-general bullet-points">
        <li class="title"><c:out value="${headline}"/></li>
        <c:forEach items="${documents}" var="doc">
            <hst:link var="link" hippobean="${doc}"/>
            <li class="link"><a href="${link}"><c:out value="${doc.title}"/></a></li>
        </c:forEach>
    </ul>
</c:if>
