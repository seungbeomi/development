<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shared/taglibs.jsp"%>
<a class="brand" href="<c:url value='/' />"><spring:message code='application_name' /></a>
<div class="nav-collapse collapse">
  <p class="navbar-text pull-right">
    Logged in as <a href="#" class="navbar-link">Username</a>
  </p>
  <ul class="nav">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="<c:url value='/batch/qrtzJobDetails' />">Batch</a></li>
    
    <!-- 
    <li><a href="#contact">Contact</a></li> 
    -->
  </ul>
</div>
<!--/.nav-collapse -->