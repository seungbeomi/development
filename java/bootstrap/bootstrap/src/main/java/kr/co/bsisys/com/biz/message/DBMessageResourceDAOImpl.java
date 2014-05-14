package kr.co.bsisys.com.biz.message;

import java.util.List;

import javax.sql.DataSource;

import kr.co.bsisys.fw.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class DBMessageResourceDAOImpl extends JdbcDaoSupport implements DBMessageResourceDAO {
  
  private static final Logger logger = LoggerFactory.getLogger(DBMessageResourceDAOImpl.class);
  
  protected String tableName = "CMM_DBMESSAGE";
  protected String codeColumn = "CODE";
  protected String languageColumn;
  protected String countryColumn;
  protected String variantColumn;
  protected String messageColumn = "MESSAGE";
  protected String findMessageSql;
  
  public void setTableName(String tableName) {
    this.tableName = tableName;
  }
  
  public void setCodeColumn(String codeColumn) {
    this.codeColumn = codeColumn;
  }
  
  public void setLanguageColumn(String languageColumn) {
    this.languageColumn = languageColumn;
  }
  
  public void setCountryColumn(String countryColumn) {
    this.countryColumn = countryColumn;
  }
  
  public void setVariantColumn(String variantColumn) {
    this.variantColumn = variantColumn;
  }
  
  public void setMessageColumn(String messageColumn) {
    this.messageColumn = messageColumn;
  }
  
  public void setFindMessageSql(String findMessageSql) {
    this.findMessageSql = findMessageSql;
  }
  
  protected DBMessageQuery dBMessageQuery = null;
  
  protected DBMessageResourceDAOImpl() {
    super();
  }
  
  @Override
  protected void initDao() {
    DataSource dataSource = getDataSource();
    if (dataSource == null) {
      logger.error("Missing dataSource in spring configuration file.");
      throw new IllegalArgumentException("Missing dataSource in spring"
          + " configuration file.");
    }
    
    this.dBMessageQuery = new DBMessageQuery(dataSource, makeSql(),
        codeColumn, languageColumn, countryColumn, variantColumn,
        messageColumn);
  }
  
  public List<DBMessageVO> findDBMessages() {
    return dBMessageQuery.execute();
  }
  
  protected String makeSql() {
    checkRequiredColumnName(codeColumn, "codeColumn");
    checkNotRequiredColumnName(languageColumn, "languageColumn");
    checkNotRequiredColumnName(countryColumn, "countryColumn");
    checkNotRequiredColumnName(variantColumn, "variantColumn");
    checkRequiredColumnName(messageColumn, "messageColumn");
    checkRequiredColumnName(tableName, "tableName");
    
    StringBuilder sql = null;
    if (StringUtil.isNotEmpty(findMessageSql)) {
      sql = new StringBuilder(findMessageSql);
    } else {
      sql = new StringBuilder("/* DB메시지 조회 */ SELECT ");
      sql.append(codeColumn);
      sql.append(",");
      if (StringUtil.isNotEmpty(languageColumn)) {
        sql.append(languageColumn);
        sql.append(",");
      }
      if (StringUtil.isNotEmpty(countryColumn)) {
        sql.append(countryColumn);
        sql.append(",");
      }
      if (StringUtil.isNotEmpty(variantColumn)) {
        sql.append(variantColumn);
        sql.append(",");
      }
      sql.append(messageColumn);
      sql.append(" FROM ");
      sql.append(tableName);
    }
    
    logger.debug("DBMessageResourceDAOImpl SQL  => [" + sql + "]");
    
    return sql.toString();
  }
  
  protected void checkRequiredColumnName(String value, String columnName) {
    if (StringUtil.isEmpty(value)) {
      logger.error("illegalArgument: " + columnName + " is null or empty.");
      throw new IllegalArgumentException("illegalArgument: " + columnName + " is null or empty.");
    }
  }
  
  protected void checkNotRequiredColumnName(String value, String columnName) {
    if (StringUtil.isNotEmpty(value)) {
      logger.error("illegalArgument: " + columnName + " is empty.");
      throw new IllegalArgumentException("illegalArgument: " + columnName + " is empty.");
    }
  }
  
}
