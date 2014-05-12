<%--
  Class Name : EgovFileAuthor.jsp
  Description : 파일(디렉토리)의 읽기/쓰기 권한을 체크하는 JSP
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.01.15    박지욱          최초 생성

    author   : 박지욱
    since    : 2009.01.15

--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.sim.service.EgovFileTool"  %>
<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="READ";
}
%>
<%
if(execFlag.equals("READ")){
%>
<!-- 파일읽기 화면  시작-->
<form name="readForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileAuthor">
<input type = "hidden" name="execFlag" value="READ_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" >파일(디렉토리) 읽기권한</th>
      <td width="280" height="23" class="title_left" ><input type = "button" method="post"  value="실행!" onclick="readForm.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" >파일명</th>
      <td width="280" height="23" class="title_left" ><input type = "text" name="filepath" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 파일읽기 화면 끝 -->
<!-- 파일쓰기 화면  시작-->
<form name="writeForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileAuthor">
<input type = "hidden" name="execFlag" value="WRITE_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <td width="150" height="23" class="required_text" >파일(디렉토리) 쓰기기권한</td>
      <td width="280" height="23" class="title_left" ><input type = "button" method="post"  value="실행!" onclick="writeForm.submit()"></td>
    </tr>
    <tr>
      <td width="150" height="23" class="title_left" >파일명</td>
      <td width="280" height="23" class="title_left" ><input type = "text" name="filepath" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 파일쓰기 화면 끝 -->
<%
} else if(execFlag.equals("READ_ACTION")){

	boolean isReadable = false;
	String filePath = request.getParameter("filepath");
	if (filePath != null && filePath.length() > 0) {
		isReadable = EgovFileTool.checkReadAuth(filePath);
	}

%>
<!-- 파일읽기권한 결과화면 시작 -->
<form name="fm1" action="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileAuthor">
<input type = "hidden" name="execFlag" value="READ">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">읽기권한</td>
        <td><%=isReadable %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 파일읽기권한 결과화면 끝 -->
<%
} else if(execFlag.equals("WRITE_ACTION")){

	boolean isWritable = false;
	String filePath = request.getParameter("filepath");
	if (filePath != null && filePath.length() > 0) {
		isWritable = EgovFileTool.checkWriteAuth(filePath);
	}

%>
<!-- 파일쓰기권한 결과화면 시작 -->
<form name="fm2" action="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileAuthor">
<input type = "hidden" name="execFlag" value="READ">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">쓰기권한</td>
        <td><%=isWritable %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 파일쓰기권한 결과화면 끝 -->
<%
}
%>