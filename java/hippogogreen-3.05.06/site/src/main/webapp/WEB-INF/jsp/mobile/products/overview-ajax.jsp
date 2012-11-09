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

<hst:include ref="boxes-top"/>

<%--@elvariable id="docs" type="com.onehippo.gogreen.utils.PageableCollection"--%>
<c:forEach items="${docs.items}" var="product">
    <hst:link var="prdlink" hippobean="${product}"/>
    <ul class="product-item">
        <li class="full-link"><a href="${prdlink}"></a></li>
        <c:choose>
            <c:when test="${not empty product.mainImage and not empty product.mainImage.mobileThumbnail}">
                <hst:link var="thumbnail" hippobean="${product.mainImage.mobileThumbnail}"/>
                <li class="image"><img src="${thumbnail}" alt="" /></li>
            </c:when>
            <c:when test="${not empty product.mainImage and not empty product.mainImage.smallThumbnail}">
                <hst:link var="thumbnail" hippobean="${product.mainImage.smallThumbnail}"/>
                <li class="image"><img src="${thumbnail}" width="40" height="40" alt="" /></li>
            </c:when>
            <c:otherwise>
                <li class="image"></li>
            </c:otherwise>
        </c:choose>
        <li class="title"><a href="${prdlink}"><c:out value="${product.title}"/></a></li>
        <li class="price"><span><fmt:formatNumber value="${product.price}" type="currency"/></span>|</li>
        <fmt:formatNumber value="${product.rating * 10}" var="ratingStyle" pattern="#0" />
        <li class="rating stars-${ratingStyle}"><span><c:out value="${product.rating}"/></span></li>
    </ul>
</c:forEach>
