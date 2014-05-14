/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import static kr.co.bsisys.fw.Constants.FILE_UPLOAD_BUFF_SIZE;
import static kr.co.bsisys.fw.Constants.SESSION_DIR_BASE;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bsisys.fw.vo.FileVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @since 2013. 3. 16.
 * @author BS정보시스템/손승범
 */
@Component
public class FileUtil {
  
  private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
  
  //TODO 동작확인
  // @Value("#{application['session.dir.base']}")
  // private static String SESSION_DIR_BASE;
  
  /**
   * 세션 디렉토리명을 반환.
   * 
   * @param sessionId
   * @return
   */
  public static String getSessionDirectoryName(String sessionId) {
    byte[] hash = HashUtil.hashSHA1(sessionId);
    return StringUtil.toHexString(hash, "");
  }
  
  /**
   * 세션 디렉토리를 반환.
   * 
   * @param sessionId
   * @return
   */
  public static File getSessionDirectory(String sessionId) {
    String dirBase = SESSION_DIR_BASE;
    
    if (StringUtil.isEmpty(SESSION_DIR_BASE)) {
      dirBase = File.separator + "temp";
    }
    
    String dirName = getSessionDirectoryName(sessionId);
    
    return new File(dirBase + File.separator + dirName);
  }
  
  /**
   * 세션 디렉토리를 생성.
   * 
   * @param sessionId
   * @return
   */
  public static boolean createSessionDirectory(String sessionId) {
    if (StringUtil.isEmpty(sessionId)) {
      return false;
    }
    
    return getSessionDirectory(sessionId).mkdirs();
  }
  
  /**
   * 세션 디렉토리를 삭제.
   * 
   * @param sessionId
   * @return
   */
  public static boolean deleteSessionDirectory(String sessionId) {
    return rmdirs(getSessionDirectory(sessionId));
  }
  
  /**
   * 디렉토리를 삭제.
   * 
   * @param dir
   * @return
   */
  public static boolean rmdirs(File dir) {
    if (dir == null) {
      return false;
    }
    
    File[] files = dir.listFiles();
    if (files != null) {
      for (int i = 0; i < files.length; i++) {
        if (files[i].isDirectory()) {
          rmdirs(files[i]);
        } else {
          files[i].delete();
        }
      }
    }
    
    return dir.delete();
  }
  
  // public static final int BUFF_SIZE = 2048;
  
  /**
   * 첨부파일을 서버에 저장한다.
   */
  protected void writeUploadedFile(MultipartFile file, String newName,
      String stordFilePath) throws Exception {
    InputStream stream = null;
    OutputStream bos = null;
    
    try {
      stream = file.getInputStream();
      File cFile = new File(stordFilePath);
      
      if (!cFile.isDirectory()) {
        boolean _flag = cFile.mkdir();
        if (!_flag) {
          throw new IOException("Directory creation Failed ");
        }
      }
      
      bos = new FileOutputStream(stordFilePath + File.separator + newName);
      
      int bytesRead = 0;
      byte[] buffer = new byte[FILE_UPLOAD_BUFF_SIZE];
      
      while ((bytesRead = stream.read(buffer, 0, FILE_UPLOAD_BUFF_SIZE)) != -1) {
        bos.write(buffer, 0, bytesRead);
      }
    } catch (Exception e) {
      logger.error("IGNORE: {}", e);
    } finally {
      if (bos != null) {
        try {
          bos.close();
        } catch (Exception ignore) {
          logger.debug("IGNORED: {}", ignore.getMessage());
        }
      }
      if (stream != null) {
        try {
          stream.close();
        } catch (Exception ignore) {
          logger.debug("IGNORED: {}", ignore.getMessage());
        }
      }
    }
  }
  
  /**
   * 서버의 파일을 다운로드한다.
   * 
   * @param request
   * @param response
   * @throws Exception
   */
  public static void downFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String downFileName = "";
    String orgFileName = "";
    
    if ((String) request.getAttribute("downFile") == null) {
      downFileName = "";
    } else {
      downFileName = (String) request.getAttribute("downFile");
    }
    
    if ((String) request.getAttribute("orgFileName") == null) {
      orgFileName = "";
    } else {
      orgFileName = (String) request.getAttribute("orginFile");
    }
    
    orgFileName = orgFileName.replaceAll("\r", "").replaceAll("\n", "");
    
    File file = new File(SESSION_DIR_BASE + File.separator + downFileName);
    
    if (!file.exists()) {
      throw new FileNotFoundException(downFileName);
    }
    
    if (!file.isFile()) {
      throw new FileNotFoundException(downFileName);
    }
    
    byte[] b = new byte[FILE_UPLOAD_BUFF_SIZE]; // buffer size 2K.
    
