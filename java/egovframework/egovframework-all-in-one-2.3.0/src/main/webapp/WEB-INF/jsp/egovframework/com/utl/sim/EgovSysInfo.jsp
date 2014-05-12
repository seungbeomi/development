<%--
  Class Name : EgovSysInfo.jsp
  Description : 시스템의 정보를 조회하는 JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.02    박지욱          최초 생성
 
    author   : 박지욱
    since    : 2009.02.11
   
--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@ page import="java.util.*"  %>
<%@ page import="java.io.*"  %>
<%@ page import="egovframework.com.utl.sim.service.EgovSysInfo"  %>

<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"></script>
<script type="text/javaScript" language="javascript">
function fnGetFreeMemory() {

	if(isNumber(document.sysForm3.memory.value, "사용할 메모리")){ 		
		document.sysForm3.submit();
	}else{
		return;
	}
}
</script>


<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="SERVER";
}
%>
<%
if(execFlag.equals("SERVER")){
%>
<!-- 서버 화면  시작-->
<form name="sysForm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovSysInfo">
<input type = "hidden" name="execFlag" value="SERVER_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>서버정보 확인</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="sysForm1.submit()"></td>
    </tr>
</table>
</form>
<!-- 서버 화면 끝 -->
<!-- 시스템 화면  시작-->
<form name="sysForm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovSysInfo">
<input type = "hidden" name="execFlag" value="SYSTEM_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
	<tr>
      <th width="150" height="23" class="required_text" nowrap>시스템정보 확인</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="sysForm2.submit()"></td>
    </tr>
</table>
</form>
<!-- 시스템 화면 끝 -->
<!-- 유효메모리체크 화면  시작-->
<form name="sysForm3" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovSysInfo">
<input type = "hidden" name="execFlag" value="MEMORY_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
	<tr>
      <th width="150" height="23" class="required_text" nowrap>유효메모리 체크</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnGetFreeMemory();"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>사용할 메모리(MB)</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="memory" value="" size="40" style="ime-mode: disabled;"/></td>
    </tr>
</table>
</form>
<!-- 유효메모리체크 화면 끝 -->
<%  
} else if(execFlag.equals("SERVER_ACTION")){
	
	Vector vector = EgovSysInfo.getPrductList();
	
%>
<!-- 서버 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovSysInfo">
<input type = "hidden" name="execFlag" value="SERVER">
<table width="560" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
	<td width="200">서버명</td>
	<td width="200">서버버전</td>
	<td width="80">실행상태</td>
	<td width="80">사용포트</td>
	</tr>
<%
    String str = "";
    for (int j = 0; j < vector.size(); j++) {
        Map map = (Map)vector.elementAt(j);
        String name = (String)map.get("name");
        String version = (String)map.get("version");
        String status = (String)map.get("status");
        String port = (String)map.get("port");
%>	
	<tr>
	<td width="200"><%=name %></td>
	<td width="200"><%=version %></td>
	<td width="80"><%=status %></td>
	<td width="80"><%=port %></td>
	</tr>
<%
    }
%>		
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 서버 결과화면 끝 -->
<%  
} else if(execFlag.equals("SYSTEM_ACTION")){
	
	String hostname = "";
	String osname = "";
	String osversion = "";
	String osprductor = "";
	String processor = "";
	String diskName = "";
	float diskFull = 0;
	float diskUsed = 0;
	float diskFree = 0;
	float moryFull = 0;
	float moryUsed = 0;
	float moryFree = 0;	

	// 호스트명, OS명, OS버전, OS제조사, 프로세서
	hostname = EgovSysInfo.getHostName();
	osname = EgovSysInfo.getOSName();
	osversion = EgovSysInfo.getOSVersion();
	osprductor = EgovSysInfo.getOSPrductor();
	processor = EgovSysInfo.getProcessorID();
	
	// 디스크 용량
	ArrayList list = EgovSysInfo.getDiskName();
	for(int i = 0; i < list.size(); i++) {
		diskName += (String)list.get(i) + " | ";
		diskFull += EgovSysInfo.getDiskFullCpcty((String)list.get(i));
		diskUsed += EgovSysInfo.getDiskUsedCpcty((String)list.get(i));
		diskFree += EgovSysInfo.getDiskFreeCpcty((String)list.get(i));
	}
	if (list.size() == 0) diskName = "윈도우 환경에서는 디스크정보가 지원되지 않습니다";
	
	// 메모리 용량
	moryUsed = EgovSysInfo.getMoryUsedCpcty();
	moryFree = EgovSysInfo.getMoryFreeCpcty();
	moryFull = moryUsed + moryFree;
	
%>
<!-- 시스템 결과화면 시작 -->
<form name="fm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovSysInfo">
<input type = "hidden" name="execFlag" value="SERVER">
<table width="600" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="200" class="title_left">호스트명</td>
        <td><%=hostname %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">OS이름</td>
        <td><%=osname %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">OS버전</td>
        <td><%=osversion %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">OS제조사</td>
        <td><%=osprductor %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">Processor ID</td>
        <td><%=processor %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">디스크명</td>
        <td><%=diskName %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">디스크 전체(Full) 용량(MB)</td>
        <td><%=diskFull %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">디스크 사용(Used) 용량(MB)</td>
        <td><%=diskUsed %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">디스크 유효(Free) 용량(MB)</td>
        <td><%=diskFree %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">메모리 전체(Full) 용량(MB)</td>
        <td><%=moryFull %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">메모리 사용(Used) 용량(MB)</td>
        <td><%=moryUsed %></td>
    </tr>
    <tr>
        <td width="200" class="title_left">메모리 유효(Free) 용량(MB)</td>
        <td><%=moryFree %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 시스템 결과화면 끝 -->
<%  
} else if(execFlag.equals("MEMORY_ACTION")){
	
	boolean result = false;
	long memory = Long.parseLong(request.getParameter("memory"));
	result = EgovSysInfo.checkMoryCpcty(memory);
	
%>
<!-- 유효메모리체크 결과화면 시작 -->
<form name="fm3" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovSysInfo">
<input type = "hidden" name="execFlag" value="SERVER">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">메모리 가용여부</td>
        <td><%=result %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm3.submit()">
</form>
<!-- 유효메모리체크 결과화면 끝 -->
<%
} 
%>