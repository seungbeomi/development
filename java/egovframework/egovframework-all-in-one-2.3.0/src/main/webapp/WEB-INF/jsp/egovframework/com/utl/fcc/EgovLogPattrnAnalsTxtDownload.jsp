<%--
  Class Name : EgovLogPattrnAnalsTxtDownload.jsp
  Description : 로그패턴분석 TXT 파일 다운로드
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.06.25    장동한          최초 생성

    author   : 장동한
    since    : 2010.06.25
--%>
<%@ page language="java" contentType="text/unknown; charset=utf-8" buffer="none"%>
<%@ page import="java.net.URLEncoder" %>
<%@page import="java.util.*"  %>
<%@page import="java.util.regex.*"  %>
<%@page import="java.text.*"  %>
<%@page import="java.io.*"  %>
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

String name ="LogPattrn";

response.setHeader("Content-Disposition","attachment; filename="+URLEncoder.encode(name, "utf-8")+".txt");

try {

	if( !getServerPath(sTxtLogPattrnPath) ){
		out.println("Thie Server Path Is Now Accessible!");
	}else{

		if(!sTxtLogPattrnPath.equals("")){


    	fileLogPattrn = new File(sTxtLogPattrnPath);

    	//파일 존재 체크
    	if(!fileLogPattrn.isFile()) {
    		out.println("디렉토리/파일명이 올바르지 않습니다!");
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
                  out.println( s );
                  out.println("<br>");
           	}

        }
        ////////////////////////////////////////////////////////////////
    	}

	}//end sTxtLogPattrnPath
  }//end getServerPath
}catch(Exception ex){
	System.out.println(ex);	// 2011.10.10 보안점검 후속조치
}finally{
	if(fileLogPattrn != null){
		try{
			fileLogPattrn=null;
		} catch(Exception e){
			System.out.println("IGNORE: " + e);	// 2011.10.10 보안점검 후속조치
		}
	}
	if(in != null){
		try{in.close();
		} catch(Exception e){
			System.out.println("IGNORE: " + e);	// 2011.10.10 보안점검 후속조치
		}
	}
}

%>