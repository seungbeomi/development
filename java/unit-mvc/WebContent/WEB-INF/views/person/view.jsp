<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="welcome.title" /></title>
</head>
<body>
<h1>
    View Person
</h1>
<form:form modelAttribute="person" action="${person.id}" method="post">
      <fieldset>
        <legend>Person Fields</legend>
        <p>
            <form:label	for="name" path="name">Name</form:label><br/>
            <form:input path="name" readonly="true"/>
        </p>
    </fieldset>
</form:form>
</body>
</html>