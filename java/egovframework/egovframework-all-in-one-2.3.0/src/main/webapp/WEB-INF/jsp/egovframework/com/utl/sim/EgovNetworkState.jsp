<%--
  Class Name : EgovNetworkState.jsp
  Description : 네트워크 가능여부 체크  JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.02    이용          최초 생성
 
    author   : 이용
    since    : 2009.02.02
   
     page language="java" contentType="text/html; charset=UTF-8" session="false" 
    page language="java" contentType="application/pdf; charset=UTF-8" session="false"
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@ page import="egovframework.com.utl.sim.service.EgovNetworkState"  %>
<%@ page import="java.util.List"  %>
<%@ page import="java.util.Iterator"  %>
<html lang="ko">
<head>
<title>EgovNetworkState</title>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
</head>
<script>
/* ********************************************************
 * 네트워크 상태 체크 함수
 ******************************************************** */
	function fn_pingTest(obj){
		if(fn_NumberChk(obj)){
			document.pingTestForm.submit();
		}
    }
/* ********************************************************
 * 해당 필드 체크
 ******************************************************** */
 	function fn_NumberChk(obj){
		inputObj = obj.value ; 
		for(i=0;i<inputObj.length;i++)   
		if (inputObj.charAt(i)<'0' || inputObj.charAt(i)>'9') {  
			if (inputObj.charAt(i) != '.') {   
				alert("숫자만 입력해주세요.") ;   
				obj.value="";   
				obj.focus() ;   
				return false ;  
			} 
		}
		return true;
	}

</script>
<%!
    String safeGetParameter (HttpServletRequest request, String name){
        String value = request.getParameter(name);
        if (value == null) {
            value = "";
        }
        return value;
    }

    String[] safeGetParameterValues(HttpServletRequest request, String name){
		String[] sCheckParameterArray = request.getParameterValues(name);
		if( sCheckParameterArray.length == 0 )
		{
		   return null;
		}
		String[] sReturn = new String[sCheckParameterArray.length];
		for( int i = 0; i < sCheckParameterArray.length; i++ )
		{
	      sReturn[i] = (sCheckParameterArray[i]);
	      if("".equals(sReturn[i]) ){
	         sReturn[i] = null;
	      }
		}
		return sReturn;
	}
%>
<%
boolean retCopy = false; 
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="PINGTEST";
}

if(execFlag.equals("PINGTEST")){
%>
<!-- PINGTEST 화면  시작-->
<form name="pingTestForm" action ="/EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovNetworkState">
<input type = "hidden" name="execFlag" value="PINGTEST_ACTION">
<input type = "hidden" name="cmdStr" value="pingtest">
<table width="840" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td colspan="2" height="23" nowrap>
        server host, localhost or gateway IP의 PING TEST를 확인한다.<br>
                     해당서버의 호스트가 죽어있거나 혹은  핑 서비스는 사용 불능 상태, 방화벽에 의한 차단일경우는  ‘false’를 반환한다.
        </td>
    </tr>
    <tr>
        <th width="150" height="23" class="title_left" nowrap>host IPADDRESS :</th>
        <td>&nbsp;<input type = "text" name="source" value="" size="50"></td>
    </tr> 
    <tr>
       <td colspan="2">
          <input type = "button" method="post"  value="실행!" onclick="pingTestForm.submit()">
       </td>
    </tr>
</table>
</form>
<!-- PINGTEST 결과화면 시작 -->
<%  
} else if(execFlag.equals("PINGTEST_ACTION")){
   String source = safeGetParameter(request,"source");
   retCopy = EgovNetworkState.getPingTest(source);

%>

<form name="mac" action ="/EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovNetworkState">
<input type = "hidden" name="execFlag" value="PINGTEST">
<table width="850" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <th width="150" height="23" class="title_left" nowrap>PINGTEST 결과</th>
        <td width="700" ><%=retCopy %></td>
    </tr>
    <tr> 
       <td width="150" height="23" class="title_left" nowrap colspan="2">&nbsp;</td>
    </tr>
    <tr>
       <td colspan="2">
          <input type = "button" method="post"  value="화면으로 돌아가기" onclick="mac.submit()">&nbsp;&nbsp;
          <input type = "button" method="post"  value="화면닫기" onclick="javascript:window.close()">
       </td>
    </tr> 
</table> 
<br>

</form>
<%
} 
%>
</html>