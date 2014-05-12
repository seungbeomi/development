<%-- *******************************************************
* Filename : EgovDiskCpcty.jsp
* Class    :
* Function : 디스크 용량정보 확인용  JSP     
*
* @version   1.0
* @author    JJY
*******************************************************  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    //log.debug("EgovDiskCpcty.jsp READY");
%>
<!-- 준비화면  시작-->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDiskCpcty">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom084">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디스크 용량정보를 체크합니다.<br><br>
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
    //log.debug("EgovDiskCpcty.jsp ACTION");
    
    //실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인 -> 결과정보구성(전체사이즈, 사용가능사이즈, 사용%, 마운트위치정보)
    ArrayList result = EgovSysInfo.getDiskCapacity();
    
%>

<!-- 결과화면 시작 -->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDiskCpcty">
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" value="reqcom084">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디스크 용량정보를 체크합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
            <table class="table-line">
                <tr>
                    <td>전체사이즈</td>
                    <td>사용가능사이즈</td>
                    <td>사용%</td>
                    <td>마운트위치정보</td>
                </tr>
                <tr>
        <% for(int i = 0 ; i<result.size() ; i++){ 
               out.println("<td>"+(String)result.get(i)+"</td>");
               if(  i>0 && (i+1)%4==0 ) out.println("</tr><tr>");
           }
        %>
               </tr>
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
