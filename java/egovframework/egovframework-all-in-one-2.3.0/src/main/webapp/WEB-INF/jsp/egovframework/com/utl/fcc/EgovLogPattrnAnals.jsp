<%--
  Class Name : EgovUnitCalc.jsp
  Description : 로그패턴분석
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.06.25    장동한          최초 생성

    author   : 장동한
    since    : 2010.06.25
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" buffer="none"%>
<%@page import="egovframework.com.utl.fcc.service.EgovNumberCheckUtil"%>
<%@page import="java.util.*"  %>
<%@page import="java.util.regex.*"  %>
<%@page import="java.text.*"  %>
<%@page import="java.io.*"  %>
<%@ page import="egovframework.com.cmm.EgovWebUtil" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<%!
	//서버 경로를 체크하는 메소드
	public boolean getServerPath(String sTxtLogPattrnPath){

		boolean booleanRtn = false;

		String[] arrPath = {"C:\\\\WindowsLog", "/product/jeus2/logs/was2/"};

		for(int i=0 ; i < arrPath.length; i++){
			Pattern pattern1 = Pattern.compile(arrPath[i]);
		    Matcher matcher1 = pattern1.matcher(sTxtLogPattrnPath);

		    if(matcher1.find()){
		    	booleanRtn = true;
			}
		}

		return booleanRtn;
	}

%>
<%
//로그패턴 File 선언
File fileLogPattrn = null;
//로그패턴 in 객체선언
BufferedReader in = null;

//찾을로그 명(패턴)명
String sTxtLogPattrn = request.getParameter("txtLogPattrn") == null ? "" : (String)request.getParameter("txtLogPattrn");

//로그파일경로명
String sTxtLogPattrnPath = request.getParameter("txtLogPattrnPath") == null ? "" : (String)request.getParameter("txtLogPattrnPath");

//로그파일 인코딩 형식
String sSelLogType = request.getParameter("selLogType") == null ? "" : (String)request.getParameter("selLogType");

// 2011.10.25 보안점검 후속조치
sTxtLogPattrn = EgovWebUtil.clearXSSMinimum(sTxtLogPattrn);
sTxtLogPattrnPath = EgovWebUtil.clearXSSMinimum(sTxtLogPattrnPath);
sSelLogType = EgovWebUtil.clearXSSMinimum(sSelLogType);

// 찾는 파일 갯수
int nFindNum = 0;


