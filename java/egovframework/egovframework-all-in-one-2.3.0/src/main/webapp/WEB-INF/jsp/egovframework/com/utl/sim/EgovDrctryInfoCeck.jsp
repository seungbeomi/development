<%-- *******************************************************
* Filename : EgovDrctryInfoCeck.jsp
* Class    :
* Function : 디렉토리 속성 체크 기능 확인용  JSP     
*
* @version   1.0
* @author    JJY
*******************************************************  --%>
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
    //log.debug("EgovDrctryInfoCeck.jsp READY");
%>
<!-- 준비화면  시작-->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryInfoCeck">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom078">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리 속성 정보를 체크합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        대상디렉토리의 속성정보를 출력, 미확인시는 블랭크<br>
        타겟경로<input type="text" name="targetDirPath1" >예) c:/data/home, /data/home<br>
        </td>
    </tr>
    <tr>
        <td>
        결과
        </td>
    </tr>
    <tr>
        <td>
        디렉토리 및 파일의 명 : 
        </td>
    </tr>
    <tr>
        <td>
        생성일자: 
        </td>
    </tr>
    <tr>
        <td>
        생성자: 
        </td>
    </tr>
    <tr>
        <td>
        권한: 읽기 , 쓰기
        </td>
    </tr>
    <tr>
        <td>
        사이즈정보 : 
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
    //log.debug("EgovDrctryInfoCeck.jsp ACTION");
    String targetDirPath1 = safeGetParameter(request,"targetDirPath1");
    
    //실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인
    String directoryName1    = EgovFileTool.getName(targetDirPath1);
    String lastModifiedDate1 = EgovFileTool.getLastModifiedDateFromFile(targetDirPath1);
    String owner1      = EgovFileTool.getOwner(targetDirPath1);
    boolean canRead1   = EgovFileTool.canRead(targetDirPath1);
    boolean canWrite1  = EgovFileTool.canWrite(targetDirPath1);
    String roleStr  = EgovFileTool.getAccess(targetDirPath1);
    long dirSize1   = EgovFileTool.getDirectorySize(targetDirPath1);
%>
<!-- 결과화면 시작 -->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryInfoCeck">
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" value="reqcom078">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리 속성 정보를 체크합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        대상디렉토리의 속성정보를 출력, 미확인시는 블랭크<br>
        타겟경로<input type="text" name="targetDirPath1" value="<%=targetDirPath1 %>" >예) c:/data/home, /data/home<br>
        </td>
    </tr>
    <tr>
        <td>
        결과
        </td>
    </tr>
    <tr>
        <td>
        디렉토리 및 파일의 명 : <%=directoryName1 %><br>
        </td>
    </tr>
    <tr>
        <td>
        생성일자: <%=lastModifiedDate1 %>
        </td>
    </tr>
    <tr>
        <td>
        생성자: <%=owner1 %>
        </td>
    </tr>
    <tr>
        <td>
        권한: 읽기 -> <%=canRead1 %> , 쓰기(디렉토리에서는 의미 없음) -> <%=canWrite1 %><br>
        사용자,그룹,타계정 권한정보 : <%=roleStr %>
        </td>
    </tr>
    <tr>
        <td>
        사이즈정보(byte): <%=dirSize1 %> 
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
