/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 메일 VO
 * 
 * @since 2013. 7. 7.
 * @author BS정보시스템/손승범
 */
public class Mail extends VO {

  private String to;
  private String from;
  private String contents;
  private String title;
  private String attachFileName;
  private String attachFilePath;
  private List<AttachFile> attachFileList = new ArrayList<AttachFile>();

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAttachFileName() {
    return attachFileName;
  }

  public void setAttachFileName(String attachFileName) {
    this.attachFileName = attachFileName;
  }

  public String getAttachFilePath() {
    return attachFilePath;
  }

  public void setAttachFilePath(String attachFilePath) {
    this.attachFilePath = attachFilePath;
  }

  public List<AttachFile> getAttachFileList() {
    return attachFileList;
  }

  public void setAttachFileList(List<AttachFile> attachFileList) {
    this.attachFileList = attachFileList;
  }

}
