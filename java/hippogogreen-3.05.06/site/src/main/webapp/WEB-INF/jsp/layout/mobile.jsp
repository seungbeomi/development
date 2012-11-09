<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
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
<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.onehippo.org/jsp/google-analytics" prefix="ga" %>
<%@include file="../includes/tags.jspf" %>

<c:set var="lang" value="${pageContext.request.locale.language}"/>

<html xmlns="http://www.w3.org/1999/xhtml" lang="${lang}">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta name="viewport" content="width=320; user-scalable=0;"/>
    <meta name="language" content="${lang}" />

    <hst:headContributions categoryExcludes="css,jsInternal,jsExternal"/>

     <hst:link var="yuicss" path="/css/mobile/yui-css.css"/>
    <link rel="stylesheet" media="screen" type="text/css" href="${yuicss}"/>

    <hst:link var="screen" path="/css/mobile/screen.css"/>
    <link rel="stylesheet" media="screen" type="text/css" href="${screen}"/>

    <hst:link var="galleryview" path="js/galleryview-2.1.1/galleryview.css"/>
    <link rel="stylesheet" media="screen" type="text/css" href="${galleryview}"/>

    <hst:headContributions categoryIncludes="css"/>

    <hst:link var="jquery" path="/js/jquery-1.4.2.min.js"/>
    <script src="${jquery}" type="text/javascript"></script>
    <hst:link var="jscommon" path="/js/mobile/js-common.js"/>
    <script src="${jscommon}" type="text/javascript"></script>

    <hst:link var="easing" path="/js/galleryview-2.1.1/jquery.easing.1.3.js"/>
    <script type="text/javascript" src="${easing}"></script>

    <hst:link var="galleryview" path="/js/galleryview-2.1.1/jquery.galleryview-2.1.1-pack.js"/>
    <script type="text/javascript" src="${galleryview}"></script>

    <hst:link var="timer" path="/js/galleryview-2.1.1/jquery.timers-1.2.js"/>
    <script type="text/javascript" src="${timer}"></script>

    <hst:link var="filter" path="/js/mobile/filter.js"/>
    <script src="${filter}" type="text/javascript"></script>

    <hst:headContributions categoryIncludes="jsInternal"/>
    
    <hst:link var="favicon" path="/images/mobile/favicon.ico"/>
    <link rel="icon" href="${favicon}" type="image/x-icon"/> 
    <hst:link var="appletouchicon" path="/images/mobile/apple-touch-icon.png"/>
    <link rel="apple-touch-icon" href="${appletouchicon}"/>

</head>
<body>
    <!-- document wrapper -->
    <div id="doc" class="yui-d-custom">
        <!-- header -->
        <hst:include ref="header"/>
        
        <!-- body / main -->
        <hst:include ref="body"/>

        <!-- footer -->
        <hst:include ref="footer"/>
    </div>
  <c:if test="${not composermode}">
    <ga:accountId/>
    <hst:link var="googleAnalytics" path="/resources/google-analytics.js"/>
    <script src="${googleAnalytics}" type="text/javascript"></script>
  </c:if>
</body>
</html>
        