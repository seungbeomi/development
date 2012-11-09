<%--

    Copyright (C) 2010-2011 Hippo B.V.

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

<hst:defineObjects/>

<!-- search navigation -->
<ul id="filter-by">
  <li id="search">
    <hst:link var="searchLink" hippobean="${facetnav}"/>
    <form id="searchform" method="get" action="${searchLink}">
      <p>
        <!--
            Let's enable x-webkit-speech so we can use Webkit's Speech recognition in this input field
            Currently works only in Chrome
        -->
        <input type="text" value="${query}" name="query" id="query" class="search-field gray" x-webkit-speech=""/>
        <fmt:message key="mobile.products.searchfacets.submit.label" var="submitValue"/>
        <input type="submit" value="${submitValue}" class="search-button"/>
        <!-- Begin: Design for clear search-->
        <hst:link var="removeLink" path="${hstRequest.requestContext.resolvedSiteMapItem.pathInfo}"/>
        <c:set var="removeTitle"><fmt:message key="mobile.products.searchfacets.clear"/><c:out value=" ${query}"/></c:set>
        <a class="clear" href="${removeLink}" title="${removeTitle}">&nbsp;</a>
        <!-- End: Design for clear search -->
      </p>
    </form>
  </li>

  <script type="text/javascript">
    // When speech input is ready, select result number 1 and submit the form
    function inputChange(e) {
      if (e.results) { // e.type == 'webkitspeechchange'
        for (var i = 0, result; result = e.results[i]; ++i) {
          console.log(result.utterance, result.confidence);
        }
        console.log('Best result: ' + this.value);
        // Put in the value manually because somehow Chrome doesn't clear the field automatically
        query.value = e.results[0].utterance;
        searchform.submit();
      }
    }

    var input = document.querySelector('[x-webkit-speech]');
    input.addEventListener('change', inputChange, false);
    input.addEventListener('webkitspeechchange', inputChange, false);
  </script>

  <li${childNav ? ' class="expanded"' : ''}>

    <span><span class="name"><c:out value="${facetnav.count} "/><fmt:message key="mobile.products.searchfacets.results"/><span class="separator"> | </span></span><a href="#"><fmt:message key="mobile.products.searchfacets.filterresults"/></a></span>


    <c:if test="${fn:length(facetnav.folders) gt 0}">
      <ul class="subfilter">
        <c:forEach var="facetvalue" items="${facetnav.folders}">
          <c:if test="${facetvalue.count > 0}">

            <c:set var="selectedItem" scope="page" value="${null}"/>
            <c:forEach items="${facetvalue.folders}" var="item">
              <c:if test="${item.leaf and item.count gt 0}">
                <c:set var="selectedItem" scope="page" value="${item}"/>
              </c:if>
            </c:forEach>

            <li${selectedItem ne null ? ' class="clear"' : ''}>

              <span><span class="name"><c:out value="${facetvalue.name}"/>:</span>

              <c:choose>
                <c:when test="${selectedItem ne null}">
                  <hst:facetnavigationlink remove="${selectedItem}" current="${facetnav}" var="removeLink"/>
                  <a class="filter" href="#">
                    <c:out value="${selectedItem.name}"/>&nbsp;(<c:out value="${selectedItem.count}"/>)
                  </a></span>
                  <c:set var="clearTitle"><fmt:message key="mobile.products.searchfacets.clearfilter"/><c:out value=" ${selectedItem.name}"/></c:set>
                  <a class="clear" href="${removeLink}" title="${clearTitle}">&nbsp;</a>
                </c:when>
                <c:otherwise>
                  <a href="#"><fmt:message key="mobile.products.searchfacets.any"/><c:out value=" ${facetvalue.name}"/></a></span>
                  <ul class="subsubfilter">
                    <c:forEach items="${facetvalue.folders}" var="item">
                      <hst:link var="link" hippobean="${item}" navigationStateful="true">
                        <hst:sitemapitem preferPath="mobile/products"/>
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
  <li id="sort-by" class="last"><span><span class="name"><fmt:message key="mobile.products.searchfacets.sortby"/></span>
  <a href="#">
    <c:choose>
      <c:when test="${order == 'title'}"><fmt:message key="mobile.products.searchfacets.name"/></c:when>
      <c:when test="${order == '-rating'}"><fmt:message key="mobile.products.searchfacets.mostpopular"/></c:when>
      <c:when test="${order == '-lastModificationDate'}"><fmt:message key="mobile.products.searchfacets.mostrecent"/></c:when>
      <c:when test="${order == '-price'}"><fmt:message key="mobile.products.searchfacets.pricehtl"/></c:when>
      <c:when test="${order == 'price'}"><fmt:message key="mobile.products.searchfacets.pricelth"/></c:when>
      <c:otherwise><fmt:message key="mobile.products.searchfacets.relevance"/></c:otherwise>
    </c:choose>
  </a>
  </span>
    <ul class="subsubfilter">
      <c:if test="${order ne 'relevance'}">
        <li><a href="?query=${query}&amp;order=relevance"><fmt:message key="mobile.products.searchfacets.relevance"/></a></li>
      </c:if>
      <c:if test="${order ne 'title'}">
        <li><a href="?query=${query}&amp;order=title"><fmt:message key="mobile.products.searchfacets.name"/></a>
        </li>
      </c:if>
      <c:if test="${order ne '-rating'}">
        <li><a href="?query=${query}&amp;order=-rating"><fmt:message key="mobile.products.searchfacets.mostpopular"/></a></li>
      </c:if>
      <c:if test="${order ne '-lastModificationDate'}">
        <li><a href="?query=${query}&amp;order=-lastModificationDate"><fmt:message key="mobile.products.searchfacets.mostrecent"/></a></li>
      </c:if>
      <c:if test="${order ne '-price'}">
        <li><a href="?query=${query}&amp;order=-price"><fmt:message key="mobile.products.searchfacets.pricehtl"/></a></li>
      </c:if>
      <c:if test="${order ne 'price'}">
        <li><a href="?query=${query}&amp;order=price"><fmt:message key="mobile.products.searchfacets.pricelth"/></a></li>
      </c:if>
    </ul>
  </li>
</ul>
