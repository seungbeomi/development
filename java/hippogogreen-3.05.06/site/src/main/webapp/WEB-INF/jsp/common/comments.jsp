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

<c:if test="${not empty comments}">        
  <div id="comments">
    <c:forEach items="${comments}" var="comment">
      <ul class="comment-item">
          <li class="name">
            <c:choose>
              <c:when test="${not empty comment.email}">
                <a href="mailto:${fn:escapeXml(comment.email)}"><c:out value="${comment.name}"/></a>
              </c:when>
              <c:otherwise>
                <c:out value="${comment.name}"/>
              </c:otherwise>
            </c:choose>
          </li>
          <li class="date"><span class="seperator">|</span> <fmt:formatDate value="${comment.creationDate.time}" type="date" pattern="MMM d, yyyy"/></li>
          <li class="text"><c:out value="${comment.body}"/></li>
          <li class="bg-bottom"></li>
      </ul>
    </c:forEach>
  </div>
</c:if>

<hst:actionURL var="actionUrl"/>

<a name="comment"></a>
<div id="article-footer">
    <h3><fmt:message key="common.comments.label"/></h3>
    <ul class="box-bottom box-form" id="review">
        <li class="content">
            <form id="frmRating" action="${actionUrl}" method="post">
                <table>
                    <tr>
                        <td colspan="2">
                            <c:if test="${not empty success}">
                                <fmt:message key="common.comments.thankyou"/>
                                <br/>
                                <br/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="label"><fmt:message key="common.comments.name"/></td>
                        <td class="input"><input type="text" value="${name}" name="name"/>
                            <c:if test="${not empty errors}">
                                <c:forEach items="${errors}" var="error">
                                    <c:if test="${error eq 'invalid.name-label'}">
                                        <span class="form-error"><fmt:message key="common.comments.name.error"/></span><br/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="label"><fmt:message key="common.comments.email"/></td>
                        <td class="input"><input type="text" value="${email}" name="email"/>
                            <c:if test="${not empty errors}">
                                <c:forEach items="${errors}" var="error">
                                    <c:if test="${error eq 'invalid.email-label'}">
                                        <span class="form-error"><fmt:message key="common.comments.email.error"/></span><br/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="label vtop"><fmt:message key="common.comments.comment"/></td>
                        <td class="input"><textarea name="comment" id="comment" rows="8" cols="50"><c:if
                                test="${not empty comment}"><c:out value="${comment}"/></c:if></textarea>
                            <c:if test="${not empty errors}">
                                <c:forEach items="${errors}" var="error">
                                    <c:if test="${error eq 'invalid.comment-label'}">
                                        <span class="form-error"><fmt:message key="common.comments.comment.error"/></span><br/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td class="submit fright"><input type="submit" value="<fmt:message key="common.comments.submit.label"/>" id="comment-button"/></td>
                    </tr>
                </table>
            </form>
        </li>
    </ul>
</div>
