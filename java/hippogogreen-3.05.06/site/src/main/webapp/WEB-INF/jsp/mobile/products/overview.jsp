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

<c:set var="mobileproductstitle"><fmt:message key="mobile.products.overview.title"/></c:set>
<hippo-gogreen:title title="${mobileproductstitle}"/>

<!-- body / main -->
<div id="bd">

    <!-- search results -->
    <div id="content">
        <div id="products" class="results">

            <hst:include ref="boxes-top"/>

            <%--@elvariable id="docs" type="com.onehippo.gogreen.utils.PageableCollection"--%>
            <c:forEach items="${docs.items}" var="product" varStatus="status">
                <hst:link var="prdlink" hippobean="${product}"/>
                <ul class="product-item">
                    <li class="full-link"><a href="${fn:escapeXml(prdlink)}"></a></li>
                    <c:choose>
                        <c:when test="${product.mainImage.mobileThumbnail != null}">
                            <hst:link var="thumbnail" hippobean="${product.mainImage.mobileThumbnail}"/>
                            <li class="image"><img src="${thumbnail}" alt="" /></li>
                        </c:when>
                        <c:otherwise>
                            <hst:link var="thumbnail" hippobean="${product.mainImage.smallThumbnail}"/>
                            <li class="image"><img src="${thumbnail}" alt="" width="40" height="40" /></li>
                        </c:otherwise>
                    </c:choose>
                    <li class="title"><a href="${fn:escapeXml(prdlink)}"><c:out value="${product.title}"/></a></li>
                    <li class="price"><span><fmt:formatNumber value="${product.price}" type="currency"/></span>|</li>
                    <fmt:formatNumber value="${product.rating * 10}" var="ratingStyle" pattern="#0" />
                    <li class="rating stars-${ratingStyle}"><span><c:out value="${product.rating}"/></span></li>
                </ul>
            </c:forEach>

        </div>

        <c:if test="${count > pageSize}">
            <div id="show-more">
                <c:choose>
                  <c:when test="${count - pageSize > defaultShowMore}">
                      <c:set var="howMany" value="${defaultShowMore}" />
                  </c:when>
                  <c:otherwise>
                      <c:set var="howMany" value="${count - pageSize}" />
                  </c:otherwise>
                </c:choose>
                <img id="load" style="float:right;display:none;" src=" <hst:link path="/images/mobile/spinner.gif"/>" alt="<fmt:message key="mobile.products.overview.loading"/>" />
                <a class="more" href="?jsEnabled=false&amp;pageSize=${pageSize + 25}&amp;from=${pageSize}&amp;count=${count}">
                  <fmt:message key="mobile.products.overview.showmore">
            		<fmt:param value="${howMany}"/>            		
            	  </fmt:message>   
                </a>
            </div>
        </c:if>
    </div>
    
 

</div>