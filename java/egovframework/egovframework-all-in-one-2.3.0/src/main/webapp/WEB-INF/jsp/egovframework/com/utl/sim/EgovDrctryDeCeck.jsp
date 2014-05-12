<%-- *******************************************************
* Filename : EgovDrctryDeCeck.jsp
* Class    :
* Function : 디렉토리 일자 체크 기능 확인용  JSP     
*
* @version   1.0
* @author    JJY
*******************************************************  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="java.util.ArrayList"  %>
<%@page import="egovframework.com.utl.sim.service.EgovFileTool"  %>
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
    //log.debug("EgovDrctryDeCeck.jsp READY");
%>
<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js" ></script>
<!-- 준비화면  시작-->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryDeCeck">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom081">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리 생성일자 정보를 체크합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        대상디렉토리의 생성일자를 출력, 미확인시는 블랭크<br>
        타겟경로<input type="text" name="targetDirPath1" title="타겟경로">예) c:/data/home, /data/home<br>
        </td>
    </tr>
    <tr>
        <td>
        기본경로이하의 디렉토리 중 생성일자(최종수정일자)가 조건범위 안에 포함되면 경로 출력, 존재하지 않으면 블랭크<br>
        타겟경로<input type="text" name="baseDirPath2" title="타겟경로">예) c:/data/home, /data/home<br>
        조건-시작일자<input type="text" name="fromDate2" title="조건-시작일자">예) 20090101<br>
        조건-종료일자<input type="text" name="toDate2" title="조건-종료일자">예) 20090131
        </td>
    </tr>
    <tr>
        <td><input type = "button" method="post"  value="실행!" 
        onclick="if(isDate(document.fm.fromDate2.value, '조건시작일') & isDate(document.fm.toDate2.value,'조건종료일')) fm.submit()">
        </td>
    </tr>
</table>
</form>
<!--  준비화면 끝 -->

<%    
}else if(execFlag.equals("ACTION")){
    // 실행을 화면 출력
    //log.debug("EgovDrctryDeCeck.jsp ACTION");
    //실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인
    String targetDirPath1 = safeGetParameter(request,"targetDirPath1");
    String baseDirPath2 = safeGetParameter(request,"baseDirPath2");
    String fromDate2      = safeGetParameter(request,"fromDate2");
    String toDate2        = safeGetParameter(request,"toDate2");
    
    String result1 = EgovFileTool.getLastModifiedDateFromFile(targetDirPath1);
    ArrayList result2 = new ArrayList();
    result2 = EgovFileTool.getLastDirectoryForModifiedDate(baseDirPath2, fromDate2, toDate2);
    // 요소기술 유틸함수 호출
    //String result1 = drctryInfo.getLastModifiedDate(targetDirPath1);
    //ArrayList result2 = drctryInfo.getListAsLastModifiedDate(baseDirPath2, fromDate2, toDate2);
    
%>

<!-- 결과화면 시작 -->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryDeCeck">
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" value="reqcom081">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리 생성일자 정보를 체크합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        대상디렉토리의 생성일자를 출력, 미확인시는 블랭크<br>
        타겟경로<input type="text" name="targetDirPath1" value="<%=targetDirPath1 %>" title="타겟경로">예) c:/data/home, /data/home<br>
        결과:<%=result1 %>
        </td>
    </tr>
    <tr>
        <td>
        기본경로이하의 디렉토리 중 생성일자(최종수정일자)가 조건범위 안에 포함되면 경로 출력, 존재하지 않으면 블랭크<br>
        타겟경로<input type="text" name="baseDirPath2" value="<%=baseDirPath2 %>" title="타겟경로">예) c:/data/home, /data/home<br>
        조건-시작일자<input type="text" name="fromDate2" value="<%=fromDate2 %>" title="조건-시작일자">예) 20090101<br>
        조건-종료일자<input type="text" name="toDate2" value="<%=toDate2 %>" title="조건-종료일자">예) 20090131<br>
        결과:<% for(int i = 0 ; i<result2.size() ; i++){ 
               out.println((String)result2.get(i));
               if(i>=0 && i< result2.size()-1){
                   out.print(", ");
               }
           }
        %>
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
