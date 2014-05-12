<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd"
	"http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd">
<html lang="ko">
<head>
<link href="<c:url value='/css/egovframework/com/cmm/com.css'/>" rel="stylesheet" type="text/css">
<title>파일 다운로드</title>
<script>
	function action() {
		if (frm.filename == "") {
			alert('Upload된 파일명을 입력해 주세요.');
			frm.filename.focus();
			return;
		}
		frm.submit();
	}
</script>
</head>
<body>
<form name="frm" action ="./EgovFileDown.do" method="post">
<table border="1">
	<tr>
		<td>uploadFileName:</td>
		<td><input type="text" name="filename"></td>
		<td> <input type="button" method="post"  value="실행!" onclick="action"></td>
	</tr>
</table>
</form>

<% if (request.getAttribute("message") != null) { %>
<script>
	alert('<%= request.getAttribute("message") %>');
</script>
<% } %>
</body>
</html>