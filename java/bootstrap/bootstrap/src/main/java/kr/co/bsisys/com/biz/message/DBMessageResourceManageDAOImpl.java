/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * DB 메세지 리소스 관리 DAO
 * 
 * @since 2013. 5. 1.
 * @author BS정보시스템/손승범
 */
@Repository
public class DBMessageResourceManageDAOImpl implements DBMessageResourceManageDAO {
  
  @Inject
  JdbcTemplate jdbcTemplate;
  
  public static final String DEF_DB_MESSAGE_LIST_QUERY = "/* DBMessageResourceManageDAOImpl.DEF_DB_MESSAGE_LIST_QUERY */ SELECT CODE, MESSAGE FROM CMM_DBMESSAGE ORDER BY CODE";
  public static final String DEF_DB_MESSAGE_SELECT_QUERY = "/* DBMessageResourceManageDAOImpl.DEF_DB_MESSAGE_SELECT_QUERY */ SELECT CODE, MESSAGE FROM CMM_DBMESSAGE WHERE CODE = ?";
  public static final String DEF_DB_MESSAGE_INSERT_QUERY = "/* DBMessageResourceManageDAOImpl.DEF_DB_MESSAGE_INSERT_QUERY */ INSERT INTO CMM_DBMESSAGE(CODE, MESSAGE) VALUES (?, ?)";
  public static final String DEF_DB_MESSAGE_UPDATE_QUERY = "/* DBMessageResourceManageDAOImpl.DEF_DB_MESSAGE_UPDATE_QUERY */ UPDATE CMM_DBMESSAGE SET MESSAGE = ? WHERE CODE = ?";
  public static final String DEF_DB_MESSAGE_DELETE_QUERY = "/* DBMessageResourceManageDAOImpl.DEF_DB_MESSAGE_DELETE_QUERY */ DELETE FROM CMM_DBMESSAGE WHERE CODE = ?";
  
  private String listQuery;
  private String selectQuery;
  private String insertQuery;
  private String updateQuery;
  private String deleteQuery;
  
  public DBMessageResourceManageDAOImpl() {
    this.listQuery = DEF_DB_MESSAGE_LIST_QUERY;
    this.selectQuery = DEF_DB_MESSAGE_SELECT_QUERY;
    this.insertQuery = DEF_DB_MESSAGE_INSERT_QUERY;
    this.updateQuery = DEF_DB_MESSAGE_UPDATE_QUERY;
    this.deleteQuery = DEF_DB_MESSAGE_DELETE_QUERY;
  }
  
  @Override
  public List<DBMessageVO> list() {
    return jdbcTemplate.query(listQuery, new String[] {},
        new RowMapper<DBMessageVO>() {
          @Override
          public DBMessageVO mapRow(ResultSet rs, int rowNum)
              throws SQLException {
            String code = rs.getString(1);
            String message = rs.getString(2);
            return new DBMessageVO(code, message);
          }
        });
  }
  
  @Override
  public DBMessageVO select(DBMessageVO dbMessage) {
    return jdbcTemplate.queryForObject(selectQuery,
        new String[] { dbMessage.getCode() },
        new RowMapper<DBMessageVO>() {
          @Override
          public DBMessageVO mapRow(ResultSet rs, int rowNum)
              throws SQLException {
            String code = rs.getString(1);
            String message = rs.getString(2);
            return new DBMessageVO(code, message);
          }
        });
  }
  
  @Override
  public int insert(DBMessageVO dbMessage) {
    return jdbcTemplate.update(insertQuery, dbMessage.getCode(),
        dbMessage.getMessage());
  }
  
  @Override
  public int update(DBMessageVO dbMessage) {
    return jdbcTemplate.update(updateQuery, dbMessage.getMessage(),
        dbMessage.getCode());
    
  }
  
  @Override
  public int delete(String code) {
    return jdbcTemplate.update(deleteQuery, code);
    
  }
  
  // setter, getter ----------------------------------------------------
  public String getListQuery() {
    return listQuery;
  }
  
  public void setListQuery(String listQuery) {
    this.listQuery = listQuery;
  }
  
  public String getSelectQuery() {
    return selectQuery;
  }
  
  public void setSelectQuery(String selectQuery) {
    this.selectQuery = selectQuery;
  }
  
  public String getInsertQuery() {
    return insertQuery;
  }
  
  public void setInsertQuery(String insertQuery) {
    this.insertQuery = insertQuery;
  }
  
  public String getUpdateQuery() {
    return updateQuery;
  }
  
  public void setUpdateQuery(String updateQuery) {
    this.updateQuery = updateQuery;
  }
  
  public String getDeleteQuery() {
    return deleteQuery;
  }
  
  public void setDeleteQuery(String deleteQuery) {
    this.deleteQuery = deleteQuery;
  }
  
}
