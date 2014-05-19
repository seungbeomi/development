/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.vo;

/**
 * 첨부파일VO
 * 
 * @since 2013. 7. 7.
 * @author BS정보시스템/손승범
 */
public class AttachFile extends VO {

  /** 첨부파일ID */
  private String atchFileId;
  /** 파일연번 */
  private String fileSec;
  /** 원파일명 */
  private String orgFileName;
  /** 저장파일명 */
  private String fileName;
  /** 파일저장경로 */
  private String filePath;
  /** 파일확장자 */
  private String fileExt;
  /** 파일크기 */
  private int fileSize;

  public String getAtchFileId() {
    return atchFileId;
  }

  public void setAtchFileId(String atchFileId) {
    this.atchFileId = atchFileId;
  }

  public String getFileSec() {
    return fileSec;
  }

  public void setFileSec(String fileSec) {
    this.fileSec = fileSec;
  }

  public String getOrgFileName() {
    return orgFileName;
  }

  public void setOrgFileName(String orgFileName) {
    this.orgFileName = orgFileName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getFileExt() {
    return fileExt;
  }

  public void setFileExt(String fileExt) {
    this.fileExt = fileExt;
  }

  public int getFileSize() {
    return fileSize;
  }

  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }

}
