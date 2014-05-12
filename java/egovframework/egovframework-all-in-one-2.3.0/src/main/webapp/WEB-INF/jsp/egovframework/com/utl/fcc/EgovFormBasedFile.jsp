<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="egovframework.com.utl.fcc.service.EgovFormBasedFileVo" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>Form Based File Upload &amp; Download</title>
<link href="<c:url value='/css/egovframework/com/cmm/com.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
function download(subpath, filename, orginal) {
	document.getElementById("target").src = "<c:url value='/EgovFormBasedFile.do'/>" 
		+ "?path=" + subpath 
		+ "&physical=" + filename
		+ "&filename=" + orginal;
}
</script>
</head>
<body>

<form name="frm" action ="<c:url value='/EgovFormBasedFile.do'/>" method="post" enctype="multipart/form-data">
<table border="1">
	<tr>        	
		<td><input name="file" type="file" id="file" size="60"/></td>
	</tr>
	<tr>
		<td><input name="file" type="file" id="file" size="60"/></td>
	</tr>
	<tr>	
		<td align="center">
			<input name="upload" type="submit" id="upload" value="Upload"/>
		</td>
	</tr> 
	<tr>
		<td>
		※ Form Based File Upload 처리의 경우 <br>
		<font color="white">※</font> Spring의 multipartResolver가 지정되지 않아야 합니다.<br>
		<font color="white">※</font> Spring의 multipartResolver를 사용하는 경우 <br>
		<font color="white">※</font> EgovFormBasedFileUtil 대신 EgovFileUploadUtil을 사용해야 함
		</td>
	</tr>
</table>
<br>
<c:if test="${not empty list}">
	<table border="1">
		<tr>
			<td>Filename</td>
			<td>Physical filename</td>
			<td>contentType</td>
			<td>size</td>
			<td>Download</td>
		</tr>
		<c:forEach var="result" items="${list}" varStatus="status">
			<tr>
				<td><c:out value="${result.fileName}"/></td>
				<td><c:out value="${result.physicalName}"/></td>
				<td><c:out value="${result.contentType}"/></td>
				<td><c:out value="${result.size}"/></td>
				<td><a
					href="javascript:download('<c:out value="${result.serverSubPath}"/>', '<c:out value="${result.physicalName}"/>', '<c:out value="${result.fileName}"/>');">다운로드</a></td>
			</tr>
		</c:forEach>
	</table>
	<iframe id="target" frameborder="0" width="0" height="0"></iframe>
</c:if>
</form>
<br>
<table border="1">
	<tr>
		<td>1. 필요 library</td>
		<td><pre>
- commons-fileupload-1.2.1.jar (http://commons.apache.org/fileupload/ 참조)
- commons-io-1.4.jar (commons-fileupload 사용 시 필요)
  
 ※ 현 예제 JSP는 JSTL을 사용하였으나 파일 upload 자체 기능에는 불필요
		</pre></td>
	</tr>
	<tr>
		<td>2. 파일 Upload 사용 방법</td>
		<td><pre>
...
// request : HttpServletRequest 객체
// uploadDir : upload 디렉토리 
//	ex : uploadDir = config.getServletContext().getRealPath("/WEB-INF/files/");
// maxFileSize : 최대 허용 size (byte단위)
//	ex : maxFileSize = 1024 * 1024 * 100;   // 100M
List&lt;EgovFormBasedFileVo&gt; list = EgovFormBasedFileUtil.uploadFiles(request, uploadDir, maxFileSize);

EgovFormBasedFileVo vo = null;
for (int i = 0; i &lt; list.size(); i++) {
	vo = list.get(i);
	
	System.out.println("File Name = " + vo.getFileName());
	System.out.println("ContentType = " + vo.getContentType());
	System.out.println("ServerSubPath = " + vo.getServerSubPath());
	System.out.println("PhysicalName = " + vo.getPhysicalName());
	System.out.println("Size = " + vo.getSize());
}
		</pre></td>
	</tr>
 	<tr>
		<td>3. 파일 Download 사용 방법</td>
		<td><pre>
...
// response : HttpServletResponse 객체
// uploadDir : upload 디렉토리 (파일 upload시 지정된 디렉토리)
// subPath : 파일 upload시 얻어진 serverSubPath
// physical : 물리적 파일명
// filename : 다운로드시 표시되는 파일명 (논리적 파일명)

EgovFormBasedFileUtil.downloadFile(response, uploadDir, subPath, physical, filename);

// 내부적으로 uploadDir + subPath + physical 파일을 filename으로 다운로드하도록 처리됨
		</pre></td>
	</tr>
</table>
</body>
</html>