<%--
  Class Name : EgovClntInfo.jsp
  Description : 클라이언트(Client)의 IP주소, OS정보, 웹브라우저정보를 조회하는 JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.01.19    박지욱          최초 생성
 
    author   : 박지욱
    since    : 2009.01.19
   
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.sim.service.EgovClntInfo"  %>
<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="CLNT";
}
%>
<%
if(execFlag.equals("CLNT")){
%>
<!-- 클라이언트 정보 확인 화면  시작-->
<form name="clntForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovClntInfo">
<input type = "hidden" name="execFlag" value="CLNT_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
  <tr> 
    <th width="150" height="23" class="required_text" nowrap >클라이언트 정보 확인&nbsp;&nbsp;</th>
    <td width="280" nowrap><input type = "button" method="post"  value="실행!" onclick="clntForm.submit()"></td>
  </tr>  
</table>
</form>
<!-- 클라이언트 정보 확인 화면 끝 -->
<%  
} else if(execFlag.equals("CLNT_ACTION")){
	
	String ipAddr = EgovClntInfo.getClntIP(request);
	String osInfo = EgovClntInfo.getClntOsInfo(request);
	String webKind = EgovClntInfo.getClntWebKind(request);
	String webVer = EgovClntInfo.getClntWebVer(request);
	
%>
<!-- 클라이언트 정보 확인 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovClntInfo">
<input type = "hidden" name="execFlag" value="CLNT">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td class="title_left">IP 주소
        </td>
        <td><%=ipAddr%>
        </td>
    </tr>
    <tr>
        <td class="title_left">OS 종류 / 버전
        </td>
        <td><%=osInfo%>
        </td>
    </tr>
    <tr>
        <td class="title_left">웹브라우저 종류 / 버전
        </td>
        <td><%=webKind%> , <%=webVer%>
        </td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 클라이언트 정보 확인 결과화면 끝 -->
<%
} 
%>