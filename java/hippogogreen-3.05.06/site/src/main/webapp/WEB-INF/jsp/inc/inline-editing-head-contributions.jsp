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
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.hippoecm.org/jsp/hst/core" prefix='hst' %>

<hst:link var="yuiLoaderSrc" path="/javascript/yui/yuiloader/yuiloader-min.js"/>
<hst:link var="inlineEditingSrc" path="/js/inline-editing/inline-editing.js"/>
<hst:element var="yuiLoader" name="script">
    <hst:attribute name="id" value="yuiloader" />
    <hst:attribute name="type" value="text/javascript" />
    <hst:attribute name="src" value="${yuiLoaderSrc}" />
</hst:element>
<hst:headContribution keyHint="yuiLoader" element="${yuiLoader}" category="jsInHead"/>
<hst:element var="inlineEditing" name="script">
    <hst:attribute name="id" value="inlineEditing" />
    <hst:attribute name="type" value="text/javascript" />
    <hst:attribute name="src" value="${inlineEditingSrc}" />
</hst:element>
<hst:headContribution keyHint="inlineEditing" element="${inlineEditing}" category="jsInHead"/>
