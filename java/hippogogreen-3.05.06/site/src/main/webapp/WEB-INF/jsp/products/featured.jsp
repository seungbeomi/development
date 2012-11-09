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
<div id="home-products">
    <h2 class="go-green"><fmt:message key="home.products.label"/></h2>
    <%--@elvariable id="products" type="java.util.List<com.onehippo.gogreen.beans.Product>"--%>
    <c:forEach items="${products}" var="prd" varStatus="index">
        <hst:link var="prdLink" hippobean="${prd}"/>
        <hst:link var="prdImgLink" hippobean="${prd.mainImage.smallThumbnail}"/>
        <c:choose>
            <c:when test="${index.count eq 1}">
                <c:set var="style" value="first "/>
            </c:when>
            <c:when test="${index.count eq 3}">
                <c:set var="style" value="last "/>
            </c:when>
        </c:choose>

        <ul class="product-item ${style} <c:if test="${preview}">editable</c:if>">
            <hst:cmseditlink hippobean="${prd}" />
            <li class="image"><a href="${fn:escapeXml(prdLink)}"><img src="${fn:escapeXml(prdImgLink)}"
                                                                      alt="${fn:escapeXml(prd.mainImage.alt)}"/></a>
            </li>
            <li class="title"><a href="${fn:escapeXml(prdLink)}"><c:out value="${prd.title}"/></a></li>
            <li class="price"><a href="${fn:escapeXml(prdLink)}"><fmt:formatNumber value="${prd.price}" type="currency"/></a></li>
            <c:if test="${reseller}">
              <li class="resellerpricefeatured">
                <fmt:message key="products.resellerprice"/>:
                <br/>
                <fmt:formatNumber value="${prd.resellerPrice}" type="currency"/>
              </li>
            </c:if>
            <fmt:formatNumber value="${prd.rating * 10}" var="ratingStyle" pattern="#0"/>
            <li class="rating stars-${ratingStyle}"><a href="${prdLink}"><c:out value="${prd.rating}"/>
                <span class="votes"> (<span class="icon"><c:out value="${prd.votes}"/></span> <fmt:message
                        key="products.featured.reviews"/>)</span></a>
            </li>
        </ul>
    </c:forEach>
  <div class="clear"></div>
</div>



