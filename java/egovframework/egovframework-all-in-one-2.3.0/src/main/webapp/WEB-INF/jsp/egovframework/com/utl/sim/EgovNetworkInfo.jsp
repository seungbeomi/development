<%--
  Class Name : EgovNetworkInfo.jsp
  Description : 네트웍정보를 가지고 오는  JSP
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
<title>EgovNetworkInfo</title>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
</head>
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
    execFlag="NETWORKINFO";
}

if (execFlag.equals("NETWORKINFO")){
%>
<!-- IPAddress 화면  시작-->
<form name="getIPForm" action ="/EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovNetworkInfo">
<input type = "hidden" name="execFlag" value="NETWORKINFO_ACTION">
<input type = "hidden" name="cmdStr" value="ipaddress">
<table width="840" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td height="23" class="title_left" nowrap>IP ADDRESS를 확인한다....</td>
    </tr>
    <tr>
        <td height="23" class="title_left" nowrap>MAC ADDRESS를 확인한다....</td>
    </tr> 
    <tr>
        <td height="23" class="title_left" nowrap>PortScan 결과를 확인한다....</td>
    </tr>     
    <tr>
        <td><input type = "button" method="post"  value="실행!" onclick="getIPForm.submit()"></td>
    </tr>
</table>
</form>

<!-- 결과 화면 -->
<%  
} else if(execFlag.equals("NETWORKINFO_ACTION")){
    List<String > list;
   String myIPAddress = null;
   String myMACAddress = null;
   String myPortScan = null;
   myIPAddress = EgovNetworkState.getMyIPaddress();
   list = EgovNetworkState.getMyPortScan();
   myMACAddress = EgovNetworkState.getMyMACAddress(myIPAddress);
%>
<!-- IPAddress 결과화면 시작 -->
<form name="fm1" action ="/EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovNetworkState">
<input type = "hidden" name="execFlag" value="NETWORKINFO">
<table width="850" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <th width="150" height="23" class="title_left" nowrap>IP ADDRESS 결과</th> <td><%=myIPAddress %></td>
    </tr>
    <tr>
        <th width="150" height="23" class="title_left" nowrap>MAC ADDRESS 결과</th> <td><%=myMACAddress %></td>
    </tr>
    <tr>
        <th width="150" height="23" class="title_left" nowrap>PortScan 결과</th> <td>&nbsp;</td>
    </tr> 
    <tr> 
    <td width="150" height="23" class="title_left" nowrap>&nbsp;</td>
    <td>
<% 
    Iterator<String> it = list.iterator();
    while (it.hasNext()) {
    String text = (String) it.next();
	%><%=text %><br><%
    }
%>  
    </td>
    </tr>
    <tr>
       <td colspan="2">
          <input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">&nbsp;&nbsp;
          <input type = "button" method="post"  value="화면닫기" onclick="javascript:window.close()">
       </td>
    </tr>
</table> 
</form>
<%
}
%>
</html>