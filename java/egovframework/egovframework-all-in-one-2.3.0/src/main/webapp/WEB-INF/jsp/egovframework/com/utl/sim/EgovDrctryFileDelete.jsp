<%-- *******************************************************
* Filename : EgovDrctryFileDelete.jsp
* Class    :
* Function : 디렉토리(파일) 삭제 기능 확인용  JSP     
*
* @version   1.0
* @author    JJY
*******************************************************  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
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
    //log.debug("EgovDrctryFileDelete.jsp READY");
%>
<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js" ></script>
<!-- 준비화면  시작-->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryFileDelete">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom076">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리(파일)를 삭제합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        삭제할 디렉토리의 절대경로를 입력하면 해당되는 디렉토리를 삭제<br>
        삭제할 디렉토리가 비존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        삭제경로<input type="text" name="dirDeletePath" title="삭제경로">예) c:/data/home/dir1, /data/home/dir1<br>
        
        </td>
    </tr>
    <tr>
        <td>
        삭제할 디렉토리의 절대경로를 입력하면 해당되는 디렉토리를 삭제<br>
        삭제할 디렉토리가 비존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        삭제경로<input type="text" name="dirDeletePath3" title="삭제경로">예) c:/data/home/dir1, /data/home/dir1<br>
        삭제조건-생성일자-시작일<input type="text" name="fromDate3" title="삭제조건-생성일자-시작일">예) 20090101<br>
        삭제조건-생성일자-종료일<input type="text" name="toDate3" title="삭제조건-생성일자-종료일">예) 20090228<br>
        </td>
    </tr>
    <tr>
        <td>
        삭제할 디렉토리의 절대경로를 입력하면 해당되는 디렉토리를 삭제<br>
        삭제할 디렉토리가 비존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        삭제경로<input type="text" name="dirDeletePath4" title="삭제경로">예) c:/data/home/dir1, /data/home/dir1<br>
        삭제조건-생성자<input type="text" name="owner4" title="삭제조건-생성자">예) user01<br>
        </td>
    </tr>
    <tr>
        <td>
        삭제할 파일의 절대경로를 입력하면 해당되는 파일를 삭제<br>
        기존에 파일이 비존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        삭제경로<input type="text" name="fileDeletePath" title="삭제경로">예) c:/data/home/file1.txt, /data/home/file1.txt<br>
        </td>
    </tr>
    <tr>
        <td><input type = "button" method="post"  value="실행!" 
        onclick="if(isDate(document.fm.fromDate3.value, '조건시작일') & isDate(document.fm.toDate3.value,'조건종료일'))fm.submit()">
        </td>
    </tr>
</table>
</form>
<!--  준비화면 끝 -->

<%    
}else if(execFlag.equals("ACTION")){
    // 실행을 화면 출력
    //log.debug("EgovDrctryFileDelete.jsp ACTION");
    //실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인
    String dirDeletePath  = safeGetParameter(request,"dirDeletePath");
    String fileDeletePath = safeGetParameter(request,"fileDeletePath");    
    String dirDeletePath3  = safeGetParameter(request,"dirDeletePath3");
    String fromDate3 = safeGetParameter(request,"fromDate3");
    String toDate3 = safeGetParameter(request,"toDate3");
    String dirDeletePath4  = safeGetParameter(request,"dirDeletePath4");
    String owner4 = safeGetParameter(request,"owner4");
   
    String result1 = EgovFileTool.deleteDirectory(dirDeletePath);
    String result2 = EgovFileTool.deleteFile(fileDeletePath);
    String result3 = EgovFileTool.deleteDirectory(dirDeletePath3, fromDate3, toDate3);
    String result4 = EgovFileTool.deleteDirectory(dirDeletePath4, owner4);
%>

<!-- 결과화면 시작 -->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryFileDelete">
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" value="reqcom076">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리(파일)를 삭제합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        삭제할 디렉토리의 절대경로를 입력하면 해당되는 디렉토리를 삭제<br>
        삭제할 디렉토리가 비존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        삭제경로<input type="text" name="dirCreationPath" value="<%=dirDeletePath %>" title="삭제경로">예) c:/data/home/dir1, /data/home/dir1<br>
        결과 : <%=result1 %>
        </td>
    </tr>
    <tr>
        <td>
        삭제할 디렉토리의 절대경로를 입력하면 해당되는 디렉토리를 삭제<br>
        삭제할 디렉토리가 비존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        삭제경로<input type="text" name="dirDeletePath3" value="<%=dirDeletePath3 %> title="삭제경로">예) c:/data/home/dir1, /data/home/dir1<br>
        삭제조건-생성일자-시작일<input type="text" name="fromDate3" value="<%=fromDate3 %>" title="삭제조건-생성일자-시작일">예) 20090101<br>
        삭제조건-생성일자-종료일<input type="text" name="toDate3" value="<%=toDate3 %>" title="삭제조건-생성일자-종료일">예) 20090228<br>
        결과 : <%=result3 %>
        </td>
    </tr>
    <tr>
        <td>
        삭제할 디렉토리의 절대경로를 입력하면 해당되는 디렉토리를 삭제<br>
        삭제할 디렉토리가 비존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        삭제경로<input type="text" name="dirDeletePath4" value="<%=dirDeletePath4 %>" title="삭제경로">예) c:/data/home/dir1, /data/home/dir1<br>
        삭제조건-생성자<input type="text" name="owner4" value="<%=owner4 %>" title="삭제조건-생성자">예) user01<br>
        결과 : <%=result4 %>
        </td>
    </tr>  
    <tr>
        <td>
        삭제할 파일의 절대경로를 입력하면 해당되는 파일를 삭제<br>
        기존에 파일이 비존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        삭제경로<input type="text" name="fileCreationPath" value="<%=fileDeletePath %>" title="삭제경로">예) c:/data/home/file1.txt, /data/home/file1.txt<br>
        결과 : <%=result2 %>
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
