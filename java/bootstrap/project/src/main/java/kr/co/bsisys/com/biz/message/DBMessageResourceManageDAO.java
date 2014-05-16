/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.message;

import java.util.List;

/**
 * 메시지관리 DAO
 * 
 * @since 2013. 5. 1.
 * @author BS정보시스템/손승범
 */
public interface DBMessageResourceManageDAO {
  
  List<DBMessageVO> list();
  
  DBMessageVO select(DBMessageVO dbMessage);
  
  int insert(DBMessageVO dbMessage);
  
  int update(DBMessageVO dbMessage);
  
  int delete(String code);
}
