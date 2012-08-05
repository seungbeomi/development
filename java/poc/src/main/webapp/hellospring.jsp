<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="seungbeomi.web.springmvc.HelloSpring" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TEST</title>
</head>
<body>

<%
ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
HelloSpring helloSpring = (HelloSpring) context.getBean("helloSpring");
out.println(helloSpring.sayHello("seungbeomi"));
%>
</body>
</html>