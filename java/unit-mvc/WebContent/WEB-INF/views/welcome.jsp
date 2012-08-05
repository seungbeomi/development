<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="welcome.title" /></title>
</head>
<body>
<h1>
    <fmt:message key="welcome.title" />
</h1>
<p>
    Locale = ${pageContext.response.locale}
</p>
<ul>
    <li> <a href="?locale=en_us">us</a> |  <a href="?locale=ko_kr">ko</a></li>
</ul>
<ul>
    <li><a href="person">@MVC Example</a></li>
</ul>
</body>
</html>