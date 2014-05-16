/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @since 2013. 3. 16.
 * @author BS정보시스템/손승범
 */
public class HashUtil {
  
  private static final Logger logger = LoggerFactory.getLogger(HashUtil.class);
  
  /**
   * 문자열을 MD5 코드로 반환.
   * 
   * @param str
   * @return
   */
  public static byte[] hashMD5(String str) {
    try {
      return hash("MD5", str);
    } catch (NoSuchAlgorithmException e) {
      logger.error("The algorithm is not available in the caller's environment.", e);
      
      return null; // can't happen
    }
  }
  
  /**
   * 문자열을 SHA1 코드로 반환.
   * 
   * @param str
   * @return
   */
  public static byte[] hashSHA1(String str) {
    try {
      return hash("SHA1", str);
    } catch (NoSuchAlgorithmException e) {
      logger.error("The algorithm is not available in the caller's environment.", e);
      
      return null; // can't happen
    }
  }
  
  /**
   * 알고리즘을 이용하여 문자열을 해시코드로 반환.
   * 
   * @param algorithm
   * @param str
   * @return
   * @throws NoSuchAlgorithmException
   */
  public static byte[] hash(String algorithm, String str) throws NoSuchAlgorithmException {
    if (algorithm == null || str == null) {
      return null;
    }
    
    MessageDigest md = MessageDigest.getInstance(algorithm.toUpperCase());
    
    return md.digest(str.getBytes());
  }
  
}
