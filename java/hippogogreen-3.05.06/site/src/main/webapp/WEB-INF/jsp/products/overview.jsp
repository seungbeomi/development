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

<c:if test="${docs.total gt 0}">
  <ol id="breadcrumbs">
    <li><fmt:message key="products.overview.location.label"/></li>
    <li>
      <hst:link var="home" siteMapItemRefId="home" />
      <a href="${home}"><fmt:message key="products.overview.location.home"/></a> &gt;
    </li>
  </ol>

  <h2><fmt:message key="products.overview.title"/></h2>

</c:if>

<div id="products">
  <%--@elvariable id="docs" type="com.onehippo.gogreen.utils.PageableCollection"--%>
  <c:forEach items="${docs.items}" var="product" varStatus="status">
    <%--@elvariable id="product" type="com.onehippo.gogreen.beans.Product"--%>
    <hst:link var="prdlink" hippobean="${product}"/>
    <ul class="product-item<c:if test="${preview}"> editable</c:if>">
      <li class="image">
        <c:if test="${not empty product.mainImage and not empty product.mainImage.smallThumbnail}">
          <a href="${fn:escapeXml(prdlink)}">
            <img src='<hst:link hippobean="${product.mainImage.smallThumbnail}"/>' alt='${fn:escapeXml(product.mainImage.alt)}'/>
          </a>
        </c:if>
      </li>
      <c:if test="${preview}">
        <li><hst:cmseditlink hippobean="${product}"/></li>
      </c:if>
      <li class="title"><a href="${fn:escapeXml(prdlink)}"><c:out value="${product.title}"/></a></li>
      <li class="${reseller ? 'nonresellerprice' : 'price'}">
        <a href="${fn:escapeXml(prdlink)}"><fmt:formatNumber value="${product.price}" type="currency"/></a> |
      </li>
      <fmt:formatNumber value="${product.rating * 10}" var="ratingStyle" pattern="#0"/>
      <li class="rating stars-${ratingStyle}"><a href="${fn:escapeXml(prdlink)}"><c:out value="${product.rating}"/></a>
      </li>
      <c:if test="${reseller}">
        <li class="resellerprice">
          <a href="${fn:escapeXml(prdlink)}"><fmt:message key="products.resellerprice"/>:
            <fmt:formatNumber value="${product.resellerPrice}" type="currency"/></a>
        </li>
      </c:if>
      <li class="description"><c:out value="${product.summary}"/></li>
    </ul>
  </c:forEach>
</div>

<c:choose>
  <c:when test="${docs.total eq 0}">
    <p id="results"><fmt:message key="search.results.noresults"/> '${query}'</p>
  </c:when>
  <c:otherwise>
    <hippo-gogreen:pagination pageableResult="${docs}" queryName="query" queryValue="${query}"/>
  </c:otherwise>
</c:choose>
