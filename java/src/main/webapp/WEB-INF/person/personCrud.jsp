<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>title</title>
<link type="text/css" rel="stylesheet" href="/development/styles/person.css"/>
<script type="text/javascript" src="/development/js/lib/jquery-1.4.4.js"></script>
<script type="text/javascript" src="/development/js/person.js"></script>
</head>
<body>
<table width="330" border="1" cellpadding="0" class="b1">
	<thead id="crud">
	<tr><th class="t4" colspan="2">Person Master</th></tr>
	<tr>
		<th colspan="2">
			<input type="radio" name="method" id="add" checked="checked"/>登録
			<input type="radio" name="method" id="view"/>参照
			<input type="radio" name="method" id="update"/>更新
			<input type="radio" name="method" id="delete"/>削除
		</th>
	</tr>
	</thead>
	<tbody id="data">
	<tr>
		<th width="100">ID</th>
		<td><input id="id"  type="text" size="12"></td>
	</tr>
	<tr><th>Name</th><td><input id="name" type="text" size="34"/></td></tr>
	<tr><th>Address</th><td><input id="address" type="text" size="34"/></td></tr>
	<tr><th>Status</th><td id="status"/>status : ${status}</td></tr>
</table>
</body>
</html>