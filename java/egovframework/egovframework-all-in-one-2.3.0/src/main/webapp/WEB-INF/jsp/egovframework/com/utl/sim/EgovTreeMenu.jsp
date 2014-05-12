<%--
  Class Name : EgovTreeMenu.jsp
  Description : 트리메뉴 생성하는 화면
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
<title>TEST</title>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<script type="text/javascript">
var imgpath = "<c:url value='/images/egovframework/com/cmm/utl/'/>";
</script>
<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovMenuGov.js"></script>
<script language="JavaScript">

    function fOpen()
    {
        document.menuGovForm.submit();
    }
</script>
</head>
<body>
<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="TREEMENUGOV";
}

if(execFlag.equals("TREEMENUGOV")){
%>
<!-- 메뉴 화면  시작-->
<form name="menuGovForm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovTreeMenu">
<input type = "hidden" name="execFlag" value="TREEMENUGOV_ACTION">
<input type = "hidden" name="char" value="|" size="50">
<input type = "hidden" name="field" value="4" size="50">
<table width="840" border="0" cellpadding="0" cellspacing="1" class="table-register">
<tr>
<td width="100" height="23" class="title_left" nowrap>트리메뉴</td>
<td>
기본적으로 메뉴화면에서 생성된 파일을 트리형태 메뉴로 보여줌<br>
ex)/product/jeus/egovProps/tmp/sample/menutest.dat
</td>
</tr>
<tr>
<td width="100" height="23" class="title_left" nowrap>메뉴변환파일</td>
<td><input type = "text" name="file" value="/product/jeus/egovProps/tmp/sample/menutest.dat" size="50"></td>
</tr>
</table>

<input type = "button" method="post"  value="실행" onclick="menuGovForm.submit()">
</form>

</body>
<%
} else if(execFlag.equals("TREEMENUGOV_ACTION")){

	String parFile  = safeGetParameter(request,"file");
    String parChar  = safeGetParameter(request,"char");
    boolean result2 = false;
    int parField    = Integer.parseInt(safeGetParameter(request,"field"));

    Vector result1  = EgovMenuGov.parsFileByMenuChar(parFile, parChar, parField);

    if(result1.size() > 0 ) result2 = true;
%>
<!-- PDF 파일변환 결과화면 시작 -->
<link href="/egovcom/css/egovframework/com/com.css" rel="stylesheet" type="text/css">
<form name="menuGovTo" action ="/EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovMenuGov">
<input type = "hidden" name="execFlag" value="TREEMENUGOV">
<table width="840" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td>트리메뉴 변환  결과</td>
        <td><%=result2 %></td>
    </tr>
</table>
<br>
<div class="tree">
<script type="text/javascript">
    var Tree = new Array;
    // nodeId | parentNodeId | nodeName | nodeUrl
<%
	    String str = "";
	    String Temp = "";

	    for (int j = 0; j < result1.size()-1; j++) {
	        ArrayList arr = (ArrayList)result1.elementAt(j);
	        System.out.println((String)arr.get(0)+"|"+(String)arr.get(1)+"|"+(String)arr.get(2)+"|"+(String)arr.get(3));
	        Temp = (String)arr.get(0)+"|"+(String)arr.get(1)+"|"+(String)arr.get(2)+"|"+(String)arr.get(3);
%>
    Tree[<%=j%>]="<%=Temp %>";
<%
        }

%>
    createTree(Tree);
</script>
</div>
</form>
<P>
<form name="TREEMENUGOVMainForm" action ="/EgovPageLink.do"  method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovTreeMenu">
<input type = "hidden" name="execFlag" value="TREEMENUGOV">
<input type = "hidden" name="file" value="<%=parFile %>">
<input type = "button" method="post"  value="이전 화면으로 돌아가기" onclick="TREEMENUGOVMainForm.submit()">
</form>
<%
}
%>
</html>