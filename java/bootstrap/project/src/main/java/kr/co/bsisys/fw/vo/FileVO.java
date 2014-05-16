/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.vo;

/**
 * 파일 VO
 * 
 * @since 2013. 5. 18.
 * @author BS정보시스템/손승범
 */
public class FileVO extends VO {

  private static final long serialVersionUID = -5031279539520124661L;

  private String orginFileName;
  private String fileName;
  private String fileExtention;
  private String filePath;
  private String contentType;
  private long fileSize;

  public String getOrginFileName() {
    return orginFileName;
  }

  public void setOrginFileName(String orginFileName) {
    this.orginFileName = orginFileName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileExtention() {
    return fileExtention;
  }

  public void setFileExtention(String fileExtention) {
    this.fileExtention = fileExtention;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  @Override
  public String toString() {
    return "FileVO [orginFileName=" + orginFileName + ", fileName=" + fileName + ", fileExtention=" + fileExtention + ", filePath=" + filePath + ", contentType=" + contentType + ", fileSize="
        + fileSize + "]";
  }

}
