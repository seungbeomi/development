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


<div id="left" class="yui-b">
  <c:if test="${fn:length(facets) gt 0}">
    <ul id="left-nav">
        <%--@elvariable id="facets" type="java.util.List<org.hippoecm.hst.content.beans.standard.HippoFolder>"--%>
        <c:forEach items="${facets}" var="facet">
            <li><a href="<hst:link hippobean="${facet}"/>"><c:out value="${facet.name}"/></a></li>
        </c:forEach>
    </ul>
  </c:if>
  <!-- components container -->
  <hst:include ref="boxes-left"/>
</div>

