<%--
  Class Name : EgovFileCopy.jsp
  Description : 파일을 다른 디렉토리로 복사하는 JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.01.16    박지욱          최초 생성
 
    author   : 박지욱
    since    : 2009.01.16
   
--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.sim.service.EgovFileTool"  %>
<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"></script>
<script type="text/javaScript" language="javascript">
function fnUpdtCopyForm() {

	if(isDate(document.updtCopyForm.updtFrom.value, "수정기간From")) {
		if (isDate(document.updtCopyForm.updtTo.value, "수정기간To")) {
			document.updtCopyForm.submit();
		} else {
			return;
		}
	}else{
		return;
	}
}

function fnSizeCopyForm() {

	if(isNumber(document.sizeCopyForm.sizeFrom.value, "사이즈From")) {
		if (isNumber(document.sizeCopyForm.sizeTo.value, "사이즈To")) {
			document.sizeCopyForm.submit();
		} else {
			return;
		}
	}else{
		return;
	}
}
</script>

<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="FILE";
}
%>
<%
if(execFlag.equals("FILE")){
%>
<!-- 파일복사 화면  시작-->
<form name="fileCopyForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="FILE_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 1개 복사</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fileCopyForm.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>원본</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="source" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>타겟</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="target" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 파일복사 화면 끝 -->
<!-- 파일목록복사 화면  시작-->
<form name="filesCopyForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="FILES_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 여러개 복사</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="filesCopyForm.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>원본1</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="source1" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>원본2</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="source2" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>원본3</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="source3" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>타겟</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="target" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 파일목록복사 화면 끝 -->
<!-- 확장자별 파일복사 화면  시작-->
<form name="extntCopyForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="EXTNT_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 확장자별 복사</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="extntCopyForm.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>원본</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="source" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>확장자</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="extnt" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>타겟</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="target" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 확장자별 파일복사 화면 끝 -->
<!-- 수정기간내 파일복사 화면  시작-->
<form name="updtCopyForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="UPDT_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 수정기간별 복사</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnUpdtCopyForm();"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>원본</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="source" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>수정기간From(YYYYMMDD)</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="updtFrom" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>수정기간To(YYYYMMDD)</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="updtTo" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>타겟</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="target" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 수정기간내 파일복사 화면 끝 -->
<!-- 사이즈내 파일복사 화면  시작-->
<form name="sizeCopyForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="SIZE_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 사이즈별 복사</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="fnSizeCopyForm();"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>원본</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="source" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>사이즈From(KByte)</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="sizeFrom" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>사이즈To(KByte)</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="sizeTo" value="" size="40" style="ime-mode: disabled;"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>타겟</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="target" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 사이즈내 파일복사 화면 끝 -->
<!-- 생성자별 파일복사 화면  시작-->
<form name="creatCopyForm" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="CREAT_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>파일 생성자별 복사</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="creatCopyForm.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>원본</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="source" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>생성자</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="creator" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>타겟</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="target" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 생성자별 파일복사 화면 끝 -->
<%  
} else if(execFlag.equals("FILE_ACTION")){
	
	String source = request.getParameter("source");
    String target = request.getParameter("target");
    boolean retCopy = false;
	if (source != null && source.length() > 0
		&& target != null && target.length() > 0) {
		retCopy = EgovFileTool.copyFile(source, target);
	}
%>
<!-- 파일복사 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="FILE">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">파일복사 결과</td>
        <td><%=retCopy %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 파일복사 결과화면 끝 -->
<%  
} else if(execFlag.equals("FILES_ACTION")){
	
	String source1 = request.getParameter("source1");
	String source2 = request.getParameter("source2");
	String source3 = request.getParameter("source3");
	String target = request.getParameter("target");
	
	boolean retCopy = false;
	if (source1 != null && source1.length() > 0
		&& source2 != null && source2.length() > 0
		&& source3 != null && source3.length() > 0
		&& target != null && target.length() > 0) {
		String [] sources = { source1, source2, source3 };
		retCopy = EgovFileTool.copyFiles(sources, target);
	}
	
%>
<!-- 파일목록복사 결과화면 시작 -->
<form name="fm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="FILE">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">파일목록복사 결과</td>
        <td><%=retCopy %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 파일목록복사 결과화면 끝 -->
<%  
} else if(execFlag.equals("EXTNT_ACTION")){
	
	String source = request.getParameter("source");
	String extnt = request.getParameter("extnt");
    String target = request.getParameter("target");
    
    boolean retCopy = false;
    if (source != null && source.length() > 0
    	&& extnt != null && extnt.length() > 0
    	&& target != null && target.length() > 0) {
   		retCopy = EgovFileTool.copyFilesByExtnt(source, extnt, target);
   	}
    
%>
<!-- 확장자별 파일복사 결과화면 시작 -->
<form name="fm3" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="FILE">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">확장자별 파일복사 결과</td>
        <td><%=retCopy %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm3.submit()">
</form>
<!-- 확장자별 파일복사 결과화면 끝 -->
<%  
} else if(execFlag.equals("UPDT_ACTION")){
	
	String source = request.getParameter("source");
	String updtFrom = request.getParameter("updtFrom");
	String updtTo = request.getParameter("updtTo");
    String target = request.getParameter("target");
    
    boolean retCopy = false;
    if (source != null && source.length() > 0
    	&& updtFrom != null && updtFrom.length() > 0
    	&& updtTo != null && updtTo.length() > 0
    	&& target != null && target.length() > 0) {
   		retCopy = EgovFileTool.copyFilesByUpdtPd(source, updtFrom, updtTo, target);
   	}
    
%>
<!-- 수정기간내 파일복사 결과화면 시작 -->
<form name="fm4" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="FILE">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">수정기간내 파일복사 결과</td>
        <td><%=retCopy %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm4.submit()">
</form>
<!-- 수정기간내 파일복사 결과화면 끝 -->
<%  
} else if(execFlag.equals("SIZE_ACTION")){
	
	String source = request.getParameter("source");
	String sizeFrom = request.getParameter("sizeFrom");
	String sizeTo = request.getParameter("sizeTo");
    String target = request.getParameter("target");
    
    boolean retCopy = false;
    if (source != null && source.length() > 0
    	&& sizeFrom != null && sizeFrom.length() > 0
    	&& sizeTo != null && sizeTo.length() > 0
    	&& target != null && target.length() > 0) {
   		retCopy = EgovFileTool.copyFilesBySize(source, Long.parseLong(sizeFrom), Long.parseLong(sizeTo), target);
   	}
    
%>
<!-- 사이즈내 파일복사 결과화면 시작 -->
<form name="fm5" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="FILE">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">사이즈내 파일복사 결과</td>
        <td><%=retCopy %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm5.submit()">
</form>
<!-- 사이즈내 파일복사 결과화면 끝 -->
<%  
} else if(execFlag.equals("CREAT_ACTION")){
	
	String source = request.getParameter("source");
	String creator = request.getParameter("creator");
    String target = request.getParameter("target");
    
    boolean retCopy = false;
    if (source != null && source.length() > 0
    	&& creator != null && creator.length() > 0
    	&& target != null && target.length() > 0) {
   		retCopy = EgovFileTool.copyFilesByOwner(source, creator, target);
   	}
    
%>
<!-- 생성자별 파일복사 결과화면 시작 -->
<form name="fm6" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovFileCopy">
<input type = "hidden" name="execFlag" value="FILE">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">생성자별 파일복사 결과</td>
        <td><%=retCopy %></td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm6.submit()">
</form>
<!-- 생성자별 파일복사 결과화면 끝 -->
<%  
}
%>