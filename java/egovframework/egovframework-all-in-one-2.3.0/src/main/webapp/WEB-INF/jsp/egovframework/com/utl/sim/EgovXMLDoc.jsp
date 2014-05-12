<%--
  Class Name : EgovXMLDoc.jsp
  Description : XML파일을 파싱하여 구조체 형태로 반환 또는 구조체 형태의 데이터를 XML파일로 저장하는 JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.03    박지욱          최초 생성
 
    author   : 박지욱
    since    : 2009.02.03
   
--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.sim.service.EgovXMLDoc"  %>
<%@page import="noNamespace.SndngMailDocument"  %>
<% 
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="CLASS";
}
%>
<%
if(execFlag.equals("CLASS")){
%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<!-- 클래스생성 화면  시작-->
<form name="xmlForm3" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovXMLDoc">
<input type = "hidden" name="execFlag" value="CLASS_ACTION">
<table width="550" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="250" height="23" class="required_text" nowrap>XML스키마를 이용한 자바클래스 생성</th>
      <td width="300" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="xmlForm3.submit()"></td>
    </tr>
    <tr>
      <th width="250" height="23" class="title_left" nowrap>XML스키마 파일</td>
      <td width="300" height="23" class="title_left" nowrap><input type = "text" name="file" value="" size="40"></td>
    </tr>
    <tr>
      <th width="250" height="23" class="title_left" nowrap>생성 JAR 경로</td>
      <td width="300" height="23" class="title_left" nowrap><input type = "text" name="jar" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 클래스생성 화면 끝 -->
<!-- 파싱 화면  시작-->
<form name="xmlForm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovXMLDoc">
<input type = "hidden" name="execFlag" value="PARS_ACTION">
<table width="550" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="250" height="23" class="required_text" nowrap>XML데이터 -> 자바구조체(발송메일)</th>
      <td width="300" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="xmlForm1.submit()"></td>
    </tr>
    <tr>
      <th width="250" height="23" class="title_left" nowrap>XML데이터 파일</td>
      <td width="300" height="23" class="title_left" nowrap><input type = "text" name="file" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 파싱 화면 끝 -->
<!-- 조립 화면  시작-->
<form name="xmlForm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovXMLDoc">
<input type = "hidden" name="execFlag" value="COMB_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="250" height="23" class="required_text" nowrap>자바구조체(발송메일) -> XML데이터</th>
      <td width="300" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="xmlForm2.submit()"></td>
    </tr>
    <tr>
      <th width="250" height="23" class="title_left" nowrap>메세지ID</td>
      <td width="300" height="23" class="title_left" nowrap><input type = "text" name="mssageId" value="" size="40"></td>
    </tr>
    <tr>
      <th width="250" height="23" class="title_left" nowrap>발신자</td>
      <td width="300" height="23" class="title_left" nowrap><input type = "text" name="dsptchPerson" value="" size="40"></td>
    </tr>
    <tr>
      <th width="250" height="23" class="title_left" nowrap>수신자</td>
      <td width="300" height="23" class="title_left" nowrap><input type = "text" name="recptnPerson" value="" size="40"></td>
    </tr>
    <tr>
      <th width="250" height="23" class="title_left" nowrap>제목</td>
      <td width="300" height="23" class="title_left" nowrap><input type = "text" name="sj" value="" size="40"></td>
    </tr>
    <tr>
      <th width="250" height="23" class="title_left" nowrap>내용</td>
      <td width="300" height="23" class="title_left" nowrap><input type = "text" name="emailCn" value="" size="40"></td>
    </tr>
    <tr>
      <th width="250" height="23" class="title_left" nowrap>발신상태</td>
      <td width="300" height="23" class="title_left" nowrap><input type = "text" name="sndngResultCode" value="" size="40"></td>
    </tr>
    <tr>
      <th width="250" height="23" class="title_left" nowrap>XML결과 파일</td>
      <td width="300" height="23" class="title_left" nowrap><input type = "text" name="file" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 조립 화면 끝 -->
<%  
} else if(execFlag.equals("CLASS_ACTION")){
	
	String file = request.getParameter("file");
	String jar = request.getParameter("jar");
	boolean result = false;
	if (file != null && file.length() > 0
		&& jar != null && jar.length() > 0) {
		result = EgovXMLDoc.creatSchemaToClass(file, jar);
	}
	
%>
<!-- 클래스생성 결과화면 시작 -->
<form name="fm3" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovXMLDoc">
<input type = "hidden" name="execFlag" value="CLASS">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td>클래스생성 결과
        </td>
        <td><%=result %>
        </td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm3.submit()">
</form>
<!-- 클래스생성 결과화면 끝 -->
<%  
} else if(execFlag.equals("PARS_ACTION")){
	
	String file = request.getParameter("file");
	String resultStr = "";
	if (file != null && file.length() > 0) {
		SndngMailDocument mailDoc = EgovXMLDoc.getXMLToClass(file);
		SndngMailDocument.SndngMail mailElement = mailDoc.getSndngMail();
		resultStr += "1.발신자: " + mailElement.getDsptchPerson() + "<br>";
		resultStr += "2.수신자: " + mailElement.getRecptnPerson() + "<br>";
		resultStr += "3.제목: " + mailElement.getSj() + "<br>";
		resultStr += "4.내용: " + mailElement.getEmailCn() + "<br>";
		resultStr += "5.발신상태: " + mailElement.getSndngResultCode() + "<br>";
	}
	
%>
<!-- 파싱 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovXMLDoc">
<input type = "hidden" name="execFlag" value="CLASS">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td>결과
        </td>
        <td><%=resultStr %>
        </td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 파싱 결과화면 끝 -->
<%  
} else if(execFlag.equals("COMB_ACTION")){
	
	String file = request.getParameter("file");
	String dsptchPerson = request.getParameter("dsptchPerson");
	String recptnPerson = request.getParameter("recptnPerson");
	String sj = request.getParameter("sj");
	String emailCn = request.getParameter("emailCn");
	String sndngResultCode = request.getParameter("sndngResultCode");
	
	boolean result = false;
	if (file != null && file.length() > 0
		&& dsptchPerson != null && dsptchPerson.length() > 0
		&& recptnPerson != null && recptnPerson.length() > 0
		&& sj != null && sj.length() > 0
		&& emailCn != null && emailCn.length() > 0
		&& sndngResultCode != null && sndngResultCode.length() > 0
	) {
		
		SndngMailDocument mailDoc;
		SndngMailDocument.SndngMail mailElement;
		mailDoc = SndngMailDocument.Factory.newInstance();
		mailElement = mailDoc.addNewSndngMail();
		mailElement.setDsptchPerson(dsptchPerson);
		mailElement.setRecptnPerson(recptnPerson);
		mailElement.setSj(sj);
		mailElement.setEmailCn(emailCn);
		mailElement.setSndngResultCode(sndngResultCode);
		
		result = EgovXMLDoc.getClassToXML(mailDoc, file);
		
	}
	
%>
<!-- 조립 결과화면 시작 -->
<form name="fm2" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovXMLDoc">
<input type = "hidden" name="execFlag" value="CLASS">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td>결과
        </td>
        <td><%=result %>
        </td>
    </tr>
</table> 
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm2.submit()">
</form>
<!-- 조립 결과화면 끝 -->
<%
} 
%>