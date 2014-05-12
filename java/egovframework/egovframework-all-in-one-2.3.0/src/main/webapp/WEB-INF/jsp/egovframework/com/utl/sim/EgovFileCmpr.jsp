<%--
  Class Name : EgovFileCmpr.jsp
  Description : 두 파일을 비교하는 JSP
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.01.20    박지욱          최초 생성

    author   : 박지욱
    since    : 2009.01.20

--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.sim.service.EgovFileTool"  %>
<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="CMPR1";
}
%>
<%
if(execFlag.equals("CMPR1")){
%>
<!-- 두 파일 사이즈비교 화면  시작-->
<form name="cmprForm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmpr">
<input type = "hidden" name="execFlag" value="CMPR1_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" >파일 사이즈 비교</th>
      <td width="280" height="23" class="title_left" ><input type = "button" method="post"  value="실행!" onclick="cmprForm1.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" >파일1</th>
      <td width="280" height="23" class="title_left" ><input type = "text" name="file1" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" >파일2</th>
      <td width="280" height="23" class="title_left" ><input type = "text" name="file2" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 두 파일 사이즈비교 화면 끝 -->
<!-- 두 파일 수정일자 화면  시작-->
<form name="cmprForm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmpr">
<input type = "hidden" name="execFlag" value="CMPR2_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
	<tr>
      <th width="150" height="23" class="required_text" >파일 수정일자 비교</th>
      <td width="280" height="23" class="title_left" ><input type = "button" method="post"  value="실행!" onclick="cmprForm2.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" >파일1</th>
      <td width="280" height="23" class="title_left" ><input type = "text" name="file1" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" >파일2</th>
      <td width="280" height="23" class="title_left" ><input type = "text" name="file2" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 두 파일 수정일자 화면 끝 -->
<!-- 두 파일 내용비교 화면  시작-->
<form name="cmprForm3" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmpr">
<input type = "hidden" name="execFlag" value="CMPR3_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 내용 비교</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="cmprForm3.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>파일1</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file1" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>파일2</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file2" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 두 파일 내용비교 화면 끝 -->
<!-- 두 파일 생성자비교 화면  시작-->
<form name="cmprForm4" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmpr">
<input type = "hidden" name="execFlag" value="CMPR4_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 생성자 비교</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="cmprForm4.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>파일1</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file1" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>파일2</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file2" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 두 파일 내용비교 화면 끝 -->
<%
} else if(execFlag.equals("CMPR1_ACTION")){

	String file1 = request.getParameter("file1");
	String file2 = request.getParameter("file2");
	boolean result = false;
	if (file1 != null && file1.length() > 0
		&& file2 != null && file2.length() > 0) {
		result = EgovFileTool.cmprFilesBySize(file1, file2);
	}

%>
<!-- 두 파일 사이즈비교 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmpr">
<input type = "hidden" name="execFlag" value="CMPR1">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="150" class="title_left">두 파일 사이즈비교 결과</td>
        <td><%=result %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 두 파일 사이즈비교 결과화면 끝 -->
<%
} else if(execFlag.equals("CMPR2_ACTION")){

	String file1 = request.getParameter("file1");
	String file2 = request.getParameter("file2");
	boolean result = false;
	if (file1 != null && file1.length() > 0
		&& file2 != null && file2.length() > 0) {
		result = EgovFileTool.cmprFilesByUpdtPd(file1, file2);
	}

%>
<!-- 두 파일 수정일자 결과화면 시작 -->
<form name="fm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmpr">
<input type = "hidden" name="execFlag" value="CMPR1">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="150" class="title_left">두 파일 수정일자 결과</td>
        <td><%=result %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 두 파일 수정일자 결과화면 끝 -->
<%
} else if(execFlag.equals("CMPR3_ACTION")){

	String file1 = request.getParameter("file1");
	String file2 = request.getParameter("file2");
	boolean result = false;
	if (file1 != null && file1.length() > 0
		&& file2 != null && file2.length() > 0) {
		result = EgovFileTool.cmprFilesByContent(file1, file2);
	}

%>
<!-- 두 파일 내용비교 결과화면 시작 -->
<form name="fm3" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmpr">
<input type = "hidden" name="execFlag" value="CMPR1">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="150" class="title_left">두 파일 내용비교 결과</td>
        <td><%=result %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm3.submit()">
</form>
<!-- 두 파일 내용비교 결과화면 끝 -->
<%
} else if(execFlag.equals("CMPR4_ACTION")){

	String file1 = request.getParameter("file1");
	String file2 = request.getParameter("file2");
	boolean result = false;
	if (file1 != null && file1.length() > 0
		&& file2 != null && file2.length() > 0) {
		result = EgovFileTool.cmprFilesByOwner(file1, file2);
	}

%>
<!-- 두 파일 생성자비교 결과화면 시작 -->
<form name="fm4" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmpr">
<input type = "hidden" name="execFlag" value="CMPR1">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="150" class="title_left">두 파일 생성자비교 결과</td>
        <td><%=result %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm4.submit()">
</form>
<!-- 두 파일 생성자비교 결과화면 끝 -->

<%
}
%>