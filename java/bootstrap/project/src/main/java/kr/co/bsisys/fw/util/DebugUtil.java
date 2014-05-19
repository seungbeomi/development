/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 시  스  템 : 
 * 프로그램ID : DebugUtil.java
 * 프로그램명 : 
 * 설      명 : 
 * 작  성  자 : BS정보시스템/손승범
 * 작  성  일 : 2013. 11. 20.
 * </pre>
 */
public class DebugUtil {
  
  /**
   * List 의 내용 출력
   * 
   * @param list
   * @return
   */
  public static String toString(List<Map<String, Object>> list) {
    StringBuffer sb = new StringBuffer();
    
    sb.append("=> List size = " + list.size());
    
    if (list.size() > 0) {
      sb.append("\n");
      
      // HEADER
      Map<String, Object> firstRow = list.get(0);
      sb.append(" | ");
      for (Map.Entry<String, Object> row : firstRow.entrySet()) {
        String key = row.getKey();
        sb.append(key);
        sb.append(" | ");
      }
      sb.append("\n");
      
      // BODY
      for (Map<String, Object> map : list) {
        sb.append(" | ");
        for (Map.Entry<String, Object> row : map.entrySet()) {
          Object value = row.getValue();
          sb.append(value);
          sb.append(" | ");
        }
        sb.append("\n");
      }
    }
    
    return sb.toString();
  }
  
}
