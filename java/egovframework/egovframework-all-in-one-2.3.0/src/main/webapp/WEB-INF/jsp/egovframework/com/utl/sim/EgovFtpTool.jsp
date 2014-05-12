<%--
  Class Name : EgovFtpTool.jsp
  Description : FTP Client - Server간 파일 송수신 기능을 제공하는 JSP
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.06    박지욱          최초 생성

    author   : 박지욱
    since    : 2009.02.06

--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.sim.service.EgovFtpTool"  %>

<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"></script>
<script type="text/javaScript" language="javascript">
function fnFtpForm1() {

	if(isNumber(document.ftpForm1.port.value, "포트")) {
		if (isNumber(document.ftpForm1.mode.value, "전송모드")) {
			document.ftpForm1.submit();
		} else {
			return;
		}
	}else{
		return;
	}
}

function fnFtpForm2() {

	if(isNumber(document.ftpForm2.port.value, "포트")) {
		if (isNumber(document.ftpForm2.mode.value, "전송모드")) {
			document.ftpForm2.submit();
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
    execFlag="DOWN";
}
%>
<%
if(execFlag.equals("DOWN")){
%>
<!-- 다운로드 화면  시작-->
<form name="ftpForm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFtpTool">
<input type = "hidden" name="execFlag" value="DOWN_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>FTP 다운로드</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnFtpForm1();"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>IP</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="ip" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>PORT</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="port" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>ID</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="id" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>PASSWORD</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="pw" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>MODE(0:ASCII, 1:BIN)</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="mode" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>수신할 파일</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="remote" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>수신 경로</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="local" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 다운로드 화면 끝 -->
<!-- 업로드 화면  시작-->
<form name="ftpForm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFtpTool">
<input type = "hidden" name="execFlag" value="UP_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>FTP 업로드</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnFtpForm2();"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>IP</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="ip" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>PORT</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="port" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>ID</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="id" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>PASSWORD</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="pw" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>MODE(0:ASCII, 1:BIN)</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="mode" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>송신할 파일</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="local" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>송신 경로</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="remote" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 업로드 화면 끝 -->
<%
} else if(execFlag.equals("DOWN_ACTION")){

	String ip = request.getParameter("ip");
	int port = Integer.parseInt(request.getParameter("port"));
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	int mode = Integer.parseInt(request.getParameter("mode"));
	String remote = request.getParameter("remote");
	String local = request.getParameter("local");

	// 2011.10.10 보안점검 후속조치
	if (pw == null || "".equals(pw)) {
	    throw new RuntimeException("password invalid!");
	}

	boolean result = false;
	if (ip != null && ip.length() > 0
		&& port != 0
		&& id != null && id.length() > 0
		&& pw != null && pw.length() > 0
		&& mode != 0
		&& remote != null && remote.length() > 0
		&& local != null && local.length() > 0) {
		result = EgovFtpTool.getFile(ip, port, id, pw, mode, remote, local);
	}

%>
<!-- 다운로드 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFtpTool">
<input type = "hidden" name="execFlag" value="DOWN">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">다운로드 결과</td>
        <td><%=result %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 다운로드 결과화면 끝 -->
<%
} else if(execFlag.equals("UP_ACTION")){

	String ip = request.getParameter("ip");
	int port = Integer.parseInt(request.getParameter("port"));
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	int mode = Integer.parseInt(request.getParameter("mode"));
	String local = request.getParameter("local");
	String remote = request.getParameter("remote");

	// 2011.10.10 보안점검 후속조치
	if (pw == null || "".equals(pw)) {
	    throw new RuntimeException("password invalid!");
	}

	boolean result = false;
	if (ip != null && ip.length() > 0
		&& port != 0
		&& id != null && id.length() > 0
		&& pw != null && pw.length() > 0
		&& mode != 0
		&& remote != null && remote.length() > 0
		&& local != null && local.length() > 0) {
		result = EgovFtpTool.sendFile(ip, port, id, pw, mode, local, remote);
	}

%>
<!-- 업로드 결과화면 시작 -->
<form name="fm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFtpTool">
<input type = "hidden" name="execFlag" value="DOWN">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">업로드 결과</td>
        <td><%=result %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 업로드 결과화면 끝 -->
<%
}
%>