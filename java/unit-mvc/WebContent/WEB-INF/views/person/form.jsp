<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="welcome.title" /></title>
<style type="text/css">
.error {background:#FBE3E4;color:#8a1f11;border-color:#FBC2C4;}
.error a {color:#8a1f11;}
</style>
</head>
<body>
<h1>
    Register Person
</h1>
<form:form modelAttribute="person" action="person" method="post">
    <fieldset>
        <legend>Person Fields</legend>
        <p>
            <form:label for="name" path="name" cssErrorClass="error">Name</form:label>
            <form:input path="name"/><form:errors path="name" cssClass="error" />
        </p>
        <p>
            <input type="submit">
        </p>
    </fieldset>
</form:form>
</body>
</html>