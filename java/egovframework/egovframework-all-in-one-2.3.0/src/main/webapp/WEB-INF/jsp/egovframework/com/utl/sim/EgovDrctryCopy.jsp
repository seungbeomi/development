<%-- *******************************************************
* Filename : EgovDrctryCopy.jsp
* Class    :
* Function : 디렉토리 복사 기능 확인용  JSP     
*
* @version   1.0
* @author    JJY
*******************************************************  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.sim.service.EgovFileTool"  %>
<%@page import="egovframework.com.cmm.service.Globals"  %>
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
    //log.debug("EgovDrctryCopy.jsp READY");
%>
<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js" ></script>
<!-- 준비화면  시작-->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryCopy">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom075">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리를 복사합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        복사할 원본디렉토리와 새로 생성될 대상디렉토리를 입력하여 복사합니다.<br>
        원본디렉토리가 존재하지 않는 경우 리턴 작업없이 종료, 대상디렉토리가 없는 경우는 생성하여 복사함<br>
        원본경로<input type="text" name="dirOriginalPath1" title="원본경로">예) c:/data/home/dir1, /data/home/dir1<br>
        대상경로<input type="text" name="dirTargetPath1" title="대상경로">예) c:/data/home/dir2, /data/home/dir2<br>
        </td>
    </tr>
    <tr>
        <td>
        복사할 원본디렉토리와 새로 생성될 대상디렉토리를 입력하여 복사합니다.<br>
        원본디렉토리가 존재하지 않는 경우 리턴 작업없이 종료, 대상디렉토리가 없는 경우는 생성하여 복사함<br>
        원본경로<input type="text" name="dirOriginalPath2" title="원본경로">예) c:/data/home/dir1, /data/home/dir1<br>
        대상경로<input type="text" name="dirTargetPath2" title="대상경로">예) c:/data/home/dir2, /data/home/dir2<br>
        복사조건-생성일자-시작일<input type="text" name="fromDate2" title="복사조건-생성일자-시작일">예) 20090101<br>
        복사조건-생성일자-종료일<input type="text" name="toDate2" title="복사조건-생성일자-종료일">예) 20090228<br>
        </td>
    </tr>
    <tr>
        <td>
        복사할 원본디렉토리와 새로 생성될 대상디렉토리를 입력하여 복사합니다.<br>
        원본디렉토리가 존재하지 않는 경우 리턴 작업없이 종료, 대상디렉토리가 없는 경우는 생성하여 복사함<br>
        원본경로<input type="text" name="dirOriginalPath3" title="원본경로" >예) c:/data/home/dir1, /data/home/dir1<br>
        대상경로<input type="text" name="dirTargetPath3" title="대상경로">예) c:/data/home/dir2, /data/home/dir2<br>
        생성자<input type="text" name="owner3" title="생성자">예) user01<br>
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
    //log.debug("EgovDrctryCopy.jsp ACTION");
    //실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인
    String dirOriginalPath1  = safeGetParameter(request,"dirOriginalPath1");
    String dirTargetPath1    = safeGetParameter(request,"dirTargetPath1");
    String dirOriginalPath2  = safeGetParameter(request,"dirOriginalPath2");
    String dirTargetPath2    = safeGetParameter(request,"dirTargetPath2");
    String fromDate2         = safeGetParameter(request,"fromDate2");
    String toDate2           = safeGetParameter(request,"toDate2");
    String dirOriginalPath3  = safeGetParameter(request,"dirOriginalPath3");
    String dirTargetPath3    = safeGetParameter(request,"dirTargetPath3");
    String owner3            = safeGetParameter(request,"owner3");
    
    boolean result1 = EgovFileTool.copyDirectory(dirOriginalPath1, dirTargetPath1);
    boolean result2 = EgovFileTool.copyDirectory(dirOriginalPath2, dirTargetPath2, fromDate2, toDate2);
    boolean result3 = EgovFileTool.copyDirectory(dirOriginalPath3, dirTargetPath3, owner3);
%>

<!-- 결과화면 시작 -->
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryCopy">
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" value="reqcom075">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리를  복사합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        복사할 원본디렉토리와 새로 생성될 대상디렉토리를 입력하여 복사합니다.<br>
        원본디렉토리가 존재하지 않는 경우 리턴 작업없이 종료, 대상디렉토리가 없는 경우는 생성하여 복사함<br>
        원본경로<input type="text" name="dirOriginalPath1" value="<%=dirOriginalPath1 %>" title="원본경로">예) c:/data/home/dir1, /data/home/dir1<br>
        대상경로<input type="text" name="dirTargetPath1" value="<%=dirTargetPath1 %>" title="대상경로">예) c:/data/home/dir2, /data/home/dir2<br>
        결과 : <%=result1 %>
        </td>
    </tr>
    <tr>
        <td>
        복사할 원본디렉토리와 새로 생성될 대상디렉토리를 입력하여 복사합니다.<br>
        원본디렉토리가 존재하지 않는 경우 리턴 작업없이 종료, 대상디렉토리가 없는 경우는 생성하여 복사함<br>
        원본경로<input type="text" name="dirOriginalPath2"  value="<%=dirOriginalPath2 %>" title="원본경로">예) c:/data/home/dir1, /data/home/dir1<br>
        대상경로<input type="text" name="dirTargetPath2"  value="<%=dirTargetPath2 %>" title="대강경로">예) c:/data/home/dir2, /data/home/dir2<br>
        복사조건-생성일자-시작일<input type="text" name="fromDate2"  value="<%=fromDate2 %>" title="복사조건-생성일자-시작일">예) 20090101<br>
        복사조건-생성일자-종료일<input type="text" name="toDate2"  value="<%=toDate2 %>" title="복사조건-생성일자-종료일">예) 20090228<br>
        결과 : <%=result2 %>
        </td>
    </tr>
    <tr>
        <td>
        복사할 원본디렉토리와 새로 생성될 대상디렉토리를 입력하여 복사합니다.<br>
        원본디렉토리가 존재하지 않는 경우 리턴 작업없이 종료, 대상디렉토리가 없는 경우는 생성하여 복사함<br>
        원본경로<input type="text" name="dirOriginalPath3"  value="<%=dirOriginalPath3 %>" title="원본경로">예) c:/data/home/dir1, /data/home/dir1<br>
        대상경로<input type="text" name="dirTargetPath3"  value="<%=dirTargetPath3 %>" title="대상경로">예) c:/data/home/dir2, /data/home/dir2<br>
        생성자<input type="text" name="owner3"  value="<%=owner3 %>" title="생성자">예) user01<br>
        결과 : <%=result3 %>
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
