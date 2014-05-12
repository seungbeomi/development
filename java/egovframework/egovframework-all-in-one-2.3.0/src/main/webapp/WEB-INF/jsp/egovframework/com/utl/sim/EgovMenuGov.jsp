<%--
  Class Name : EgovMenuGov.jsp
  Description : 파일을 다른 디렉토리로 복사하는 JSP
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.02    이용          최초 생성

    author   : 이용
    since    : 2009.02.02

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@ page import="egovframework.com.utl.sim.service.EgovFileTool"  %>
<%@ page import="egovframework.com.utl.sim.service.EgovMenuGov"  %>
<%@ page import="egovframework.com.cmm.service.Globals"  %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Vector" %>
<%!
    String safeGetParameter (HttpServletRequest request, String name){
        String value = request.getParameter(name);
        if (value == null) {
            value = "";
        }
        return value;
    }

    String[] safeGetParameterValues (HttpServletRequest request, String name){
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
<html lang="ko">
<head>
<title>EgovMenuGov</title>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<script type="text/javascript">
var imgpath = "<c:url value='/images/egovframework/com/cmm/utl/'/>";
</script>
<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovMenuGov.js"></script>
<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"></script>
<script language="JavaScript">

    function fOpen()
    {
        document.menuGovForm.submit();
    }

    function fRowAdd( pElement )
   {
      var vRowIndex = pElement.parentElement.parentElement.rowIndex;
      var vIlJeongRow = document.getElementById("divMenuList").insertRow(vRowIndex+1);
      vHtml02 ="<tr><td width=80 ><a onclick='fRowAdd(this)' style='cursor:hand;' title='라인추가'>+</a></td>";
      vHtml03 ="<td width=80 ><input type='text' name='hiddenMenuIDArray'    value='' size=10 maxlength='10' onChange='fn_NumberChk(this)'></td>";
      vHtml04 ="<td width=164><input type='text' name='hiddenMenuNameArray'  value='' size=20 maxlength='20'></td>";
      vHtml05 ="<td width=80 ><input type='text' name='hiddenMenuLevelArray' value='' size=5  maxlength='5'  onChange='fn_NumberChk(this)'></td>";
      vHtml06 ="<td width=360><input type='text' name='hiddenMenuURLArray'   value='' size=50 maxlength='50'></td>";
      vHtml07 ="<td width=80 ><a onclick=\"fRowDelete(this)\" style='cursor:hand;' title='라인삭제'>-</a></td>";
      vHtml08 ="<td width=16 ></td></tr>";

      i=0;
      vIlJeongRow.insertCell(i++).innerHTML = vHtml02;
      vIlJeongRow.insertCell(i++).innerHTML = vHtml03;
      vIlJeongRow.insertCell(i++).innerHTML = vHtml04;
      vIlJeongRow.insertCell(i++).innerHTML = vHtml05;
      vIlJeongRow.insertCell(i++).innerHTML = vHtml06;
      vIlJeongRow.insertCell(i++).innerHTML = vHtml07;
      vIlJeongRow.insertCell(i++).innerHTML = vHtml08;
   }

   /**
   *내    용 : 일정을 삭제한다
   *파라메터 :
   */
   function fRowDelete(pElement){

      var vRowIndex      = pElement.parentElement.parentElement.rowIndex;
      if(vRowIndex > 1){
         document.getElementById("divMenuList").deleteRow(vRowIndex);
      }else alert("첫번째 라인은 삭제 할 수 없습니다");
   }

   function fn_NumberChk(obj){
		inputObj = obj.value ;
		for(i=0;i<inputObj.length;i++)
		if (inputObj.charAt(i)<'0' || inputObj.charAt(i)>'9') {
			if (inputObj.charAt(i) != '*') {
				alert("숫자만 입력해주세요.") ;
				obj.value="";
				obj.focus() ;
				return false ;
			}
		}
   }


</script>
</head>
<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="MENUGOV";
}
%>
<%
if(execFlag.equals("MENUGOV")){
    String parFile  = request.getParameter("file");
    if(parFile==null) parFile= "";
%>
<body>
<!-- 메뉴 화면  시작-->
<form name="menuGovForm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovMenuGov">
<input type = "hidden" name="execFlag" value="MENUGOV_MOVE">
<input type = "hidden" name="char" value="|" size="50">
<input type = "hidden" name="field" value="4" size="50">

<table width="840" border="0" cellpadding="0" cellspacing="1" class="table-register">
<tr>
<td width="100" height="23" class="title_left" nowrap>메뉴관리</td>
<td>
파일 형태의 메뉴를 등록하는 화면으로 등록된 DAT 형태의 파일의 데이타를 바탕으로 트리메뉴를 구현<br>
서버에서 쓰기가 가능한 디렉토리를 지정해 줘야함.<br>
ex)/product/jeus/egovProps/tmp/sample/menutest.dat
</td>
</tr>
<tr>
<td width="100" height="23" class="title_left" nowrap>메뉴변환파일</td>
<td ><input type = "text" name="file" value="<%=parFile %>" size="50"></td>
</tr>
</table>

