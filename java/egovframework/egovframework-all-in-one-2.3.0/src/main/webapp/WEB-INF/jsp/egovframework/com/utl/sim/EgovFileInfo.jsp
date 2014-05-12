<%--
  Class Name : EgovFileInfo.jsp
  Description : 파일의 속성정보를 조회하는 JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.02    박지욱          최초 생성
 
    author   : 박지욱
    since    : 2009.02.02
   
--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.sim.service.EgovFileTool"  %>
<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="DISK";
}
%>
<%
if(execFlag.equals("DISK")){
%>
<!-- 디스크명 화면  시작-->
<form name="infoForm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileInfo">
<input type = "hidden" name="execFlag" value="DISK_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 속성정보 조회</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="infoForm1.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>파일</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 디스크명 화면 끝 -->
<%  
} else if(execFlag.equals("DISK_ACTION")){
	
	String file = request.getParameter("file");
	
	String disk = "";
	String drctry = "";
	String name = "";
	String date = "";
	String owner = "";
	String access = "";
	long size = 0;
	String format = "";
	
	if (file != null && file.length() > 0) {
		disk = EgovFileTool.getMountLc(file);
		drctry = EgovFileTool.getDrctryName(file);
		name = EgovFileTool.getFileName(file);
		date = EgovFileTool.getUpdtDate(file);
		owner = EgovFileTool.getOwner(file);
		access = EgovFileTool.getAccess(file);
		size = EgovFileTool.getSize(file);
		format = EgovFileTool.getFormat(file);
	}
	
	
%>
<!-- 디스크명 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileInfo">
<input type = "hidden" name="execFlag" value="DISK">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">디스크</td>
        <td><%=disk %></td>
    </tr>
    <tr>
        <td width="100" class="title_left">디렉토리</td>
        <td><%=drctry %></td>
    </tr>
    <tr>
        <td width="100" class="title_left">파일명</td>
        <td><%=file %></td>
    </tr>
    <tr>
        <td width="100" class="title_left">최종수정일</td>
        <td><%=date %></td>
    </tr>
    <tr>
        <td width="100" class="title_left">생성자</td>
        <td><%=owner %></td>
    </tr>
    <tr>
        <td width="100" class="title_left">접근권한</td>
        <td><%=access %></td>
    </tr>
    <tr>
        <td width="100" class="title_left">사이즈</td>
        <td><%=size %></td>
    </tr>
    <tr>
        <td width="100" class="title_left">포맷</td>
        <td><%=format %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 디스크명 결과화면 끝 -->
<%  
} 
%>