    response.setContentType("application/x-msdownload");
    response.setHeader("Content-Disposition:", "attachment; filename="
        + new String(orgFileName.getBytes(), "UTF-8"));
    response.setHeader("Content-Transfer-Encoding", "binary");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    
    BufferedInputStream fin = null;
    BufferedOutputStream outs = null;
    
    try {
      fin = new BufferedInputStream(new FileInputStream(file));
      outs = new BufferedOutputStream(response.getOutputStream());
      int read = 0;
      
      while ((read = fin.read(b)) != -1) {
        outs.write(b, 0, read);
      }
    } finally {
      if (outs != null) {
        try {
          outs.close();
        } catch (Exception ignore) {
          logger.debug("IGNORED: {}", ignore.getMessage());
        }
      }
      if (fin != null) {
        try {
          fin.close();
        } catch (Exception ignore) {
          logger.debug("IGNORED: {}", ignore.getMessage());
        }
      }
    }
  }
  
  /**
   * 첨부로 등록된 파일을 서버에 업로드한다.
   */
  public static FileVO uploadFile(MultipartFile file) throws Exception {
    FileVO fileVO = new FileVO();
    // Write File 이후 Move File????
    String newName = "";
    String stordFilePath = SESSION_DIR_BASE;
    String orginFileName = file.getOriginalFilename();
    String contentType = file.getContentType();
    
    int index = orginFileName.lastIndexOf(".");
    // String fileName = orginFileName.substring(0, _index);
    String fileExt = orginFileName.substring(index + 1);
    long size = file.getSize();
    
    // newName 은 Naming Convention에 의해서 생성
    newName = DateUtil.getTimeStamp() + "." + fileExt;
    writeFile(file, newName, stordFilePath);
    // storedFilePath는 지정
    fileVO.setOrginFileName(orginFileName);
    fileVO.setFileName(newName);
    fileVO.setFileExtention(fileExt);
    fileVO.setFilePath(stordFilePath);
    fileVO.setFileSize(size);
    fileVO.setContentType(contentType);
    
    return fileVO;
  }
  
  /**
   * 파일을 실제 물리적인 경로에 생성한다.
   */
  protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
    InputStream stream = null;
    OutputStream bos = null;
    
    try {
      stream = file.getInputStream();
      File cFile = new File(stordFilePath);
      
      if (!cFile.isDirectory())
        cFile.mkdir();
      
      bos = new FileOutputStream(stordFilePath + File.separator + newName);
      
      int bytesRead = 0;
      byte[] buffer = new byte[FILE_UPLOAD_BUFF_SIZE];
      
      while ((bytesRead = stream.read(buffer, 0,
          FILE_UPLOAD_BUFF_SIZE)) != -1) {
        bos.write(buffer, 0, bytesRead);
      }
    } catch (Exception e) {
      logger.error("IGNORED: {}", e.getMessage());
    } finally {
      if (bos != null) {
        try {
          bos.close();
        } catch (Exception ignore) {
          logger.error("IGNORED: {}", ignore.getMessage());
        }
      }
      if (stream != null) {
        try {
          stream.close();
        } catch (Exception ignore) {
          logger.error("IGNORED: {}", ignore.getMessage());
        }
      }
    }
  }
  
  /**
   * 서버 파일에 대하여 다운로드를 처리한다.
   * 
   * @param response
   * @param streFileNm
   *          : 파일저장 경로가 포함된 형태
   * @param orignFileNm
   * @throws Exception
   */
  public void downFile(HttpServletResponse response, String streFileNm, String orignFileNm) throws Exception {
    String downFileName = streFileNm;
    String orgFileName = orignFileNm;
    
    File file = new File(SESSION_DIR_BASE + File.separator + downFileName);
    
    if (!file.exists()) {
      throw new FileNotFoundException(downFileName);
    }
    
    if (!file.isFile()) {
      throw new FileNotFoundException(downFileName);
    }
    
    int fSize = (int) file.length();
    if (fSize > 0) {
      BufferedInputStream in = null;
      
      try {
        in = new BufferedInputStream(new FileInputStream(file));
        
        String mimetype = "text/html"; // "application/x-msdownload"
        
        response.setBufferSize(fSize);
        response.setContentType(mimetype);
        response.setHeader("Content-Disposition:", "attachment; filename=" + orgFileName);
        response.setContentLength(fSize);
        FileCopyUtils.copy(in, response.getOutputStream());
      } finally {
        if (in != null) {
          try {
            in.close();
          } catch (Exception ignore) {
            logger.error("IGNORED: {}", ignore.getMessage());
          }
        }
      }
      
      response.getOutputStream().flush();
      response.getOutputStream().close();
    }
    
  }
  
}
