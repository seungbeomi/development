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

<fmt:setBundle basename="typenames" var="types"/>
<ul class="box-general" id="filter">
  <li class="title"><fmt:message key="search.facetnav.title"/></li>
  <li class="text">
    <ul>

      <hst:link var="searchLink" siteMapItemRefId="search"/>
      <li class="filter-by"><fmt:message key="search.facetnav.freetext"/>
        <c:set var="formId"><hst:namespace/>facetnavsearch</c:set>
        <form id="${formId}" method="get" action="${searchLink}" class="facetsearch" onsubmit="sanitizeRequestParam(document.forms['${formId}']['query'])">
          <p>
            <input type="text" value="${fn:escapeXml(query)}" name="query" class="facetsearch-field"/>
            <input type="submit" id="suggest-button" value="<fmt:message key="search.facetnav.submit.label"/>"/>
          </p>
        </form>
      </li>

      <c:forEach var="facet" items="${facetnav.folders}">
        <c:if test="${facet.count > 0}">
          <c:choose>
            <c:when test="${facet.leaf}">
              <li class="filter-by"><c:out value="${facet.name}"/>
                <ul class="bullet-points">
                  <li><a class="filter" href="#"><c:out value="${facet.name}"/></a></li>
                </ul>
              </li>
            </c:when>
            <c:when test="${facet.childCountsCombined eq 0}">
            </c:when>
            <c:otherwise>
              <li class="filter-by"><c:out value="${facet.name}"/>:
                <c:if test="${not empty facet.folders}">
                  <ul class="bullet-points">
                    <c:forEach items="${facet.folders}" var="item">
                      <c:choose>
                        <c:when test="${item.leaf and item.count gt 0}">
                          <hst:facetnavigationlink remove="${item}" current="${facetnav}" var="removeLink"/>
                          <li>
                            <a class="filter" href="${removeLink}">
                              <c:set var="undefined" value="???${item.name}???"/>
                              <fmt:message bundle="${types}" key="${item.name}" var="label"/>
                              <c:choose>
                                <c:when test="${label == undefined}"><c:out value="${item.name}"/></c:when>
                                <c:otherwise><c:out value="${label}"/></c:otherwise>
                              </c:choose>
                            </a>
                          </li>
                        </c:when>
                        <c:when test="${item.leaf and item.count eq 0}">
                        </c:when>
                        <c:otherwise>
                          <hst:link var="link" hippobean="${item}" navigationStateful="true"/>
                          <li>
                            <a href="${link}">
                              <c:set var="undefined" value="???${item.name}???"/>
                              <fmt:message bundle="${types}" key="${item.name}" var="label"/>
                              <c:choose>
                                <c:when test="${label == undefined}"><c:out value="${item.name}"/></c:when>
                                <c:otherwise><c:out value="${label}"/></c:otherwise>
                              </c:choose>
                            </a>&nbsp;(${item.count})
                          </li>
                        </c:otherwise>
                      </c:choose>
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
