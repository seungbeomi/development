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

<ul>
  <c:forEach var="item" items="${menu.siteMenuItems}">
    <hst:link var="link" link="${item.hstLink}" />
    <li><a href="${fn:escapeXml(link)}"><c:out value="${item.name}"/></a></li>
  </c:forEach>
</ul>
