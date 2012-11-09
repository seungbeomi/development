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

<%--
<hst:headContribution>
    <hst:link var="filter" path="/js/mobile/filter.js"/>
    <script src="${filter}" type="text/javascript"></script>
</hst:headContribution>
--%>

<!-- search navigation -->
<ul id="filter-by">
    <li id="search">
        <%--<hst:link var="searchLink" hippobean="${facetnav}"/>--%>
        <c:set value="${fn:escapeXml(query)}" var="query"/>
        <form method="get" action="${searchLink}">
            <p>
                <input type="text" value="${query}" name="query" class="search-field gray" />
                <input type="submit" value="<fmt:message key="mobile.events.searchfacets.submit.label"/>" class="search-button" />
                <!-- Begin: Design for clear search--> 
                <hst:link path="/events/search"  var="removeLink" />
                <a class="clear" href="${removeLink}" title="<fmt:message key="mobile.events.searchfacets.clear"/> ${query}">&nbsp;</a>
                <!-- End: Design for clear search -->
            </p>
        </form>
    </li>

    <c:choose>
        <c:when test="${childNav}">
            <li class="expanded">
        </c:when>
        <c:otherwise>
            <li>
        </c:otherwise>
    </c:choose>    
        <span><span class="name"><c:out value="${facetnav.count}"/> <fmt:message key="mobile.events.searchfacets.results"/> <span class="separator">|</span></span><a href="#"><fmt:message key="mobile.events.searchfacets.filterresults"/></a></span>
        <c:if test="${facetnav.count gt 0}">
        
          <ul class="subfilter">          
              <c:forEach var="facetvalue" items="${facetnav.folders}">
                  <c:if test="${facetvalue.count > 0}">
                  
                      <c:set var="selectedItem" scope="page" value="${null}"/>
                      <c:forEach items="${facetvalue.folders}" var="item">
                          <c:if test="${item.leaf and item.count gt 0}">
                              <c:set var="selectedItem" scope="page" value="${item}"/>
                          </c:if>
                      </c:forEach>
  
                      <c:choose>
                          <c:when test="${selectedItem != null}">
                              <li class="clear">
                          </c:when>
                          <c:otherwise>
                              <li>
                          </c:otherwise>
                      </c:choose>    
                  
                      <span><span class="name"><c:out value="${facetvalue.name}"/>:</span>
  
                      <c:choose>
                          <c:when test="${selectedItem != null}">
                              <hst:facetnavigationlink remove="${selectedItem}" current="${facetnav}" var="removeLink"/>
                              <a class="filter" href="#">
                                          <c:out value="${selectedItem.name}"/>&nbsp;(<c:out value="${selectedItem.count}"/>)
                              </a></span>
                              <a class="clear" href="${removeLink}" title="<fmt:message key="mobile.events.searchfacets.clearfilter"/> ${selectedItem.name}">&nbsp;</a>
                          </c:when>
                          <c:otherwise>
                              <a href="#"><fmt:message key="mobile.events.searchfacets.any"/> <c:out value="${facetvalue.name}"/></a></span>
                              <ul class="subsubfilter">
                                  <c:forEach items="${facetvalue.folders}" var="item">
                                      <hst:link var="link" hippobean="${item}" navigationStateful="true">
                                          <hst:sitemapitem preferPath="mobile/events"/> 
                                      </hst:link>
                                      <li><a href="${link}"><c:out value="${item.name}"/>&nbsp;(${item.count})</a></li>
                                  </c:forEach>
                              </ul>
                          </c:otherwise>
                      </c:choose>    
  
                      </li>
  
                  </c:if>
              </c:forEach>        
        
          </ul>
        </c:if>
    </li>
    <li id="sort-by" class="last"><span><span class="name"><fmt:message key="mobile.events.searchfacets.sortby"/></span>
    <a href="#">
        <c:choose>
            <c:when test="${order != 'relevance'}"><fmt:message key="mobile.events.searchfacets.relevance"/></c:when>
            <c:when test="${order == 'title'}"><fmt:message key="mobile.events.searchfacets.name"/></c:when>
            <c:when test="${order == 'date'}"><fmt:message key="mobile.events.searchfacets.date"/></c:when>
            <c:when test="${order == '-lastModificationDate'}"><fmt:message key="mobile.events.searchfacets.mostrecent"/></c:when>
            <c:otherwise><fmt:message key="mobile.events.searchfacets.relevance"/></c:otherwise>
        </c:choose>
    </a>
    </span>
        <ul class="subsubfilter">
            <c:if test="${order != 'relevance'}"><li><a href="?query=${query}&amp;order=relevance"><fmt:message key="mobile.events.searchfacets.relevance"/></a></li></c:if>
            <c:if test="${order != 'title'}"><li><a href="?query=${query}&amp;order=title"><fmt:message key="mobile.events.searchfacets.name"/></a></li></c:if>
            <c:if test="${order != 'date'}"><li><a href="?query=${query}&amp;order=date"><fmt:message key="mobile.events.searchfacets.date"/></a></li></c:if>
            <c:if test="${order != '-lastModificationDate'}"><li><a href="?query=${query}&amp;order=-lastModificationDate"><fmt:message key="mobile.events.searchfacets.mostrecent"/></a></li></c:if>
        </ul>
    </li>
</ul>
