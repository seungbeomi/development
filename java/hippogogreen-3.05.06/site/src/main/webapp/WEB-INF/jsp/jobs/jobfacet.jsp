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
<div class="yui-b" id="right">
    <ul id="filter" class="box-general">
        <li class="title"><fmt:message key="jobs.jobfacet.refinesearch"/></li>
        <li class="text">
            <ul>
                <hst:link var="searchLink" hippobean="${facetnav}"/>
                <li class="filter-by"><fmt:message key="jobs.jobfacet.freetext"/>
                  <c:set var="formId"><hst:namespace/>facetnavsearch</c:set>
                  <form id="${formId}" method="get" action="${searchLink}" class="facetsearch" onsubmit="sanitizeRequestParam(document.forms['${formId}']['query'])">
                    <p>
                      <input type="text" value="${query}" name="query" class="facetsearch-field" />
                      <input type="submit" class="facetsearch-button" value="<fmt:message key="jobs.jobfacet.submit.label"/>" />
                    </p>
                  </form>
                </li>
                <c:forEach var="facetvalue" items="${facetnav.folders}" varStatus="loop">
                    <c:if test="${facetnav.count >0}">
                        <c:choose>
                            <%-- Adding custom check for SDEMO-141 --%>
                            <c:when test="${facetvalue.name eq 'employer'}">
                            </c:when>
                            <c:when test="${facetvalue.leaf}">
                                <li class="filter-by"><c:out value="${facetvalue.name}"/>
                                    <ul class="bullet-points">
                                        <li><a class="filter" href="#"><c:out value="${facetvalue.name}"/></a></li>
                                    </ul>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="filter-by"><c:out value="${facetvalue.name}"/>:

                                  <c:if test="${not empty facetvalue.folders}">
                                    <ul class="bullet-points">
                                      <c:forEach items="${facetvalue.folders}" var="item">
                                        <li>
                                          <c:choose>
                                            <c:when test="${item.leaf and item.count gt 0}">
                                              <hst:facetnavigationlink remove="${item}" current="${facetnav}" var="removeLink"/>
                                              <a class="filter" href="${fn:escapeXml(removeLink)}"><c:out value="${item.name}"/></a>
                                            </c:when>
                                            <c:when test="${item.leaf and item.count eq 0}">
                                            </c:when>
                                            <c:otherwise>
                                              <hst:link var="link" hippobean="${item}" navigationStateful="true" />
                                              <a href="${fn:escapeXml(link)}"><c:out value="${item.name}"/></a>&nbsp;(${item.count})
                                            </c:otherwise>
                                          </c:choose>
                                        </li>
                                      </c:forEach>
                                    </ul>
                                  </c:if>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </ul>
        </li>
    </ul>
  <!-- components container -->
  <hst:include ref="boxes-right"/>
</div>

