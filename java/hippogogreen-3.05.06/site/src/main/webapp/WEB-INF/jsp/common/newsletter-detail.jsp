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
<%@include file="../includes/tags.jspf" %>

<c:set var="lang" value="${pageContext.request.locale.language}"/>

<html xmlns="http://www.w3.org/1999/xhtml" lang="${lang}">
<head>
    <hst:headContributions categoryExcludes="css,jsInternal,jsExternal" />
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <link rel="schema.DC" href="http://purl.org/dc/elements/1.1/" />
    <link rel="schema.DCTERMS" href="http://purl.org/dc/terms/" />
    <meta name="DC.keywords" content="<fmt:message key="layout.webpage.metadckeywords"/>" />
    <meta name="description" content="<fmt:message key="layout.webpage.metadescription"/>" />
    <meta name="DC.type" content="webpagina" scheme="THC.type" />
    <meta name="DCTERMS.issued" content="2009-07-09T10:31" scheme="DCTERMS.W3CDTF" />
    <meta name="DCTERMS.available" content="2009-07-09T10:31" scheme="DCTERMS.W3CDTF" />
    <meta name="DC.title" content="<fmt:message key="layout.webpage.metadctitle"/>" />
    <meta name="DC.language" content="${lang}" scheme="DCTERMS.RFC3066" />

    <hst:link var="yuicss" path="/css/yui-css.css"/>
    <link rel="stylesheet" media="screen" type="text/css" href="${yuicss}"/>

    <hst:link var="screen" path="/css/screen.css"/>
    <link rel="stylesheet" media="screen" type="text/css" href="${screen}"/>

    <hst:headContributions categoryIncludes="css"/>

    <hst:link var="jquery" path="/js/jquery-1.4.2.min.js"/>
    <script src="${jquery}" type="text/javascript"></script>

    <hst:link var="favicon" path="/images/favicon.ico"/>
    <link rel="icon" href="${favicon}" type="image/x-icon"/>
    <link rel="shortcut icon" href="${favicon}" type="image/x-icon"/>

    <hst:link var="appletouchicon" path="/images/apple-touch-icon.png"/>
    <link rel="apple-touch-icon" href="${appletouchicon}"/>

    <!--[if lte IE 7]>
       <hst:link var="ie7css" path="/css/ie7.css"/>
       <link rel="stylesheet" media="screen" type="text/css" href="${ie7css}">
       <script type="text/javascript">ie7 = true;</script>
    <![endif]-->

    <!--
        <hst:link var="print" path="/css/print.css"/>
        <link rel="stylesheet" media="print" type="text/css" href="${print}">
     -->
</head>
<body<c:if test="${cookie.textSize.value ne null}"> style="font-size: ${cookie.textSize.value}px;"</c:if>>

<div class="yui-main">
<div id="content" class="yui-b left-and-right">

  <c:choose>
    <c:when test="${documents ne null}">
      <div id="about">
        <h2 class="title"><c:out value="${folderName}"/></h2>
        <c:forEach items="${documents}" var="document">
          <ul class="about-item <c:if test="${preview}">editable</c:if>">
            <hst:cmseditlink hippobean="${document}" />
            <hst:link var="link" hippobean="${document}"/>
            <li class="title"><a href="${link}"><c:out value="${document.title}"/></a></li>
            <li class="description"><c:out value="${document.summary}"/></li>
          </ul>
        </c:forEach>
      </div>
    </c:when>
    <c:when test="${document ne null}">
      <div id="article" class="about <c:if test="${preview}">editable</c:if>">
        <hst:cmseditlink hippobean="${document}" />
        <h2 class="title"><c:out value="${document.title}"/></h2>

        <p class="intro"><c:out value="${document.summary}"/></p>

        <div class="yui-cssbase body">
          <hst:html hippohtml="${document.description}"/>
        </div>
      </div>
    </c:when>
  </c:choose>

</div>
</div>
<div>
  <hst:include ref="boxes-under"/>
</div>
<hst:link var="jscommon" path="/js/js-common.js"/>
<script src="${jscommon}" type="text/javascript"></script>

<hst:link var="jquerytools" path="/js/jquery.tools.min.js"/>
<script src="${jquerytools}" type="text/javascript"></script>

<hst:link var="helpoverlay" path="/js/help-overlay.js"/>
<script src="${helpoverlay}" type="text/javascript"></script>

<hst:headContributions categoryIncludes="jsExternal"/>
<hst:headContributions categoryIncludes="jsInternal"/>
</body>
</html>


