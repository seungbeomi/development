package kr.co.bsisys.com.biz.message;

import kr.co.bsisys.fw.vo.VO;

/**
 * 
 * @since 2013. 5. 1.
 * @author BS정보시스템/손승범
 */
public class DBMessageVO extends VO {
  
  private static final long serialVersionUID = 1L;
  
  protected String code;
  protected String language;
  protected String country;
  protected String variant;
  protected String message;
  
  public DBMessageVO() {
  }
  
  public DBMessageVO(String code, String message) {
    this.code = code;
    this.message = message;
  }
  
  public DBMessageVO(String code, String language, String country,
      String variant, String message) {
    this.code = code;
    this.language = language;
    this.country = country;
    this.variant = variant;
    this.message = message;
  }
  
  public String getCode() {
    return code;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getLanguage() {
    return language;
  }
  
  public void setLanguage(String language) {
    this.language = language;
  }
  
  public String getCountry() {
    return country;
  }
  
  public void setCountry(String country) {
    this.country = country;
  }
  
  public String getVariant() {
    return variant;
  }
  
  public void setVariant(String variant) {
    this.variant = variant;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  @Override
  public String toString() {
    return "DBMessage [code=" + code + ", language=" + language
        + ", country=" + country + ", variant=" + variant
        + ", message=" + message + "]";
  }
  
}
