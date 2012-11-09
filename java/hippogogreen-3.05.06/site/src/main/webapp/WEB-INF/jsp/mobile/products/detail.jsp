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

<hippo-gogreen:title title="${document.title}"/>

<hst:headContribution category="jsInternal">
    <hst:link var="rateJs" path="/js/mobile/rate.js"/>
    <script src="${rateJs}" type="text/javascript"></script>
</hst:headContribution>

<ga:trackDocument hippoDocumentBean="${document}"/>
    
<!-- body / main -->
<div id="bd">
    
    <div id="content" class="detail">
        <h1><c:out value="${document.title}"/></h1>
        <p id="product-info" class="doc-info">
          <span class="price"><fmt:formatNumber value="${document.price}" type="currency"/></span>
          <span class="seperator">|</span>
          <fmt:formatNumber value="${document.rating * 10}" var="ratingStyle" pattern="#0"/>
          <span class="rating stars-${ratingStyle}"><c:out value="${document.rating}"/></span>
        </p>
        
        <!-- image gallery -->
        <c:if test="${fn:length(document.images) > 0}">
          <div id="gallery-container">
            <ul id="gallery">
                <hst:link hippobean="${document.images[0].largeThumbnail}" var="imgLink"/>
                <li><img src="${imgLink}" alt="${fn:escapeXml(document.images[0].alt)}"/></li>
            </ul>
            <p class="copyright">&lt;<fmt:message key="mobile.products.detail.imgcopyright"/>&gt;</p>
          </div>
        </c:if>
        
        <p class="date"><fmt:message key="mobile.products.detail.sampledate"/></p>
        <p class="summary"><c:out value="${document.summary}"/></p>
        <div class="description">
          <hst:html hippohtml="${document.description}"/>
        </div>
        <hippo-gogreen:copyright document="${document}" truncate="60"/>
        
        <hst:actionURL var="actionUrl"/>
        
        <h3><fmt:message key="products.detail.reviewarticle"/></h3>
        <div id="review">
          <form id="frmRating" action="${actionUrl}" method="post">
            <table>
              <c:if test="${not empty success}">
              <tr>
                <td colspan="2">
                  <fmt:message key="products.detail.thanksforreview"/>
                </td>
              </tr>
              </c:if>
              <tr>
                <td class="label"><fmt:message key="products.detail.name"/></td>
                <td class="input"><input type="text" value="${fn:escapeXml(name)}" name="name" />
                  <c:if test="${not empty errors}">
                    <c:forEach items="${errors}" var="error">
                      <c:if test="${error eq 'invalid.name-label'}">
                        <span class="form-error"><fmt:message key="products.detail.name.error"/></span>
                      </c:if>
                    </c:forEach>
                  </c:if>
                </td>
              </tr>
              <tr>
                <td class="label"><fmt:message key="products.detail.email"/></td>
                <td class="input"><input type="text" value="${fn:escapeXml(email)}" name="email" />
                  <c:if test="${not empty errors}">
                    <c:forEach items="${errors}" var="error">
                      <c:if test="${error eq 'invalid.email-label'}">
                        <span class="form-error"><fmt:message key="products.detail.email.error"/></span>
                      </c:if>
                    </c:forEach>
                  </c:if>
                </td>
              </tr>
              <tr>
                <td class="label vmiddle"><fmt:message key="products.detail.score"/></td>
                <td class="input">
                  <ol class="rate">
                    <li><span title="Rate: 1">1</span></li>
                    <li><span title="Rate: 2">2</span></li>
                    <li><span title="Rate: 3">3</span></li>
                    <li><span title="Rate: 4">4</span></li>
                    <li><span title="Rate: 5">5</span></li>
                  </ol>
                <input type="hidden" value="0" name="rating" id="ratingField" />
                </td>
              </tr>
              <tr>
                <td class="label vtop"><fmt:message key="products.detail.review"/></td>
                <td class="input">
                  <textarea name="comment" id="comment" rows="3" cols="24"><c:if test="${not empty comment}"><c:out value="${comment}"/></c:if></textarea>
                  <c:if test="${not empty errors}">
                    <c:forEach items="${errors}" var="error">
                      <c:if test="${error eq 'invalid.comment-label'}">
                        <span class="form-error"><fmt:message key="products.detail.review.error"/></span>
                      </c:if>
                    </c:forEach>
                  </c:if>
                </td>
              </tr>
              <tr>
                <td></td>
                <td class="submit"><input type="submit" value="<fmt:message key="products.detail.submit.label"/>" id="review-button" /></td>
              </tr>
            </table>
          </form>
        </div>
      </div>

</div>