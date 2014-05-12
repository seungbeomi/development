<%-- *******************************************************
* Filename : EgovDrctryExst.jsp
* Class    :
* Function : 디렉토리 존재 체크 기능 확인용  JSP     
*
* @version   1.0
* @author    JJY
*******************************************************  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.sim.service.EgovFileTool"  %>
<%@page import="java.util.ArrayList"  %>
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
    //log.debug("EgovFileTool.jsp READY");
%>
<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js" ></script>
<!-- 준비화면  시작-->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryExst">
<input type = "hidden" name="execFlag" value="ACTION">
<input type = "hidden" name="cmdStr" value="reqcom082">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr> 
    <tr>
        <td>
        <br>디렉토리존재를 체크합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        대상디렉토리가 존재하면 경로출력, 존재하지 않으면 블랭크<br>
    public String getExistDirectory(String targetDirPath)<br>
        타겟경로<input type="text" name="targetDirPath1" title="타겟경로">
        </td>
    </tr> 
    <tr>
        <td>
        하위디렉토리에 타겟경로의 디렉토리가 존재하면 경로출력, 존재하지 않으면 블랭크<br>
    public ArrayList getExistDirectory(String baseDirPath, String targetDirPath, int dirCnt)<br>
        기본경로<input type="text" name="baseDirPath2"  title="기본경로">예) c:/data/home, /data/home<br>
        타겟경로<input type="text" name="targetDirPath2" title="타겟경로">예) conf<br>
        찾을갯수<input type="text" name="dirCnt2" title="찾을갯수">예) 1, 100
        </td>
    </tr>
    <tr>
        <td>
        대상디렉토리의 최종수정일자가 조건범위안에 포함되면 경로출력, 존재하지 않으면 블랭크<br>
    public String getExistDirectory(String targetDirPath, String fromDate, String toDate)<br>
        타겟경로<input type="text" name="targetDirPath3" title="타겟경로">예) c:/data/home, /data/home<br>
        조건-시작일자<input type="text" name="fromDate3" title="조건-시작일자">예) 20090101<br>
        조건-종료일자<input type="text" name="toDate3" title="조건-종료일자">예) 20090131
        </td>
    </tr>
    <tr>
        <td>
        대상디렉토리의 생성자가 일치하면 경로 출력, 일치하지 않으면 블랭크<br>
    public String getExistDirectory(String targetDirPath, String ownerName)<br>
        타겟경로<input type="text" name="targetDirPath4" title="타겟경로">예) c:/data/home, /data/home<br>
        생성자<input type="text" name="ownerName4" title="생성자">예) user01
        </td>
    </tr>
    <tr>
        <td><input type = "button" method="post"  value="실행!" 
        onclick="if(isDate(document.fm.fromDate3.value, '조건시작일') & isDate(document.fm.toDate3.value,'조건종료일')) fm.submit()">
        </td>
    </tr>
</table>
</form>
<!--  준비화면 끝 -->

<%    
}else if(execFlag.equals("ACTION")){
	// 실행을 화면 출력
    //log.debug("EgovDrctryExst.jsp ACTION");
	//실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인
	String targetDirPath1 = safeGetParameter(request,"targetDirPath1");

	String baseDirPath2 = safeGetParameter(request,"baseDirPath2");
	String targetDirPath2 = safeGetParameter(request,"targetDirPath2");
	String dirCnt2 = safeGetParameter(request,"dirCnt2");
	if (dirCnt2==null || dirCnt2.equals("")){
		dirCnt2="0";
	}
	
	String targetDirPath3 = safeGetParameter(request,"targetDirPath3");
    String fromDate3      = safeGetParameter(request,"fromDate3");
	String toDate3        = safeGetParameter(request,"toDate3");
	
	String targetDirPath4 = safeGetParameter(request,"targetDirPath4");
    String ownerName4     = safeGetParameter(request,"ownerName4");
	
    // 요소기술 유틸함수 호출
	boolean result1 = EgovFileTool.getExistDirectory(targetDirPath1);
	ArrayList result2 = EgovFileTool.getExistDirectory(baseDirPath2, targetDirPath2, Integer.parseInt(dirCnt2) );
	boolean result3 = EgovFileTool.getExistDirectory(targetDirPath3, fromDate3, toDate3);
	boolean result4 = EgovFileTool.getExistDirectory(targetDirPath4, ownerName4);
    
%>

<!-- 결과화면 시작 -->
<form name="fm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovDrctryExst">
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" value="reqcom082">
<table class="table-line">
    <tr>
        <td>
                기능설명
        </td>
    </tr> 
    <tr>
        <td>
        <br>디렉토리존재를 체크합니다.<br><br>
        </td>
    </tr>
    <tr>
        <td>
        대상디렉토리가 존재하면 경로출력, 존재하지 않으면 블랭크<br>
    public String getExistDirectory(String targetDirPath)<br>
        타겟경로<input type="text" name="targetDirPath1" value=<%= targetDirPath1 %> title="타겟경로">예) c:/data/home, /data/home<br>
        결과:<%=result1 %>
        </td>
    </tr>
    <tr>
        <td>
        하위디렉토리에 타겟경로의 디렉토리가 존재하면 경로출력, 존재하지 않으면 블랭크<br>
    public ArrayList getExistDirectory(String baseDirPath, String targetDirPath, int dirCnt)<br>
        기본경로<input type="text" name="baseDirPath2"   value=<%= baseDirPath2 %> title="기본경로">예) c:/data/home, /data/home<br>
        타겟경로<input type="text" name="targetDirPath2" value=<%= targetDirPath2 %> title="타겟경로">예) conf<br>
        찾을갯수<input type="text" name="dirCnt2" value=<%=dirCnt2 %> title="찾을갯수"><br>
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
        <td>
        대상디렉토리의 최종수정일자가 조건범위안에 포함되면 경로출력, 포함되지 않으면 블랭크<br>
    public String getExistDirectory(String targetDirPath, String fromDate, String toDate)<br>
        타겟경로<input type="text" name="targetDirPath3" value=<%= targetDirPath3 %> title="타겟경로">예) c:/data/home, /data/home<br>
        조건-시작일자<input type="text" name="fromDate3"  value=<%= fromDate3 %> title="조건-시작일자">예) 20090101<br>
        조건-종료일자<input type="text" name="toDate3"    value=<%= toDate3 %> title="조건-종료일자">예) 20090131<br>
        결과:<%=result3 %>
        </td>
    </tr>
    <tr>
        <td>
        대상디렉토리의 생성자가 일치하면 경로 출력, 일치하지 않으면 블랭크<br>
    public String getExistDirectory(String targetDirPath, String ownerName)<br>
        타겟경로<input type="text" name="targetDirPath4" value=<%= targetDirPath4 %> title="타겟경로">예)c:/data/home, /data/home<br>
        생성자<input type="text" name="ownerName4" value=<%= ownerName4 %> title="생성자">예) user01<br>
        결과:<%=result4 %>
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
