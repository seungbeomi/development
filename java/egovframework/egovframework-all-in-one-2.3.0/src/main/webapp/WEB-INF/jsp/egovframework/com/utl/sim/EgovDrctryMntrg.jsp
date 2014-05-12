<%-- *******************************************************
* Filename : EgovDrctryMntrg.jsp
* Class    :
* Function : 디렉토리 감시 기능 확인용  JSP     
*
* @version   1.0
* @author    JJY
*******************************************************  --%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="java.util.StringTokenizer"  %>
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
    //log.debug("EgovDrctryMntrg.jsp READY");
%>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!-- 준비화면  시작-->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryMntrg">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom074">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리를 감시합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        감시할 디렉토리를 입력하여 감시합니다.<br>
        신규파일이 생성되는 경우 로그파일에 로그정보 입력<br>
        대상경로<input type="text" name="dirTargetPath1" >예) c:/data/home/dir1, /data/home/dir1<br>
        </td>
    </tr>
    <tr>
        <td>
        감시중단할 디렉토리를 입력하여 중단합니다.<br>
        대상경로<input type="text" name="dirTargetPath2" >예) c:/data/home/dir1, /data/home/dir1<br>
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
    //log.debug("EgovDrctryMntrg.jsp ACTION");
    //실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인
    String dirTargetPath1    = safeGetParameter(request,"dirTargetPath1");
    String logFile1          = safeGetParameter(request,"logFile1");
    String dirTargetPath2    = safeGetParameter(request,"dirTargetPath2");
    String logFile2          = safeGetParameter(request,"logFile2");
    
    boolean result1 = EgovFileTool.startDirectoryMonitering(dirTargetPath1);
    StringBuffer logInfo1 = EgovFileTool.getDirectoryMoniteringInfo(dirTargetPath1);
    boolean result2 = EgovFileTool.stopDirectoryMonitering(dirTargetPath2);
    StringBuffer logInfo2 = EgovFileTool.getDirectoryMoniteringInfo(dirTargetPath2);
   
    // 알고리즘
    // :  감시경로, 감기경로에 대한 기록용 로그파일경로, 이벤트실행프로그램(로그기록)
    // 1. 감시경로에 대한 감시 시작한다..
    //    감시시작시 로그파일에 대한 하위목록을 보관한다.
    //    감시시작과 동시에 대상디렉토리명에 해당하는 로그파일을 생성한다.
    //    만약 같은이름의 로그파일이 존재한다면 추가적인 감시를 시작하지 않는다.
    // 2. 1분간격으로 감시하며 최초목록과 현재목록을 비교한다.
    // 3. 최초목록과 현재 목록에 차이가 있는 경우 이벤트를 호출한다.
    // 4. 이벤트호출시 로그파일에 차이정보를 기록한다.
    // 5. 모니터링을 종료하고자 하는 경우는 로그파일을 삭제한다.
%>

<!-- 결과화면 시작 -->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryMntrg">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom074">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr>
    <tr>
        <td>
        <br>디렉토리를 감시합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        감시할 디렉토리를 입력하여 감시합니다.<br>
        신규파일이 생성되는 경우 로그파일에 로그정보 입력<br>
        대상경로<input type="text" name="dirTargetPath1" value="<%=dirTargetPath1 %>">예) c:/data/home/dir1, /data/home/dir1<br>
       결과 : 감시기능 시작여부:<%=result1 %><br>
       로그정보<br>
       <%       
       StringTokenizer sto = new StringTokenizer(logInfo1.toString(),"\n");
       while(sto.hasMoreElements()) {
           out.println(sto.nextElement().toString()+"<br>");
       }
       %>
        </td>
    </tr>
    <tr>
        <td>
        감시중단할 디렉토리를 입력하여 중단합니다.<br>
        대상경로<input type="text" name="dirTargetPath2" value="<%=dirTargetPath2 %>">예) c:/data/home/dir1, /data/home/dir1<br>
        결과 : 감시기능 종료여부:<%=result2 %><br>
        로그정보<br>
       <%       
       sto = new StringTokenizer(logInfo2.toString(),"\n");
       while(sto.hasMoreElements()) {
           out.println(sto.nextElement().toString()+"<br>");
       }
       %>
        </td>
    </tr>
    <tr>
        <td><input type = "button" method="post"  value="테스트 데이터 입력화면으로 이동" onclick='fm.execFlag.value="READY";fm.submit();'>
            <input type = "button" method="post"  value="모니터링 중단" onclick="fm.submit()">
        </td>
    </tr>
</table>
</form>
<!--  결과화면 끝 -->
<%
}
%>
