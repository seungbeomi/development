<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-3.dtd">
<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
</head>
<body>
	<div tiles:string="title">Title</div>
	
	<div id="header" tiles:include="header">Header</div>
	
	<div id="body" tiles:include="body">Body</div>
	
	<div>
		<span th:text="${serverTime}">server time</span>
	</div>
	${serverTime}
	
</body>
</html>