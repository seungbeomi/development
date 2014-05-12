<%-- *******************************************************
* Filename : EgovProcsInfo.jsp
* Class    :
* Function : 프로세스정보 확인용  JSP     
*
* @version   1.0
* @author    JJY
*******************************************************  --%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="java.util.ArrayList"  %>
<%@page import="java.util.Iterator"  %>
<%@page import="egovframework.com.utl.sim.service.EgovSysInfo"  %>
<%@page import="egovframework.com.cmm.service.Globals"  %>
<%@page import="javax.servlet.http.HttpServletRequest"  %>
<%!
    String safeGetParameter (HttpServletRequest request, String name){
        String value = request.getParameter(name);
        if (value == null) {
            value = "";
        }
        return value;
    }
%>

<%
    // 준비화면, 실행결과 출력화면의 구분
String execFlag = safeGetParameter(request,"execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="READY";
}
%>
<%
    if(execFlag.equals("READY")){
    // 실행을 위한 화면 준비
    // log.debug("ComUtlSimProcsInfo.jsp READY");
%>
<!-- 준비화면  시작-->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovProcsInfo">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom078">
<table class="table-line">
    <tr>
        <td colspan="2">프로세스 명</td>
    </tr>
    <tr>
        <td><input type="text" name="processName" ></td>    
        <td><input type="button" value="실행!" onclick="fm.submit()"></td>
    </tr>
</table>
</form>
<!--  준비화면 끝 -->

<%
    }else if(execFlag.equals("ACTION")){
    // 실행을 화면 출력
    // log.debug("ComUtlSimDrctryInfoCeck.jsp ACTION");
    String strProcessName = safeGetParameter(request,"processName") == null ? "" : safeGetParameter(request,"processName");
    
    ArrayList list = new ArrayList();
    
    if(strProcessName.equals(""))
        list = EgovSysInfo.getProcessId();
    else 
        list = EgovSysInfo.getProcessId(strProcessName);
    
%>
<!-- 결과화면 시작 -->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovProcsInfo">
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" value="reqcom078">
<table class="table-line">

<%  if ("WINDOWS".equals(Globals.OS_TYPE)) { %>
    <tr>
        <td align="center">이미지 이름</td>
        <td align="center">PID</td>
        <td align="center">세션 이름</td>
        <td align="center">세션#</td>
        <td align="center">메모리 사용</td>
    </tr>
<%  } else if ("UNIX".equals(Globals.OS_TYPE)) { %>
    <tr>
        <td align="center">PID</td>
        <td align="center">GROUP</td>
        <td align="center">USER</td>
        <td align="center">COMMAND</td>
        <td align="center">DIR</td>
    </tr>
<%  } %>
    
<% 
   if ("WINDOWS".equals(Globals.OS_TYPE)) { 
	    String[] result;
	
	    Iterator<String[]> it = list.iterator();
	
	    int i = 0;
	
	    while (it.hasNext()) {
	    %><tr><%
	        result = it.next();
	        for(i=0; i<result.length;i++) {
	       %><td><%=result[i]%></td><%
	        }
	    %></tr><%
	    }
   }else{
	   String result="";
	    
       Iterator it = list.iterator();
   
       int i = 0;
   
       while (it.hasNext()) {
       %><tr><%
           result = (String)it.next();
           String[] resultArr = result.split(" "); 
           for(i=0; i<resultArr.length;i++) {
          %><td><%=resultArr[i]%></td><%
           }
       %></tr><%
       }
   }

%>
    <tr>
        <td colspan="5" align="center"><input type = "button"  value="테스트 데이터 입력화면으로 이동" onclick="fm.submit()"></td>
    </tr>
</table>
</form>
<!--  결과화면 끝 -->
<%
}
%>
