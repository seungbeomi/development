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
<%--@elvariable id="document" type="com.onehippo.gogreen.beans.Product"--%>
<%@include file="../includes/tags.jspf" %>

<c:set var="datePattern" value="dd-MM-yyyy"/>
<!-- NOTE: Switch on the following variable if you want to eanble Inline Editing feature in this page. -->
<c:set var="inlineEditingEnabled" value="false" /> 

<c:if test="${preview}">
    <c:if test="${inlineEditingEnabled}">
        <jsp:include page="../inc/inline-editing-head-contributions.jsp"/>
    </c:if>
</c:if>
<hippo-gogreen:title title="${document.title}"/>

<hst:headContribution keyHint="rate" category="jsInternal">
     <hst:link path="/js/rate.js" var="rateJs"/>
    <script type="text/javascript" src="${rateJs}"></script>
</hst:headContribution>

<%-- <h1><hst:link var="linkToRestProduct" hippobean="${document}" mount="restapi"  /><a href="${linkToRestProduct}">rest product</a></h1> --%>

<div <c:if test="${preview}">class="editable"</c:if>>
  <ol id="breadcrumbs">
      <li><fmt:message key="products.detail.location.label"/></li>
      <li>
          <hst:link var="home" siteMapItemRefId="home" />
          <a href="${home}"><fmt:message key="products.detail.location.home"/></a> &gt;
      </li>
      <li>
          <hst:link var="products" siteMapItemRefId="products" />
          <a href="${products}"><fmt:message key="products.detail.title"/></a> &gt;
      </li>
  </ol>

  <h2><c:out value="${document.title}"/></h2>


  <div id="product" class="product-item">
      <hst:cmseditlink hippobean="${document}" />

      <c:if test="${fn:length(document.images) > 0}">
          <hippo-gogreen:image-gallery/>
          <hippo-gogreen:imagecopyright image="${document.images[0]}"/>
      </c:if>

      <p class="date"><fmt:message key="products.detail.sampledate"/></p>

      <p class="info">
          <span class="${reseller ? 'nonresellerprice' : 'price'}"><fmt:formatNumber value="${document.price}" type="currency"/></span><span class="seperator"> | </span>
          <fmt:formatNumber value="${document.rating * 10}" var="ratingStyle" pattern="#0"/>
          <span class="rating stars-${ratingStyle}"><c:out value="${document.rating}"/><span class="votes">(<span
            class="icon"><c:out value="${votes}"/></span> <fmt:message key="products.detail.reviews"/>)</span></span>
          <c:if test="${reseller}">
            <span class="resellerprice"><fmt:message key="products.resellerprice"/>: <fmt:formatNumber value="${document.resellerPrice}" type="currency"/></span>
          </c:if>
      </p>

      <div class="yui-cssbase body">
          <div id="editable_cont" class="inline-editor-editable-container">
              <span class="<c:if test="${preview}">editable</c:if>" id="hippogogreen:summary"><c:out value="${document.summary}"/></span>
              <span class="<c:if test="${preview}">editable</c:if> inline" id="hippogogreen:description"><hst:html hippohtml="${document.description}"/></span>
          <hippo-gogreen:copyright document="${document}"/>
    </div>
      </div>
  </div>
  <ul id="document-actions">
      <li><a class="comment" href="#review"><fmt:message key="products.detail.review"/></a></li>
  </ul>

  <div id="comments">
      <%--@elvariable id="reviews" type="java.util.List<com.onehippo.gogreen.beans.Review>"--%>
      <c:forEach items="${reviews}" var="review">
          <ul class="comment-item">
              <li class="name"><a href="#"><c:out value="${review.name}"/></a></li>
              <li class="date"><span class="seperator">|</span> <fmt:formatDate value="${review.date.time}"
                                                                                pattern="MMM dd, yyyy"/></li>
              <li class="text">
                  <ul>
                      <li class="score"><fmt:message key="products.detail.score"/>:</li>
                      <fmt:formatNumber value="${review.rating * 10}" var="reviewRatingStyle" pattern="#0"/>
                      <li class="rating stars-${reviewRatingStyle}"></li>
                      <li class="review"><c:out value="${review.comment}"/></li>
                  </ul>
              </li>
              <li class="bg-bottom"></li>
          </ul>
      </c:forEach>
  </div>

  <hst:actionURL var="actionUrl"/>

  <div id="article-footer">
      <h3><fmt:message key="products.detail.reviewarticle"/></h3>
      <ul class="box-bottom box-form" id="review">
          <li class="content">
              <form id="frmRating" action="${actionUrl}" method="post">
                  <table>
                      <tr>
                          <td colspan="2">
                              <c:if test="${not empty success}">
                                  <fmt:message key="products.detail.thanksforreview"/>
                                  <br/>
                                  <br/>
                              </c:if>
                          </td>
                      </tr>
                      <tr>
                          <td class="label"><fmt:message key="products.detail.name"/></td>
                          <td class="input"><input type="text" value="${fn:escapeXml(name)}" name="name" />
                              <c:if test="${not empty errors}">
                                  <c:forEach items="${errors}" var="error">
                                      <c:if test="${error eq 'invalid.name-label'}">
                                          <span class="form-error"><fmt:message key="products.detail.name.error"/></span><br/>
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
                                          <span class="form-error"><fmt:message key="products.detail.email.error"/></span><br/>
                                      </c:if>
                                  </c:forEach>
                              </c:if>
                          </td>
                      </tr>
                      <tr>
                          <td class="label vtop"><fmt:message key="products.detail.score"/></td>
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
                          <%--Do not split next line in rows, else the textarea will show white spaces on initialization--%>
                          <td class="input"><textarea name="comment" id="comment" rows="8" cols="50"><c:if test="${not empty comment}"><c:out value="${comment}"/></c:if></textarea>
                              <c:if test="${not empty errors}">
                                  <c:forEach items="${errors}" var="error">
                                      <c:if test="${error eq 'invalid.comment-label'}">
                                          <span class="form-error"><fmt:message key="products.detail.review.error"/></span><br/>
                                      </c:if>
                                  </c:forEach>
                              </c:if>
                          </td>
                      </tr>
                      <tr>
                          <td></td>
                          <td class="submit fright"><input type="submit" value="<fmt:message key="products.detail.submit.label"/>" id="comment-button" /></td>
                      </tr>
                  </table>
              </form>
          </li>
      </ul>
  </div>
</div>

<hst:headContribution keyHint="formCss">
    <link rel="stylesheet" href="<hst:link path="/css/easyforms.css"/>" type="text/css"/>
</hst:headContribution>

<ga:trackDocument hippoDocumentBean="${document}"/>

<c:if test="${preview && inlineEditingEnabled}">
    <jsp:include page="../inc/inline-editing-editor-form.jsp"/>
</c:if>
