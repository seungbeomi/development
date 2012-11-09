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

<hst:actionURL var="suggestProductAction"/>
<ul class="box-right box-form">
    <li class="title"><fmt:message key="products.suggest.title"/></li>
    <c:if test="${not empty errors}">
    <li class="form-error"><fmt:message key="products.suggest.errors"/>
    </li>
    </c:if>
    <c:if test="${not empty success}">
    <li><fmt:message key="products.suggest.thanks"/><br/><br/>
        <a href="<hst:link path="products" />"><fmt:message key="products.suggest.suggestanother"/></a>
    </li>
    </c:if>

    <li class="content">
        <form action="${suggestProductAction}" method="post">
            <table>
                <tr>
                    <td class="label"><fmt:message key="products.suggest.name"/>*</td>            
                    <td class="input"><input type="text" value="${name}" name="name" />
                        <c:forEach items="${errors}" var="error">
                            <c:if test="${error eq 'invalid.name-label'}">
                               <span class="form-error"><fmt:message key="products.suggest.name.error"/></span>
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td class="label"><fmt:message key="products.suggest.price"/>*</td>
                    <td class="input"><input type="text" value="${price}" name="price" />
                        <c:forEach items="${errors}" var="error">
                            <c:if test="${error eq 'invalid.price-label'}">
                               <span class="form-error"><fmt:message key="products.suggest.price.error"/></span>
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td class="label vtop"><fmt:message key="products.suggest.description"/></td>
                    <td class="input"><textarea name="description" cols="1" rows="2"></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td class="submit fright"><input type="submit" value="<fmt:message key="products.suggest.submit.label"/>" id="suggest-button" /></td>
                </tr>
            </table>
        </form>
    </li>
</ul>
