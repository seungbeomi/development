package kr.co.tsb.core;

public interface Constants {

	String CR = "¥r";
	String LF = "¥n";
	String CRLF = CR + LF;
	String FILE_SEPARATOR = System.getProperty("file.separator");
	String LINE_SEPARATOR = System.getProperty("line.separator");
	boolean IS_MAC = CR.equals(LINE_SEPARATOR);
	boolean IS_UNIX = LF.equals(LINE_SEPARATOR);
	boolean IS_WINDOW = CRLF.equals(LINE_SEPARATOR);
	String CHARSET_UTF_8 = "UTF-8";
	String CHARSET_ISO_8859_1 = "ISO8859-1";
	String CHARSET_EUC_KR = "EUC-KR";
	String HTTP_METHOD_GET = "GET";
	String HTTP_METHOD_POST = "POST";
	String HTTP_METHOD_HEAD = "HEAD";
	String HTTP_METHOD_PUT = "PUT";
	String HTTP_METHOD_DELETE = "DELETE";
	String HTTP_METHOD_OPTIONS = "OPTIONS";
	String HTTP_METHOD_TRACE = "TRACE";

}
