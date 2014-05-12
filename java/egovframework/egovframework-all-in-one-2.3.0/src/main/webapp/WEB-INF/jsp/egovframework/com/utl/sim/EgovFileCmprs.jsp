<%--
  Class Name : EgovFileCmprs.jsp
  Description : 파일(디렉토리)의 압축/해제를 하는 JSP
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.04    박지욱          최초 생성

    author   : 박지욱
    since    : 2009.02.04

--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.sim.service.EgovFileCmprs"  %>
<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="CMPRS";
}
%>
<%
if(execFlag.equals("CMPRS")){
%>
<!-- 압축 화면  시작-->
<form name="cmprsForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmprs">
<input type = "hidden" name="execFlag" value="CMPRS_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
	<tr>
      <th width="150" height="23" class="required_text" >파일(디렉토리) 압축</th>
      <td width="280" height="23" class="title_left" ><input type = "button" method="post"  value="실행!" onclick="cmprsForm.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" >원본파일</th>
      <td width="280" height="23" class="title_left" ><input type = "text" name="source" value="" size="40"></td>
    </tr>
	<tr>
      <th width="150" height="23" class="title_left" >압축파일</th>
      <td width="280" height="23" class="title_left" ><input type = "text" name="target" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 압축 화면 끝 -->
<!-- 압축 해제 화면 시작 -->
<form name="decmprsForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmprs">
<input type = "hidden" name="execFlag" value="DECMPRS_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
	<tr>
      <th width="150" height="23" class="required_text" >파일(디렉토리) 압축해제</th>
      <td width="280" height="23" class="title_left" ><input type = "button" method="post"  value="실행!" onclick="decmprsForm.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" >압축파일</th>
      <td width="280" height="23" class="title_left" ><input type = "text" name="source" value="" size="40"></td>
    </tr>
	<tr>
      <th width="150" height="23" class="title_left" >해제경로</th>
      <td width="280" height="23" class="title_left" ><input type = "text" name="target" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 압축 해제 화면 끝 -->
<%
} else if(execFlag.equals("CMPRS_ACTION")){

	boolean isCompressed = false;
	String source = request.getParameter("source");
	String target = request.getParameter("target");
	if (source != null && source.length() > 0
		&& target != null && target.length() > 0) {
		isCompressed = EgovFileCmprs.cmprsFile(source, target);
	}

%>
<!-- 압축 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmprs">
<input type = "hidden" name="execFlag" value="CMPRS">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">압축 결과</td>
        <td><%=isCompressed %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 압축 결과화면 끝 -->
<%
} else if(execFlag.equals("DECMPRS_ACTION")){

	boolean isCompressed = false;
	String source = request.getParameter("source");
	String target = request.getParameter("target");
	if (source != null && source.length() > 0
		&& target != null && target.length() > 0) {
		isCompressed = EgovFileCmprs.decmprsFile(source, target);
	}

%>
<!-- 압축해제 결과화면 시작 -->
<form name="fm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCmprs">
<input type = "hidden" name="execFlag" value="CMPRS">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">압축해제 결과</td>
        <td><%=isCompressed %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 압축해제 결과화면 끝 -->
<%
}
%>