try {

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>로그패턴분석</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js" ></script>
<link href="/css/egovframework/com/cmm/utl/com.css" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 저장처리화면
 ******************************************************** a*/
function fn_egov_save_LogPattrn(){

	var varFrom = document.formLogPattrn;

	if(varFrom.txtLogPattrn.value == ""){
		alert("찾을로그 명(패턴)명을 입력해주세요!");
		varFrom.txtLogPattrn.focus();
		return;
	}

	if(varFrom.txtLogPattrnPath.value == ""){
		alert("로그파일경로명을 입력해주세요!");
		varFrom.txtLogPattrnPath.focus();
		return;
	}

	varFrom.onsubmit();

}

/* ********************************************************
 * TXT 검색 결과 다운로드
 ******************************************************** */
 function fn_egov_txt_LogPattrn(){
	 var varFrom = document.formLogPattrn;
	 var sURL ="/EgovPageLink.do?link=cmm/utl/EgovLogPattrnAnalsTxtDownload";

	 sURL=  sURL + "&txtLogPattrn=" + varFrom.txtLogPattrn.value;
	 sURL=  sURL + "&txtLogPattrnPath=" + varFrom.txtLogPattrnPath.value;
	 sURL=  sURL + "&selLogType=" + fn_egov_SelectBoxValue("selLogType");

	 document.getElementById("ifr_hidden").src = sURL;

}

 /* ********************************************************
 * SELECT BOX VALUE FUNCTION
 ******************************************************** */
 function fn_egov_SelectBoxValue(sbName)
 {
 	var FValue = "";
 	for(var i=0; i < document.getElementById(sbName).length; i++)
 	{
 		if(document.getElementById(sbName).options[i].selected == true){

 			FValue=document.getElementById(sbName).options[i].value;
 		}
 	}

 	return  FValue;
 }
</script>
<style type="text/css">
.btnNew {
border : 0 solid #000;
color : #000000;
background-image : url(/images/egovframework/com/cmm/uss/umt/button/bu2_bg.gif);
cursor : pointer;
}

h1 {font-size:12px;}
caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>
</head>
<body>
<DIV id="content" style="width:712px">
<!-- noscript 테그 -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 상단 타이틀  영역 -->
<div class="title_left" style="width:100%;margin:0 0 0 0;">
	<h1><img src="/images/egovframework/com/cmm/uss/umt/icon/tit_icon.gif" width="16" height="16" hspace="3" style="vertical-align:middle; display:inline-block;" alt=" ">&nbsp;로그패턴분석</h1>
</div>


<!--  줄간격조정  -->
<div style="width:100%;border:solid 0px #000000;height:3px"></div>

<!--  상단  등롬폼 영역 START -->
<form name="formLogPattrn" method="post" action="/EgovPageLink.do?link=cmm/utl/EgovLogPattrnAnals">
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register" summary="로그패턴분석  등롬폼을  제공한다.">
<caption>로그패턴분석  등롬폼을  제공한다</caption>
<tr>
	<th scope="row" width="25%" height="23" class="required_text" nowrap><label id="IdUnityLinkNm">찾을로그(패턴)명</label><img src="/images/egovframework/com/cmm/uss/umt/icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
	<td width="75%">
	<input type="text" title="찾을로그(패턴)명" size="10" value="<%=sTxtLogPattrn %>" name="txtLogPattrn" style="width:98%">
	</td>
</tr>
<tr>
	<th scope="row" height="23" class="required_text" ><label id="IdUnityLinkSeCode">로그파일경로명(서버)</label><img src="/images/egovframework/com/cmm/uss/umt/icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
	<td>
	<input type="text" title="로그파일경로명" size="10" value="<%=sTxtLogPattrnPath %>" name="txtLogPattrnPath" style="width:98%">
	</td>
</tr>
<tr>
	<th scope="row" height="23" class="required_text" ><label id="IdIncodeType">인코딩형식</label><img src="/images/egovframework/com/cmm/uss/umt/icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
	<td>
	<select name="selLogType" id="selLogType" title="인코딩형식">
	<option value="ansi" <%if(sSelLogType.equals("")){ %>selected<%}%>>ANSI</option>
	<option value="utf-8" <%if(sSelLogType.equals("utf-8")){ %>selected<%}%>>UTF-8</option>
	<option value="euc-kr" <%if(sSelLogType.equals("euc-kr")){ %>selected<%}%>>EUC-KR</option>
	<option value="MS949" <%if(sSelLogType.equals("MS949")){ %>selected<%}%>>MS949</option>
	</select>
	</td>
</tr>
</table>

<center>
<!--  줄간격조정  -->
<div style="width:100%;border:solid 0px #000000;height:3px"></div>

<!--  목록/저장버튼  -->
<table border="0" cellspacing="0" cellpadding="0" align="center" summary="검색/다운로드 버튼을 제공한다.">
<caption>검색/다운로드 버튼을 제공한다.</caption>
<tr>
  <th scope="row">&nbsp;</th>
  <td><img src="/images/egovframework/com/cmm/uss/umt/button/bu2_left.gif"  width="8" height="20"></td>
  <td class="btnBackground" nowrap><input type="submit" value="검색" name="btnSearch" onclick="fn_egov_save_LogPattrn(); return false;" class="btnNew" style="height:20px;width:26px;" >
  </td>
  <td><img src="/images/egovframework/com/cmm/uss/umt/button/bu2_right.gif"  width="8" height="20"></td>
  <%if(!sTxtLogPattrn.equals("")){%>
  <td>&nbsp;</td>
  <td><img src="/images/egovframework/com/cmm/uss/umt/button/bu2_left.gif"  width="8" height="20"></td>
  <td class="btnBackground" nowrap><input type="button" value="다운로드" alt="TXT형식의  검색 결과를 다운받는다!" title="TXT형식의  검색 결과를 다운받는다!" name="btnSearch" onclick="fn_egov_txt_LogPattrn(); return false;" class="btnNew" style="height:20px;width:50px;" >
  </td>
  <td><img src="/images/egovframework/com/cmm/uss/umt/button/bu2_right.gif"  width="8" height="20"></td>
  <%}%>

</tr>
</table>
<table border="0" width="100%" cellspacing="0" cellpadding="0" align="center" summary="보안성에 따라서 아래의 디렉토리만 접근  경고">
<caption>보안성에 따라서 아래의 디렉토리만 접근  경고</caption>
<tr>
	<td colspan="8" align="right">
		웹 보안성에 따라서 아래의 디렉토리만 접근이 가능합니다.<br>
		윈도우 : C:\WindowsLog<br>
		UNIX : /product/jeus2/logs/was2/<br>
	</td>
</tr>
</table>
</center>
</form>
<!--  상단  등롬폼 역영 End  -->
<iframe name="ifr_hidden" id="ifr_hidden" title="Txt 파일 다운로드  iframe" src="about:blank;" style="width:0px;height:0px;visibility: hidden;"></iframe>
<!--  줄간격조정  -->
<div style="width:100%;border:solid 0px #000000;height:3px"></div>

<!--  결과 출력 영역 시작  -->
<table width="100%" cellspacing="0" cellpadding="0" border="0" summary="검색/분석 결과를 출력한다.">
<caption>검색/분석 결과를 출력한다</caption>
<tr><th scope="col"> <th></tr>
<tr>
	<td>
<%


if( !sTxtLogPattrnPath.equals("") && !getServerPath(sTxtLogPattrnPath) ){
	out.println("지정된 서버 경로명이 맞지  않습니다!" + "<br>");
}else{

	if(!sTxtLogPattrnPath.equals("")){

	    	fileLogPattrn = new File(sTxtLogPattrnPath);

	    	//파일 존재 체크
	    	if(!fileLogPattrn.isFile()) {
	    		out.println("<center>디렉토리/파일명이 올바르지 않습니다!</center><br>");
	            out.println("<br>");
	    	}

	    	//로그파일 출력
	    	if( fileLogPattrn.isFile() ){
	        //////////////////////////////////////////////////////////////// 100MB까지 읽게

	        if(sSelLogType.equals("ansi")){
	        	in = new BufferedReader(new InputStreamReader(new FileInputStream(sTxtLogPattrnPath)));
	        }else{
	        	in = new BufferedReader(new InputStreamReader(new FileInputStream(sTxtLogPattrnPath),sSelLogType));
	        }

	        String s;
	        int nLine = 0;
	        while((s = in.readLine()) != null) {
	        	//라인번호 증가  C[A-Z]* Pattern.CASE_INSENSITIVE
	        	nLine++;
	            Pattern pattern = Pattern.compile(sTxtLogPattrn);
	            Matcher matcher = pattern.matcher(s);

	            if(matcher.find()){
	            	  //System.out.println(matcher.group());
	            	  //System.out.println(matcher.group());
	            	  //String sMatcher = (String)matcher.group();
	            	  //System.out.println("라인(" + nLine + "):" + matcher.group().toString());
	            	  out.println("라인(" + nLine + "):");
	                  out.println( s.replaceAll(matcher.group().toString(), "<font color='blue'><b>" + matcher.group().toString() + "</b></font>") );
	                  out.println("<br>");
	                  nFindNum++;
	           	}

	        }
	//in.close();
	        ////////////////////////////////////////////////////////////////
	    	}


	}

%>
<%if(!sTxtLogPattrnPath.equals("") && nFindNum == 0){%>
	<center> <%=sTxtLogPattrn %> 로그(패턴) 대한  결과가  없습니다!</center><br>
<%} %>

<%}%>
</td>
</tr>
</table>
<!-- 결과 출력 영역 종료  -->
<!--
정규식 예제
PatternPattern : c[a-z]* 결과: c,ca,co,car,combat,count,
Pattern : c[a-z] 결과: ca,co,
Pattern : c[a-z] 결과: cA,ca,co,
Pattern : c[a-zA-Z0-9] 결과: cA,ca,co,c0,cA,ca,co,c0,
Pattern : .* 결과: bat,baby,bonus,c,cA,ca,co,c.,c0,c#,car,combat,count,date,disc,
Pattern : c. 결과:결과: cA,ca,co,c.,c0,c#,
Pattern : c.* 결과: c,cA,ca,co,c.,c0,c#,car,combat,count,
Pattern : c\.c\. 결과: c.,
Pattern : c\w 결과: cA,ca,co,c0,
Pattern :: c\d 결과: c0,
Pattern : c.*t 결과: combat,count,
PatternPattern : [b|c].* 결과: bat,baby,bonus,c,cA,ca,co,c.,c0,c#,car,combat,count,
Pattern : [^b|c].* 결과: date,disc,
Pattern : .*a.* 결과: bat,baby,ca,car,combat,date,
Pattern : .*a.+ 결과: bat,baby,car,combat,date,bat,baby,car,combat,date,

 -->
</DIV>

</body>
</html>
<%
// 2011.10.10 보안점검 후속조치
} catch (Exception ex) {
	System.out.println("IGNORE: " + ex);
} finally {
	if (fileLogPattrn != null) {
	    try {
			fileLogPattrn = null;
	    } catch (Exception e) {
			System.out.println("IGNORE: " + e);
	    }
	}
	if (in != null) {
	    try {
			in.close();
	    } catch (Exception e) {
			System.out.println("IGNORE: " + e);
	    }
	}
}
 %>