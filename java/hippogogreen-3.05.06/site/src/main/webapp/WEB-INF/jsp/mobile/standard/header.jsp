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

<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://www.hippoecm.org/jsp/hst/core" prefix='hst'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div id="hd">
  <!-- language navigation -->
  <hst:include ref="langnavigation"/>
  
  <div id="hd-main">

    <!-- logo -->
    <c:set var="lang" value="${pageContext.request.locale.language}"/>

    <hst:include ref="logo"/>

    <!-- navigation -->
    <ul id="nav">
        <c:forEach var="item" items="${menu.siteMenuItems}">
            <hst:link var="link" link="${item.hstLink}"/>
            <c:choose >
                <c:when test="${item.expanded}">
                    <li class="active">
                        <a href="${link}"><c:out value="${item.name}"/></a>
                        <ul>
                            <c:choose>
                                <c:when test="${detailPage}">
                                    <li class="back">
                                        <a href="${link}" onclick="javascript:history.back(); return false;"><c:out value="${item.name}"/></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${item.childMenuItems}" var="childItem">
                                        <hst:link var="childLink" link="${childItem.hstLink}"/>
                                        <c:choose>
                                            <c:when test="${childItem.expanded or (menu.deepestExpandedItem == item and childItem == item.childMenuItems[0])}">
                                                <li class="active">
                                                    <a href="${childLink}"><c:out value="${childItem.name}"/></a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li>
                                                    <a href="${childLink}"><c:out value="${childItem.name}"/></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                       <a href="${link}"><c:out value="${item.name}"/></a>
                    </li>
                </c:otherwise>
            </c:choose>

        </c:forEach>

        <%--            
        <li<c:if test="${param.section eq 'products'}"> class="active"</c:if>>
          <a href="products.jsp"><fmt:message key="mobile.standard.header.productslink"/></a>
          <ul>
            <c:choose>
            <c:when test="${param.page eq 'detail'}">
              <li class="back">
                <a href="products.jsp" onclick="javascript:history.back(); return false;"><fmt:message key="mobile.standard.header.productslink"/></a></li>
            </c:when>
            <c:otherwise>
              <li<c:if test="${param.page eq 'popular'}"> class="active"</c:if>>
                <a href="products.jsp"><fmt:message key="mobile.standard.header.popular"/></a></li>
              <li<c:if test="${param.page eq 'recent'}"> class="active"</c:if>>
                <a href="?page=recent"><fmt:message key="mobile.standard.header.recent"/></a></li>
              <li<c:if test="${param.page eq 'search'}"> class="active"</c:if>>
                <a href="products-search.jsp"><fmt:message key="mobile.standard.header.search"/></a></li>
            </c:otherwise>
            </c:choose>
          </ul>
        </li>
        <li<c:if test="${param.section eq 'events'}"> class="active"</c:if>>
          <a href="events.jsp"><fmt:message key="mobile.standard.header.events"/></a>
          <ul>
            <c:choose>
            <c:when test="${param.page eq 'detail'}">
              <li class="back">
                <a href="events.jsp" onclick="javascript:history.back(); return false;">Events</a></li>
            </c:when>
            <c:otherwise>
              <li<c:if test="${param.page eq 'upcoming'}"> class="active"</c:if>>
                <a href="events.jsp"><fmt:message key="mobile.standard.header.eventsupcoming"/></a></li>
              <li<c:if test="${param.page eq 'past'}"> class="active"</c:if>>
                <a href="?page=recent"><fmt:message key="mobile.standard.header.eventspast"/></a></li>
              <li<c:if test="${param.page eq 'search'}"> class="active"</c:if>>
                <a href="events-search.jsp"><fmt:message key="mobile.standard.header.search"/></a></li>
            </c:otherwise>
            </c:choose>
          </ul>
        </li>
        --%>
    </ul>
  </div>
</div>
