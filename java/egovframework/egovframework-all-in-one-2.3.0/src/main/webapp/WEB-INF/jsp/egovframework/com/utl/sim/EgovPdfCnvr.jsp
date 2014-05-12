<%--
  Class Name : EgovPdfCnvr.jsp
  Description : xls,doc,ppt를 Pdf로 변환하는 화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.02    이용          최초 생성
 
    author   : 이용
    since    : 2009.02.02
   
     page language="java" contentType="text/html; charset=UTF-8" session="false" 
    page language="java" contentType="application/pdf; charset=UTF-8" session="false"
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@ page import="egovframework.com.utl.sim.service.EgovPdfCnvr"  %>
<%@ page import="java.util.List"  %>
<%@ page import="java.util.Iterator"  %>
<html lang="ko">
<head>
<title>EgovPdfCnvr</title>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">

<script>
/* ********************************************************
 * PDF변환 처리 함수
 ******************************************************** */
function fn_PDFConverter() {
   if(!checkFile()) return;   // 변환파일 체크 함수 호출
   if(!checkTarget()) return; // 생성파일명 체크 함수 호출
   document.convertPDFForm.submit();

}

/* ********************************************************
 * 변환파일 체크 함수
 ******************************************************** */
function checkFile(){ 
	if(document.convertPDFForm.source.value==""){
	   alert("변환할  파일을 지정해 주세요");
	   return false;
	}

	var  str_dotlocation,str_ext,str_low;
	str_value  = document.convertPDFForm.source.value;
	str_low   = str_value.toLowerCase(str_value);
	str_dotlocation = str_low.lastIndexOf(".");
	str_ext   = str_low.substring(str_dotlocation+1);
	
	switch (str_ext) {
	  case "xls" :
	  case "xlsx" :
	  case "doc" :
	  case "docx" :
	  case "ppt" :
	  case "pptx" :
		 return true;
	     break;
	  default:
	     alert("파일 형식이 맞지 않습니다.\n xls,doc,ppt 만\n 변환 가능합니다!");
	  document.convertPDFForm.source.focus();
	     return false;
	}
}

/* ********************************************************
 * 생성파일명 체크 함수
 ******************************************************** */
function checkTarget(){ 
	if(document.convertPDFForm.target.value==""){
	   alert("PDF생성 파일명을 입력해 주세요");
	   document.convertPDFForm.target.focus();
	   return false;
	}

	var  str_dotlocation,str_ext,str_low;
	str_value  = document.convertPDFForm.target.value;
	if(str_value.indexOf(".")!=-1) { 
		alert("PDF 생성파일명은 확장자를 제외한 변경할 파일명만 입력해 주세요");
		document.convertPDFForm.target.focus();
		return false;
	}
	return true;	
}

</script>
</head>
<%!
    String safeGetParameter (HttpServletRequest request, String name){
        String value = request.getParameter(name);
        if (value == null) {
            value = "";
        }
        return value;
    }

    String[] safeGetParameterValues(HttpServletRequest request, String name){
		String[] sCheckParameterArray = request.getParameterValues(name);
		if( sCheckParameterArray.length == 0 )
		{
		   return null;
		}
		String[] sReturn = new String[sCheckParameterArray.length];
		for( int i = 0; i < sCheckParameterArray.length; i++ )
		{
	      sReturn[i] = (sCheckParameterArray[i]);
	      if("".equals(sReturn[i]) ){
	         sReturn[i] = null;
	      }
		}
		return sReturn;
	}
%>
<%
boolean retCopy = false; 
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="CONVERTPDF";
}

if(execFlag.equals("CONVERTPDF")){
%>
<!-- PDF 파일변환 화면  시작-->
<!-- form name="convertPDFForm" action ="/EgovPageLink.do  sym/mpm/testpdf.do" method="post" enctype="multipart/form-data" -->
<form name="convertPDFForm" action ="/EgovPageLink.do" method="post" enctype="multipart/form-data">
<!--  form name="convertPDFForm" action ="/pdfTest.jsp" method="post" target="_b" enctype="multipart/form-data"-->
<input type = "hidden" name="link" value="cmm/utl/EgovPdfCnvr">
<input type = "hidden" name="execFlag" value="CONVERTPDF_ACTION">
<input type = "hidden" name="cmdStr" value="convertpdf">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <th class="title_left" nowrap>PDF 변환</th>
        <td class="required_text">&nbsp;</td>
    </tr> 
    <tr>
        <td colspan="2"> 
        Open Office를 이용한 PDF변환하는 관계로 해당 서버에 Open Office가 존재한다는 가정하에<br>
        soffice.exe를 8100번 port로 연결한 후 파일 변환작업이 이루어짐.<br>
        ex: "C:\Program Files\OpenOffice.org 3\program\soffice.exe" -headless -accept=socket,port=8100;urp;<p>
                     원본파일은 찾기버튼을 이용하여 선택  <br>
                     변경파일은 확장자를 제외한 파일명만 입력.<br>
        </td>
    </tr>     
    <tr>
        <th width="150" height="23" class="title_left" nowrap>원본 file :</th>
        <td width="280" height="23" class="title_left" nowrap>&nbsp;<input type = "file" name="source" value="" size="50" ></td>
    <tr>
    </tr>
        <th width="150" height="23" class="title_left" nowrap>변경 file :</th>
        <td width="280" height="23" class="title_left" nowrap>&nbsp;<input type = "text" name="target" value="" size="50"></td>
    </tr>
    <tr>
        <td colspan="2">&nbsp;<input type = "button" method="post"  value="실행!" onclick="javascript:fn_PDFConverter()"></td>
    </tr>
</table>
</form>
<!-- 결과 화면 -->
<%  
} else if(execFlag.equals("CONVERTPDF_ACTION")){
            String target = safeGetParameter(request,"target");
            retCopy = EgovPdfCnvr.getPDF(target, request, response);
%>
<!-- PDF 파일변환 결과화면 시작 -->
<form name="convertPDFTo" action ="/EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovNetworkState">
<input type = "hidden" name="execFlag" value="CONVERTPDF">
<table width="840" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <th width="150" height="23" class="title_left" nowrap>PDF 변환  결과</th>
        <td>&nbsp;<%=retCopy %></td>
    </tr>
    <tr>
       <td colspan="2">
          <input type = "button" method="post"  value="화면으로 돌아가기" onclick="convertPDFTo.submit()">&nbsp;&nbsp;
          <input type = "button" method="post"  value="화면닫기" onclick="javascript:window.close()">
       </td>
    </tr>

</table> 
</form>
<%
} else {
%>

<%
}
%>
</html>