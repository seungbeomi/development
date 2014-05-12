<%--
  Class Name : EgovFileExst.jsp
  Description : 파일이 존재하는지 체크하는 JSP
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

<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"></script>
<script type="text/javaScript" language="javascript">
function fnExstForm3() {

	if(isDate(document.exstForm3.fromDate.value, "수정기간From")) {
		if (isDate(document.exstForm3.toDate.value, "수정기간To")) {
			document.exstForm3.submit();
		} else {
			return;
		}
	}else{
		return;
	}
}

function fnExstForm4() {

	if(isNumber(document.exstForm4.fromSize.value, "사이즈From")) {
		if (isNumber(document.exstForm4.toSize.value, "사이즈To")) {
			document.exstForm4.submit();
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
    execFlag="EXST1";
}
%>
<%
if(execFlag.equals("EXST1")){
%>
<!-- 디렉토리 파일찾기 화면  시작-->
<form name="exstForm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileExst">
<input type = "hidden" name="execFlag" value="EXST1_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일존재 확인</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="exstForm1.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>경로</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="drctry" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>파일</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 디렉토리 파일찾기 화면 끝 -->
<!-- 확장자별 파일찾기 화면  시작-->
<form name="exstForm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileExst">
<input type = "hidden" name="execFlag" value="EXST2_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>확장자별 파일존재 확인</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="exstForm2.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>경로</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="drctry" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>확장자</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="eventn" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
</table>
</form>
<!-- 확장자별 파일찾기 화면 끝 -->
<!-- 수정기간내 파일찾기 화면  시작-->
<form name="exstForm3" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileExst">
<input type = "hidden" name="execFlag" value="EXST3_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>수정기간별 파일존재 확인</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnExstForm3()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>경로</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="drctry" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>수정기간From</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="fromDate" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>수정기간To</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="toDate" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
</table>
</form>
<!-- 수정기간내 파일찾기 화면 끝 -->
<!-- 사이즈내 파일찾기 화면  시작-->
<form name="exstForm4" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileExst">
<input type = "hidden" name="execFlag" value="EXST4_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>사이즈별 파일존재 확인</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnExstForm4()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>경로</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="drctry" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>사이즈From(KB)</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="fromSize" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>사이즈To(KB)</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="toSize" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
</table>
</form>
<!-- 사이즈내 파일찾기 화면 끝 -->
<!-- 생성자별 파일찾기 화면  시작-->
<form name="exstForm5" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileExst">
<input type = "hidden" name="execFlag" value="EXST5_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>생성자별 파일존재 확인</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="exstForm5.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>경로</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="drctry" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>생성자</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="creator" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 생성자별 파일찾기 화면 끝 -->
<%  
} else if(execFlag.equals("EXST1_ACTION")){
	
	String drctry = request.getParameter("drctry");
	String file = request.getParameter("file");
	boolean result = false;
	if (drctry != null && drctry.length() > 0
		&& file != null && file.length() > 0) {
		result = EgovFileTool.checkFileExstByName(drctry, file);
	}
	
%>
<!-- 디렉토리 파일찾기 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileExst">
<input type = "hidden" name="execFlag" value="EXST1">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">결과</td>
        <td><%=result %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 디렉토리 파일찾기 결과화면 끝 -->
<%  
} else if(execFlag.equals("EXST2_ACTION")){
	
	String drctry = request.getParameter("drctry");
	String eventn = request.getParameter("eventn");
	boolean result = false;
	if (drctry != null && drctry.length() > 0
		&& eventn != null && eventn.length() > 0) {
		result = EgovFileTool.checkFileExstByExtnt(drctry, eventn);
	}
	
%>
<!-- 확장자별 파일찾기 결과화면 시작 -->
<form name="fm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileExst">
<input type = "hidden" name="execFlag" value="EXST1">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">결과</td>
        <td><%=result %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 확장자별 파일찾기 결과화면 끝 -->
<%  
} else if(execFlag.equals("EXST3_ACTION")){
	
	String drctry = request.getParameter("drctry");
	String fromDate = request.getParameter("fromDate");
	String toDate = request.getParameter("toDate");
	boolean result = false;
	if (drctry != null && drctry.length() > 0
		&& fromDate != null && fromDate.length() > 0
		&& toDate != null && toDate.length() > 0) {
		result = EgovFileTool.checkFileExstByUpdtPd(drctry, fromDate, toDate);
	}
	
%>
<!-- 수정기간내 파일찾기 결과화면 시작 -->
<form name="fm3" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileExst">
<input type = "hidden" name="execFlag" value="EXST1">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">결과</td>
        <td><%=result %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm3.submit()">
</form>
<!-- 수정기간내 파일찾기 결과화면 끝 -->
<%  
} else if(execFlag.equals("EXST4_ACTION")){
	
	String drctry = request.getParameter("drctry");
	String fromSize = request.getParameter("fromSize");
	String toSize = request.getParameter("toSize");
	boolean result = false;
	if (drctry != null && drctry.length() > 0
		&& fromSize != null && fromSize.length() > 0
		&& toSize != null && toSize.length() > 0) {
		result = EgovFileTool.checkFileExstBySize(drctry, Long.parseLong(fromSize), Long.parseLong(toSize));
	}
	
%>
<!-- 사이즈내 파일찾기 결과화면 시작 -->
<form name="fm4" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileExst">
<input type = "hidden" name="execFlag" value="EXST1">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">결과</td>
        <td><%=result %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm4.submit()">
</form>
<!-- 사이즈내 파일찾기 결과화면 끝 -->
<%  
} else if(execFlag.equals("EXST5_ACTION")){
	
	String drctry = request.getParameter("drctry");
	String creator = request.getParameter("creator");
	boolean result = false;
	if (drctry != null && drctry.length() > 0
		&& creator != null && creator.length() > 0) {
		result = EgovFileTool.checkFileExstByOwner(drctry, creator);
	}
	
%>
<!-- 생성자별 파일찾기 결과화면 시작 -->
<form name="fm5" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileExst">
<input type = "hidden" name="execFlag" value="EXST1"/>
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">결과</td>
        <td><%=result %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm5.submit()">
</form>
<!-- 생성자별 파일찾기 결과화면 끝 -->
<%
} 
%>