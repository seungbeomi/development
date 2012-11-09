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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
    <title>Hippo Sales Demo</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=320; user-scalable=0;"/>
    <link rel="stylesheet" media="screen" type="text/css" href="css/yui-css.css">
    <link rel="stylesheet" media="screen" type="text/css" href="css/screen.css">
    
    <c:if test="${param.page eq 'search'}">
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/js-common.js"></script>
    <script type="text/javascript" src="js/filter.js"></script>
    </c:if>
    
    <c:if test="${param.section eq 'products' and param.page eq 'detail'}">
    <link rel="stylesheet" media="screen" type="text/css" href="js/galleryview-2.1.1/galleryview.css"/>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/galleryview-2.1.1/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="js/galleryview-2.1.1/jquery.galleryview-2.1.1-pack.js"></script>
    <script type="text/javascript" src="js/galleryview-2.1.1/jquery.timers-1.2.js"></script>
    </c:if>
    
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png"/> 
</head>
<body>
    <!-- document wrapper -->
    <div id="doc" class="yui-d-custom">
        <!-- header -->
        <div id="hd">
            <!-- logo -->
            <img src="images/logo.png" alt="Hippo Logo" class="logo">
        
            <!-- navigation -->
            <ul id="nav">
                <li<c:if test="${param.section eq 'products'}"> class="active"</c:if>>
                  <a href="products.jsp" title="Products">Products</a>
                  <ul>
                    <c:choose>
                    <c:when test="${param.page eq 'detail'}">
                      <li class="back">
                        <a href="products.jsp" onClick="javascript:history.back(); return false;" title="Products">Products</a></li>
                    </c:when>
                    <c:otherwise>
                      <li<c:if test="${param.page eq 'popular'}"> class="active"</c:if>>
                        <a href="products.jsp" title="Popular">Popular</a></li>
                      <li<c:if test="${param.page eq 'recent'}"> class="active"</c:if>>
                        <a href="?page=recent" title="Recent">Recent</a></li>
                      <li<c:if test="${param.page eq 'search'}"> class="active"</c:if>>
                        <a href="products-search.jsp" title="Search">Search</a></li>
                    </c:otherwise>
                    </c:choose>
                  </ul>
                </li>
                <li<c:if test="${param.section eq 'events'}"> class="active"</c:if>>
                  <a href="events.jsp" title="Events">Events</a>
                  <ul>
                    <c:choose>
                    <c:when test="${param.page eq 'detail'}">
                      <li class="back">
                        <a href="events.jsp" onClick="javascript:history.back(); return false;" title="Events">Events</a></li>
                    </c:when>
                    <c:otherwise>
                      <li<c:if test="${param.page eq 'upcoming'}"> class="active"</c:if>>
                        <a href="events.jsp" title="Upcoming">Upcoming</a></li>
                      <li<c:if test="${param.page eq 'past'}"> class="active"</c:if>>
                        <a href="?page=recent" title="Past">Past</a></li>
                      <li<c:if test="${param.page eq 'search'}"> class="active"</c:if>>
                        <a href="events-search.jsp" title="Search">Search</a></li>
                    </c:otherwise>
                    </c:choose>
                  </ul>
                </li>
            </ul>
        </div>
        
        <!-- body / main -->
        <div id="bd">
