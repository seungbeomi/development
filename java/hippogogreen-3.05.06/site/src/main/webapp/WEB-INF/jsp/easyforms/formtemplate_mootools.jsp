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
<%--@elvariable id="form" type="org.onehippo.forge.easyforms.model.Form"--%>
<%--@elvariable id="likert" type="org.onehippo.forge.easyforms.model.Likert"--%>
<%--@elvariable id="ef_errors" type="java.util.List"--%>
<%--@elvariable id="error" type="org.onehippo.forge.easyforms.model.ErrorMessage"--%>
<c:forEach items="${ef_errors}" var="error">
    <div class="form-error"><c:out value="${error.message}"/></div>
</c:forEach>
<form class="form" action="<hst:actionURL />" method="post" id="${form.id}">
    <c:forEach var="field" items="${form.fields}">
        <c:choose>
            <c:when test="${field.simpleText}">
                <div class="ef-text">
                    <h2><c:out value="${field.label}"/></h2>

                    <p><c:out value="${field.hint}"/></p>
                </div>
            </c:when>
            <%-- simple types layout--%>
            <c:when test="${field.textField or field.password or field.textArea or field.dropdown or field.radioBox or field.checkBox}">
                <div class="ef-field">
                    <label><c:out value="${field.label}"/><span class="ef-req"><c:out value="${field.requiredMarker}"/></span></label>${field.html}<span
                        class="ef-hint"><c:out value="${field.hint}"/></span>
                </div>
            </c:when>
            <c:when test="${field.radioGroup}">
                <div class="ef-field">
                    <label><c:out value="${field.label}"/><span class="ef-req"><c:out value="${field.requiredMarker}"/></span></label>
                    <c:forEach var="radio" items="${field.fields}">
                        <p>${radio.html} <span><c:out value="${radio.label}"/></span></p>
                    </c:forEach>
                    <c:if test="${field.allowOther}">
                           <c:out value="${field.otherChoice}"/> <fmt:message key="easyforms.formtemplate.other"/>: <span><c:out value="${field.other}"/></span>
                    </c:if>
                    <span class="ef-hint"><c:out value="${field.hint}"/></span>
                </div>
            </c:when>
            <c:when test="${field.checkBoxGroup}">
                <div class="ef-field">
                    <label><c:out value="${field.label}"/><span class="ef-req"><c:out value="${field.requiredMarker}"/></span></label>
                    <c:forEach var="box" items="${field.fields}">
                        <p>${box.html} <c:out value="${box.label}"/></p>
                    </c:forEach>
                     <c:if test="${field.allowOther}">
                           ${field.otherChoice} <fmt:message key="easyforms.formtemplate.other"/>: <span>${field.other}</span>
                    </c:if>
                    <span class="ef-hint"><c:out value="${field.hint}"/></span>
                </div>
            </c:when>
            <%--  LIKERT--%>
            <c:when test="${field.likert}">
                <div class="ef-field">
                    <label><c:out value="${field.label}"/><span class="ef-req"><c:out value="${field.requiredMarker}"/></span></label>
                    <table class="ef-likert-table">
                        <tr>
                            <td>&nbsp;</td>
                            <c:forEach var="option" items="${field.options}">
                                <td>${option}</td>
                            </c:forEach>
                        </tr>
                        <c:forEach var="map" items="${field.htmlMap}">
                            <tr>
                                <td><c:out value="${map.key.label}"/></td>
                                <c:forEach var="radio" items="${map.value}">
                                    <td>${radio.html}</td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:when>
        </c:choose>
    </c:forEach>
    <div class="ef-buttons">
        <c:forEach var="button" items="${form.buttons}">
            ${button.html}
        </c:forEach>
    </div>
</form>
<%--
    HERE WE PRINT JAVASCRIPT CALL WHICH WILL VALIDATE OUR FORM
--%>
${form.jsCall}
<%--
    //########################################################################
    //  HEADER CONTRIBUTIONS
    //########################################################################
--%>

<hst:headContribution keyHint="formValidationCss" category="css">
    <link rel="stylesheet" href="<hst:link path="/js/formcheck/theme/blue/formcheck.css"/>" type="text/css"/>
</hst:headContribution>
<hst:headContribution keyHint="formJsMoo" category="jsInternal">
    <script type="text/javascript" src="<hst:link path="/js/mootools-1.2.4-core.js"/>"></script>
</hst:headContribution>
<hst:headContribution keyHint="formJsLang" category="jsInternal">
    <script type="text/javascript" src="<hst:link path="/js/formcheck/lang/en.js"/>"></script>
</hst:headContribution>
<hst:headContribution keyHint="formJsValidation" category="jsInternal">
    <script type="text/javascript" src="<hst:link path="/js/formcheck/formcheck.js"/>"></script>
</hst:headContribution>
<%--
    override all:
--%>
<hst:headContribution keyHint="formCss" category="css">
    <link rel="stylesheet" href="<hst:link path="/css/easyforms.css"/>" type="text/css"/>
</hst:headContribution>

