<%-- *******************************************************
* Filename : EgovDrctryCreat.jsp
* Class    :
* Function : 디렉토리(파일) 생성 기능 확인용  JSP     
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
    //log.debug("EgovDrctryCreat.jsp READY");
%>
<!-- 준비화면  시작-->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryCreat">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom077">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리(파일)를 생성합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        생성할 디렉토리의 절대경로를 입력하면 해당되는 디렉토리를 생성<br>
        기존에 디렉토리가 존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        생성경로<input type="text" name="dirCreationPath"  title="생성경로">예) c:/data/home/dir1, /data/home/dir1<br>
        </td>
    </tr>
    <tr>
        <td>
        생성할 파일의 절대경로를 입력하면 해당되는 파일를 생성<br>
        기존에 파일이 존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        생성경로<input type="text" name="fileCreationPath" title="생성경로">예) c:/data/home/file1.txt, /data/home/file1.txt<br>
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
    //log.debug("EgovDrctryCreat.jsp ACTION");
    //실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인
    String dirCreationPath  = safeGetParameter(request,"dirCreationPath");
    String fileCreationPath = safeGetParameter(request,"fileCreationPath");
    
    String result1 = EgovFileTool.createNewDirectory(dirCreationPath);
    String result2 = EgovFileTool.createNewFile(fileCreationPath);
%>

<!-- 결과화면 시작 -->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryCreat">
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" value="reqcom077">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리(파일)를 생성합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        생성할 디렉토리의 절대경로를 입력하면 해당되는 디렉토리를 생성<br>
        기존에 디렉토리가 존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        생성경로<input type="text" name="dirCreationPath" value="<%=dirCreationPath %>" title="생성경로">예) c:/data/home/dir1, /data/home/dir1<br>
        결과 : <%=result1 %>
        </td>
    </tr>
    <tr>
        <td>
        생성할 파일의 절대경로를 입력하면 해당되는 파일를 생성<br>
        기존에 파일이 존재하는 경우 별도 작업 없이 블랭크를 리턴<br>
        생성경로<input type="text" name="fileCreationPath" value="<%=fileCreationPath %>" title="생성경로">예) c:/data/home/file1.txt, /data/home/file1.txt<br>
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
