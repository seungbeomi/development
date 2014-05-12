<%--
  Class Name : EgovFilePars.jsp
  Description : 파일을 특정 구분자(',', '|', 'TAB') 또는 일정 길이로 파싱하는 JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.01.23    박지욱          최초 생성
 
    author   : 박지욱
    since    : 2009.01.23
   
--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="java.util.*"  %>
<%@page import="egovframework.com.utl.sim.service.EgovFileTool"  %>

<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"></script>
<script type="text/javaScript" language="javascript">
function fnParsForm1() {

	if(isNumber(document.parsForm1.field.value, "필드수")) {
		document.parsForm1.submit();
	}else{
		return;
	}
}
function fnParsForm2() {

	if(isNumber(document.parsForm2.line.value, "라인수")) {
		document.parsForm2.submit();
	}else{
		return;
	}
}
</script>

<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="PARS1";
}
%>
<%
if(execFlag.equals("PARS1")){
%>
<!-- 파일파싱(구분자) 화면  시작-->
<form name="parsForm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFilePars">
<input type = "hidden" name="execFlag" value="PARS1_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 특정구분자 파싱</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnParsForm1()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>파일</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>구분자</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="char" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>필드수</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="field" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
</table>
</form>
<!-- 파일파싱(구분자) 화면 끝 -->
<!-- 파일파싱(size) 화면  시작-->
<form name="parsForm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFilePars">
<input type = "hidden" name="execFlag" value="PARS2_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 일정길이 파싱</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnParsForm2()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>파일</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>필드길이(예,2:3:3)</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="len" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>라인수</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="line" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
</table>
</form>
<!-- 파일파싱(size) 화면 끝 -->
<%  
} else if(execFlag.equals("PARS1_ACTION")){
	
	String parFile = request.getParameter("file");
	String parChar = request.getParameter("char");
	int parField = Integer.parseInt(request.getParameter("field"));

	Vector list = new Vector();
	if (parFile != null && parFile.length() > 0
		&& parChar != null && parChar.length() > 0
		&& parField > 0) {
		list = EgovFileTool.parsFileByChar(parFile, parChar, parField);
	}
%>
<!-- 파일파싱(구분자) 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFilePars">
<input type = "hidden" name="execFlag" value="PARS1">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="150" class="title_left">구분자파싱 결과
        </td>
    </tr>
</table> 
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
<%
for (int j = 0; j < list.size(); j++) {
%>
	<tr>
<%		
	ArrayList arr = (ArrayList)list.elementAt(j);
	for (int k = 0; k < arr.size(); k++) {
%>
	<td><%=(String)arr.get(k) %></td>
<% 
	}
%>
 	</tr>   
<%
}
%>       
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 파일파싱(구분자) 결과화면 끝 -->
<%  
} else if(execFlag.equals("PARS2_ACTION")){
	
	String parFile = request.getParameter("file");
	String[] parSize = request.getParameter("len").split(":");
	String line = request.getParameter("line");
	
	int [] parLen = null;
	int parLine = 0;
	Vector list = new Vector();
	if (parFile != null && parFile.length() > 0
		&& parSize != null && parSize.length > 0
		&& line != null && line.length() > 0) {
		parLen = new int [parSize.length];
		for (int i = 0; i < parSize.length; i++) {
			parLen[i] = Integer.parseInt(parSize[i]);
		}
		parLine = Integer.parseInt(line);
		list = EgovFileTool.parsFileBySize(parFile, parLen, parLine);
	}
	
%>
<!-- 파일파싱(size) 결과화면 시작 -->
<form name="fm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFilePars">
<input type = "hidden" name="execFlag" value="PARS1">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="150" class="title_left">사이즈파싱 결과</td>
    </tr>
</table> 
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
<%
for (int j = 0; j < list.size(); j++) {
%>
	<tr>
<%		
	ArrayList arr = (ArrayList)list.elementAt(j);
	for (int k = 0; k < arr.size(); k++) {
%>
	<td><%=(String)arr.get(k) %></td>
<% 
	}
%>
 	</tr>   
<%
}
%>       
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 파일파싱(size) 결과화면 끝 -->
<%
}
%>