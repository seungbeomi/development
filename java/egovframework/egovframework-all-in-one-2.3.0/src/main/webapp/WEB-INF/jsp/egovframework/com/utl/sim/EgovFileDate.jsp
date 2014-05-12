<%--
  Class Name : EgovFileDate.jsp
  Description : 파일의 생성일자별 파일목록 조회 또는 특정 생성기간내 파일목록 조회하는 JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.01.28    박지욱          최초 생성
 
    author   : 박지욱
    since    : 2009.01.28
   
--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="java.util.ArrayList"  %>
<%@page import="egovframework.com.utl.sim.service.EgovFileTool"  %>

<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"></script>
<script type="text/javaScript" language="javascript">
function fnDateForm1() {

	if(isDate(document.dateForm1.day.value, "최종수정일자")) {
		document.dateForm1.submit();
	}else{
		return;
	}
}

function fnDateForm2() {

	if(isDate(document.dateForm2.day1.value, "최종수정일자From")) {
		if (isDate(document.dateForm2.day2.value, "최종수정일자To")) {
			document.dateForm2.submit();
		} else {
			return;
		}
	}else{
		return;
	}
}
</script>

<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="DATE1";
}
%>
<%
if(execFlag.equals("DATE1")){
%>
<!-- 생성일자가 동일한 파일목록 화면  시작-->
<form name="dateForm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileDate">
<input type = "hidden" name="execFlag" value="DATE1_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="200" height="23" class="required_text" nowrap>최종수정일자별 파일목록 조회</th>
      <td width="230" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnDateForm1()"></td>
    </tr>
    <tr>
      <th width="200" height="23" class="title_left" nowrap>경로</td>
      <td width="230" height="23" class="title_left" nowrap><input type = "text" name="file" value="" size="40"></td>
    </tr>
    <tr>
      <th width="200" height="23" class="title_left" nowrap>최종수정일자(YYYYMMDD)</td>
      <td width="230" height="23" class="title_left" nowrap><input type = "text" name="day" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 생성일자가 동일한 파일목록 화면 끝 -->
<!-- 생성기간내 존재하는 파일목록 화면  시작-->
<form name="dateForm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileDate">
<input type = "hidden" name="execFlag" value="DATE2_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="200" height="23" class="required_text" nowrap>최종수정기간별 파일목록 조회</th>
      <td width="230" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnDateForm2()"></td>
    </tr>
    <tr>
      <th width="200" height="23" class="title_left" nowrap>경로</td>
      <td width="230" height="23" class="title_left" nowrap><input type = "text" name="file" value="" size="40"></td>
    </tr>
    <tr>
      <th width="200" height="23" class="title_left" nowrap>최종수정일자From(YYYYMMDD)</td>
      <td width="230" height="23" class="title_left" nowrap><input type = "text" name="day1" value="" size="40"></td>
    </tr>
    <tr>
      <th width="200" height="23" class="title_left" nowrap>최종수정일자To(YYYYMMDD)</td>
      <td width="230" height="23" class="title_left" nowrap><input type = "text" name="day2" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 생성기간내 존재하는 파일목록 화면 끝 -->
<%  
} else if(execFlag.equals("DATE1_ACTION")){
	
	String file = request.getParameter("file");
	String day = request.getParameter("day");
	
	ArrayList list = null;
	if (file != null && file.length() > 0
		&& day != null && day.length() > 0) {
		list = EgovFileTool.getFileListByDate(file, day);
	}
	String result = "";
	if (list != null && list.size() > 0) {
		for (int j = 0; j < list.size(); j++) {
			String str = (String)list.get(j);
			result += str + "<br>";
		}
	}
	
%>
<!-- 생성일자가 동일한 파일목록 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileDate">
<input type = "hidden" name="execFlag" value="DATE1">
<table width="500" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="200" class="title_left">파일목록</td>
        <td width="300"><%=result %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 생성일자가 동일한 파일목록 결과화면 끝 -->
<%  
} else if(execFlag.equals("DATE2_ACTION")){
	
	String file = request.getParameter("file");
	String day1 = request.getParameter("day1");
	String day2 = request.getParameter("day2");
	
	ArrayList list = null;
	if (file != null && file.length() > 0
		&& day1 != null && day1.length() > 0
		&& day2 != null && day2.length() > 0) {
		list = EgovFileTool.getFileListByUpdtPd(file, day1, day2);
	}
	String result = "";
	if (list != null && list.size() > 0) {
		for (int j = 0; j < list.size(); j++) {
			String str = (String)list.get(j);
			result += str + "<br>";
		}
	}
	
%>
<!-- 생성기간내 존재하는 파일목록 결과화면 시작 -->
<form name="fm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileDate">
<input type = "hidden" name="execFlag" value="DATE1">
<table width="500" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="200" class="title_left">파일목록</td>
        <td width="300"><%=result %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 생성기간내 존재하는 파일목록 결과화면 끝 -->
<%
}
%>