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

<%@ page import="org.hippoecm.hst.core.container.ContainerConstants" %>
<%@ page import="org.hippoecm.hst.core.component.HstRequest" %>
<%@ page import="org.hippoecm.hst.util.HstRequestUtils" %>
<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.hippoecm.org/jsp/hst/core" prefix='hst' %>
<%@ taglib prefix="hippo-gogreen" tagdir="/WEB-INF/tags" %>


<hst:element var="dialogCss" name="link">
    <hst:attribute name="type">text/css</hst:attribute>
    <hst:attribute name="href"><hst:link path="/css/preview.css"/></hst:attribute>
    <hst:attribute name="rel">stylesheet</hst:attribute>
    <hst:attribute name="media">screen</hst:attribute>
</hst:element>
<hst:element var="uiCustom" name="script">
    <hst:attribute name="type">text/javascript</hst:attribute>
    <hst:attribute name="src"><hst:link path="/js/jquery-ui-1.8.2.custom.min.js"/></hst:attribute>
</hst:element>

<hst:headContribution keyHint="uiCustom" element="${uiCustom}" category="jsInternal"/>
<hst:headContribution keyHint="dialogCss" element="${dialogCss}" category="css"/>
<hst:headContribution keyHint="dialog" element="${dialog}" category="jsInternal"/>

<div id="hippo-dialog" class="btn-rounded minimized">
    <div id="hippo-dialog-head">
        <a href="#" class="hippo-header-home"><fmt:message key="inc.dialog.hippoheader"/></a>
        <span class="hippo-dialog-title"><fmt:message key="inc.dialog.preview"/></span>
        <ul>
            <li><a class="hippo-dialog-minimize" href="#"><fmt:message key="inc.dialog.minimize"/></a></li>
        </ul>
    </div>

    <div id="hippo-dialog-body">
        <div id="hippo-dialog-table-head">
            <span class="hippo-table-head"><fmt:message key="inc.dialog.clicktoedit"/></span>
        </div>
        <div class="hippo-dialog-content">
            <ul>
                <c:forEach items="${news.items}" var="bean">
                  <hst:cmseditlink hippobean="${bean}" var="editlink"/>
                    <li><a target="CMS" href="<c:out value="${editlink}" />&amp;mode=edit"><c:out value="${bean.title}"/></a></li>
                </c:forEach>
                <c:if test="${documents != null}">
                    <c:forEach items="${documents.items}" var="bean">
                      <hst:cmseditlink hippobean="${bean}" var="editlink"/>
                        <li><a target="CMS" href="<c:out value="${editlink}" />&amp;mode=edit"><c:out value="${bean.title}"/></a></li>
                    </c:forEach>
                </c:if>
                <c:if test="${not empty document}">
                  <hst:cmseditlink hippobean="${document}" var="editlink"/>
                    <li><a target="CMS" href="<c:out value="${editlink}" />&amp;mode=edit"><c:out value="${document.title}"/></a>
                    </li>
                </c:if>
            </ul>
        </div>
        <div id="hippo-dialog-table-footer">
            <%
                        HstRequest hstRequest = HstRequestUtils.getHstRequest(request);
                        pageContext.setAttribute("cmsLocation", hstRequest.getRequestContext().getContainerConfiguration().getString(ContainerConstants.CMS_LOCATION));
            %>

            <span><a target="CMS" href="${cmsLocation}?path=/content/gallery"><fmt:message key="inc.dialog.gallerylink.label"/></a></span> |
            <span><a target="CMS" href="${cmsLocation}?path=/content/assets"><fmt:message key="inc.dialog.assetslink.label"/></a></span> |
            <span><a target="CMS" href="${cmsLocation}?path=dashboard"><fmt:message key="inc.dialog.dashboardlink.label"/></a></span>

        </div>
    </div>
</div>

