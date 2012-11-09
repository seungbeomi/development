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
<div id="right" class="yui-b">
  <ul class="box-general" id="filter">
      <li class="title"><fmt:message key="products.facetnav.title"/></li>
      <li class="text">
          <ul>
              <hst:link var="searchLink" hippobean="${facetnav}"/>
              <li class="filter-by"><fmt:message key="products.facetnav.freetext"/>
                  <c:set var="formId"><hst:namespace/>facetnavsearch</c:set>
                  <form id="${formId}" method="get" action="${searchLink}" class="facetsearch" onsubmit="sanitizeRequestParam(document.forms['${formId}']['query'])">
                      <p>
                         <input type="text" value="${query}" name="query" class="facetsearch-field" />
                         <input type="submit" class="facetsearch-button" value="<fmt:message key="products.facetnav.submit.label"/>" />
                      </p>
                  </form>
              </li>
              <c:forEach var="facetvalue" items="${facetnav.folders}">
                  <c:if test="${facetvalue.count >0}">
                      <c:choose>
                          <c:when test="${facetvalue.leaf}">
                              <li class="filter-by"><c:out value="${facetvalue.name}"/>
                                  <ul class="bullet-points">
                                      <li><a class="filter" href="#"><c:out value="${facetvalue.name}"/></a></li>
                                  </ul>
                              </li>
                          </c:when>
                          <c:when test="${facet.childCountsCombined eq 0}">
                          </c:when>
                          <c:when test="${not empty facetvalue.folders}">
                              <li class="filter-by"><c:out value="${facetvalue.name}"/>:
                                  <ul class="bullet-points">
                                      <c:forEach items="${facetvalue.folders}" var="item">
                                          <c:choose>
                                              <c:when test="${item.leaf and item.count gt 0}">
                                                  <hst:facetnavigationlink remove="${item}" current="${facetnav}"
                                                                           var="removeLink"/>
                                                  <li><a class="filter" href="${removeLink}"><c:out value="${item.name}"/></a></li>
                                              </c:when>
                                              <c:when test="${item.leaf and item.count eq 0}">
                                              </c:when>
                                              <c:otherwise>
                                                  <hst:link var="link" hippobean="${item}" navigationStateful="true"/>
                                                  <li><a href="${link}"><c:out value="${item.name}"/></a>&nbsp;(${item.count})</li>
                                              </c:otherwise>
                                          </c:choose>
                                      </c:forEach>
                                  </ul>
                              </li>
                          </c:when>
                      </c:choose>
                  </c:if>
              </c:forEach>
          </ul>
      </li>
  </ul>

  <hst:include ref="suggestproduct"/>
  <!-- components container -->
  <hst:include ref="boxes-right"/>
</div>
