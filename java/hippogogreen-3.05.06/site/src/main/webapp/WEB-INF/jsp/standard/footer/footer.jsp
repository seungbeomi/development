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

<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://www.hippoecm.org/jsp/hst/core" prefix='hst' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="ft">
    <div class="logo">
        <a href="http://www.onehippo.com">
          <img src="<hst:link path="/images/logo-hippo.png"/>" alt="<fmt:message key="standard.footer.onehippo"/>"/>
        </a>

        <p><fmt:message key="standard.footer.copyright"/></p>

        <p>
          <hst:link var="termsLink" path="${termsPath}"/>
          <a href="${termsLink}"><fmt:message key="standard.footer.termsandconditions"/></a>
        </p>
    </div>
    <div class="yui-gb" id="ft-nav">
        <div class="yui-u first">
            <h3><fmt:message key="standard.footer.servicelabel"/></h3>
            <hst:include ref="service"/>
        </div>
        <div class="yui-u">
            <h3><fmt:message key="standard.footer.sectionslabel"/></h3>
            <hst:include ref="sections"/>
        </div>
    </div>
    <img id="ft-image" src="<hst:link path="/images/ft-leaf.png"/>" alt="" />
</div>