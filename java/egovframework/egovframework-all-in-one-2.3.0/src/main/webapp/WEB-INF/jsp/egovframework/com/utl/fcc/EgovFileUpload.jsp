<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd"
	"http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd">
<html lang="ko">
<head>
<title>File Upload</title>
<link href="<c:url value='/css/egovframework/com/cmm/com.css'/>" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
	function process() {
		var frm = document.frm;

		if (frm.file.value == '') {
			alert('파일을 선택해 주세요.');
			return;
		}
		
		frm.submit();
	}
</script>
<body>
<form name="frm" action ="/EgovFileUp.do" method="post" enctype="multipart/form-data">
<table border="1">
	<tr>        	
		<td> 
			<input type = "file" name="file">
		</td>	
		<td>
			<input type = "button" value="실행!" onclick="process()">
		</td>
	</tr> 
</table>
<br>
<% 
if (request.getAttribute("result") != null) {
	ArrayList list = (ArrayList)request.getAttribute("result");
	HashMap map = (HashMap)list.get(0);
%>
<table border="1">
	<tr>        	
		<td>originalFileName:</td>
		<td><%= map.get("originalFileName") %></td>
	</tr>
	<tr>        	
		<td>uploadFileName:<br>(다운로드 테스트시 필요)</td>
		<td><%= map.get("uploadFileName") %></td>
	</tr>
	<tr>        	
		<td>fileExtension:</td>
		<td><%= map.get("fileExtension") %></td>
	</tr>
	<tr>        	
		<td>filePath:</td>
		<td><%= map.get("filePath") %></td>
	</tr>
	<tr>        	
		<td>fileSize:</td>
		<td><%= map.get("fileSize") %></td>
	</tr>
</table>
<% 
}
%>
</form>
</body>
</html>