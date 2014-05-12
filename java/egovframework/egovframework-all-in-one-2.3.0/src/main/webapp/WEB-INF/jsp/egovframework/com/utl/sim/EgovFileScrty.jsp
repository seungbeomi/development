<%--
  Class Name : EgovFileScrty.jsp
  Description : Base64인코딩/디코딩 방식을 이용한 데이터를 암호화/복호화하는 JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.04    박지욱          최초 생성
 
    author   : 박지욱
    since    : 2009.02.04
   
--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="java.io.*"  %>
<%@page import="java.security.Key"  %>
<%@page import="egovframework.com.utl.sim.service.EgovFileScrty"  %>
<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="ENCRYPT";
}
%>
<%
if(execFlag.equals("ENCRYPT")){
%>
<!-- 암호화 화면  시작-->
<form name="cryptForm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileScrty">
<input type = "hidden" name="execFlag" value="ENCRYPT_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 암호화(txt)</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="cryptForm1.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>암호화 파일경로</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file1" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>암호화 결과파일</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file2" value="" size="40"></td>
    </tr>
</table>
</form>
<form name="cryptForm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileScrty">
<input type = "hidden" name="execFlag" value="DECRYPT_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 복호화(txt)</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="cryptForm2.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>복호화 파일경로</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file1" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>복호화 결과파일</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file2" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 암호화 화면 끝 -->
<%  
} else if(execFlag.equals("ENCRYPT_ACTION")){

	String source = request.getParameter("file1");
	String target = request.getParameter("file2");
	boolean result = false;
	if (source != null && source.length() > 0
		&& target != null && target.length() > 0) {
		result = EgovFileScrty.encryptFile(source, target);
	}
%>
<!-- 암호화 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileScrty">
<input type = "hidden" name="execFlag" value="ENCRYPT">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">암호화 결과</td>
        <td><%=result %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 암호화 결과화면 끝 -->
<%  
} else if(execFlag.equals("DECRYPT_ACTION")){

	String source = request.getParameter("file1");
	String target = request.getParameter("file2");
	boolean result = false;
	if (source != null && source.length() > 0
		&& target != null && target.length() > 0) {
		result = EgovFileScrty.decryptFile(source, target);
	}
%>
<!-- 복호화 결과화면 시작 -->
<form name="fm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileScrty">
<input type = "hidden" name="execFlag" value="ENCRYPT">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">복호화 결과</td>
        <td><%=result %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 복호화 결과화면 끝 -->
<%
} 
%>