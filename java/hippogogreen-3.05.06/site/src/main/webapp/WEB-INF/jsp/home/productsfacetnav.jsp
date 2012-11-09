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
<div id="find-products">
    <h3><fmt:message key="home.products.findproduct"/></h3>
    <hst:link var="allProducts" siteMapItemRefId="products"/>
    <a class="all" href="${allProducts}"><fmt:message key="home.products.all.label"/></a>
    <c:if test="${not empty facetnav.folders}">
        <ul>
            <c:forEach var="facet" items="${facetnav.folders}">
                <li>
                        ${facet.name}
                    <c:if test="${not empty facet.folders}">
                        <ul class="bullet-points">
                            <c:forEach var="facetvalue" items="${facet.folders}">
                                <li class="text">
                                    <c:choose>
                                        <c:when test="${facetvalue.leaf}">
                                            <c:out value="${facetvalue.name}" escapeXml="true"/>
                                            (${facetvalue.count})
                                        </c:when>
                                        <c:otherwise>
                                            <hst:link var="link" hippobean="${facetvalue}"/>
                                            <a href="${link}"><c:out
                                                    value="${facetvalue.name}"/>&nbsp;(${facetvalue.count})</a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <div class="clear"></div>
</div>








