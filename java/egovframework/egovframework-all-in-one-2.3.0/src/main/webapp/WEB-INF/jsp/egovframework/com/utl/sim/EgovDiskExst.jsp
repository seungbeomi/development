<%-- *******************************************************
* Filename : EgovDiskExst.jsp
* Class    :
* Function : 디스크존재 확인용  JSP     
*
* @version   1.0
* @author    JJY
*******************************************************  --%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="java.util.ArrayList"  %>
<%@page import="egovframework.com.utl.sim.service.EgovSysInfo"  %>
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
}%>
<%
if(execFlag.equals("READY")){
    // 실행을 위한 화면 준비
    //log.debug("EgovDiskExst.jsp READY");
%>
<!-- 준비화면  시작-->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDiskExst">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom085">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디스크존재정보를 체크합니다.(UNIX)<br><br>
        </td>
    </tr>
    <tr>
        <td>
        diks, cdrom, usb 중 선택하여 입력<br>
        디스크명<input type="text" name="diskName" title="디스크명">
        </td>
    </tr> 
    <tr>
        <td><input type = "button" method="post"  value="실행!" onclick="fm.submit()">
        </td>
    </tr>
</table>
</form>
<!--  준비화면 끝 -->

<%    
}else if(execFlag.equals("ACTION")){
    // 실행을 화면 출력
    //log.debug("EgovDiskExst.jsp ACTION");
    //실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인
    String diskName = safeGetParameter(request,"diskName");
    ArrayList result = EgovSysInfo.getExistDisk(diskName);
%>

<!-- 결과화면 시작 -->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDiskExst">
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" value="reqcom085">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디스크존재정보를 체크합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        result: <%= diskName%> 디스크장치 갯수 : 
            <table  border="1">
        <% for(int i = 0 ; i<result.size() ; i++){ 
               if( i==0 || i%6==0 ) out.println("<tr>");
               out.println("<td>"+(String)result.get(i)+"</td>");
               if( i==0 || i%6==0 ) out.println("</tr>");
           }
        %>
            </table>
        </td>
    </tr>
    <tr>
        <td><input type = "button" method="post"  value="테스트 데이터 입력화면으로 이동" onclick="fm.submit()">
        </td>
    </tr>
</table>
</form>
<!--  결과화면 끝 -->
<%
}
%>