<input type = "button" method="post"  value="실행!" onclick="menuGovForm.submit()">
</form>
</body>
<!-- 메뉴 화면  끝 -->
<%
}else if(execFlag.equals("MENUGOV_MOVE")){

    String parFile  = safeGetParameter(request,"file");
    String parChar  = safeGetParameter(request,"char");
    int parField    = Integer.parseInt(safeGetParameter(request,"field"));;

//    Vector result1 = EgovFileTool.parsFileByChar(parFile, parChar, parField);
      Vector result1 = EgovMenuGov.parsFileByMenuChar(parFile, parChar, parField);
%>
<link href="/egovcom/css/egovframework/com/com.css" rel="stylesheet" type="text/css">
<form name="menuGovForm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovMenuGov">
<input type = "hidden" name="execFlag" value="MENUGOV_ACTION">

<input type = "hidden" name="char" value="<%=parChar %>" >
<input type = "hidden" name="field" value="<%=parField %>" >
<table ID='divMenuList' width="840" border="0" cellpadding="0" cellspacing="1" class="table-register">
<tr>
<td width=80 >메뉴파일:</td>
<td width=80 colspan=5><input type = "text" name="file" value="<%=parFile %>" readOnly></td>
</tr>
<tr>
<td width=80 >라인추가</td>
<td width=80 >메뉴ID</td>
<td width=164>메뉴명</td>
<td width=80 >상위메뉴ID</td>
<td width=360>URL</td>
<td width=80 >라인삭제</td>
<td width=16 ></td>
</tr>
<%
    String str = "";
    if(result1.size() > 0){
    for (int j = 0; j < result1.size()-1; j++) {
        ArrayList arr = (ArrayList)result1.elementAt(j);
%>
<tr>
<td width=80 ><a onclick='fRowAdd(this)' style='cursor:hand;' title='라인추가'>+</a> </td>
<td width=80 ><input type='text' name='hiddenMenuIDArray'    value='<%=(String)arr.get(0) %>' size=10 maxlength='10' onChange="fn_NumberChk(this)"></td>
<td width=164><input type='text' name='hiddenMenuNameArray'  value='<%=(String)arr.get(2) %>' size=20 maxlength='20'></td>
<td width=80 ><input type='text' name='hiddenMenuLevelArray' value='<%=(String)arr.get(1) %>' size=5  maxlength='5'  onChange="fn_NumberChk(this)"></td>
<td width=360><input type='text' name='hiddenMenuURLArray'   value='<%=(String)arr.get(3) %>' size=50 maxlength='30'></td>
<td width=80 ><a onclick='fRowDelete(this)' style='cursor:hand;' title='라인삭제'>-</a></td>
<td width=16 ></td>
</tr>
<%
       }
    }else{
%>
<tr>
<td width=80 ><a onclick='fRowAdd(this)' style='cursor:hand;' title='라인추가'>+</a> </td>
<td width=80 ><input type='text' name='hiddenMenuIDArray'    value='' size=10 maxlength='10' onChange='fn_NumberChk(this)'></td>
<td width=164><input type='text' name='hiddenMenuNameArray'  value='' size=20 maxlength='20'></td>
<td width=80 ><input type='text' name='hiddenMenuLevelArray' value='' size=5  maxlength='5' onChange='fn_NumberChk(this)'></td>
<td width=360><input type='text' name='hiddenMenuURLArray'   value='' size=50 maxlength='50'></td>
<td width=80 ><a onclick='fRowDelete(this)' style='cursor:hand;' title='라인삭제'>-</a></td>
<td width=16 ></td>
</tr>
<%
    }
%>
</table>
<input type = "button" method="post"  value="저장 "     onclick="menuGovForm.submit()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type = "button" method="post"  value="다시작성 " onclick="javascript:location.reload()">
<p>
</form>
<form name="menuGovMainForm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovMenuGov">
<input type = "hidden" name="execFlag" value="MENUGOV">
<input type = "hidden" name="file" value="<%=parFile %>">
<input type = "button" method="post"  value="이전 화면으로 돌아가기" onclick="menuGovMainForm.submit()">
</form>
<%
} else if(execFlag.equals("MENUGOV_ACTION")){
	String parFile  = safeGetParameter(request,"file");
	String[] menuIDArray    = safeGetParameterValues(request,"hiddenMenuIDArray");
    String[] menuNameArray  = safeGetParameterValues(request,"hiddenMenuNameArray");
    String[] menuLevelArray = safeGetParameterValues(request,"hiddenMenuLevelArray");
    String[] menuURLArray   = safeGetParameterValues(request,"hiddenMenuURLArray");

    boolean result2 = EgovMenuGov.setDataByDATFile(parFile, menuIDArray, menuNameArray, menuLevelArray, menuURLArray);
%>
<link href="/egovcom/css/egovframework/com/com.css" rel="stylesheet" type="text/css">
<form name="menuGovTo" action ="/EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovMenuGov">
<input type = "hidden" name="execFlag" value="MENUGOV">
<table width="840" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td>메뉴저장  결과
        </td>
        <td><%=result2 %>
        </td>
    </tr>
</table>
<br>
</form>
<P>
<form name="menuGovMainForm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovMenuGov">
<input type = "hidden" name="execFlag" value="MENUGOV">
<input type = "hidden" name="file" value="<%=parFile %>">
<input type = "button" method="post"  value="이전 화면으로 돌아가기" onclick="menuGovMainForm.submit()">
</form>
<%
}
%>
</html>