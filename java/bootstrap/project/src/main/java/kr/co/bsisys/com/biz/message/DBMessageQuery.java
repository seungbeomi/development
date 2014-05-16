package kr.co.bsisys.com.biz.message;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.object.MappingSqlQuery;

public class DBMessageQuery extends MappingSqlQuery<DBMessageVO> {
  
  private static final Logger logger = LoggerFactory.getLogger(DBMessageQuery.class);
  
  protected String rsCodeColumn = null;
  protected String rsLanguageColumn = null;
  protected String rsCountryColumn = null;
  protected String rsVariantColumn = null;
  protected String rsMessageColumn = null;
  
  public DBMessageQuery(DataSource ds, String sql, String codeColumn,
      String languageColumn, String countryColumn, String variantColumn,
      String messageColumn) {
    super(ds, sql);
    rsCodeColumn = codeColumn;
    rsLanguageColumn = languageColumn;
    rsCountryColumn = countryColumn;
    rsVariantColumn = variantColumn;
    rsMessageColumn = messageColumn;
    compile();
  }
  
  @Override
  protected DBMessageVO mapRow(ResultSet rs, int rowNum) throws SQLException {
    String code = null;
    String language = null;
    String country = null;
    String variant = null;
    String message = null;
    
    code = rs.getString(rsCodeColumn);
    if (code == null) {
      code = "";
      if (logger.isWarnEnabled()) {
        logger.warn("MessageCode is null");
      }
    }
    
    if (rsLanguageColumn != null) {
      language = rs.getString(rsLanguageColumn);
      if (language == null) {
        language = "";
      }
    }
    
    if (rsCountryColumn != null) {
      country = rs.getString(rsCountryColumn);
      if (country == null) {
        country = "";
      }
    }
    
    if (rsVariantColumn != null) {
      variant = rs.getString(rsVariantColumn);
      if (variant == null) {
        variant = "";
      }
    }
    
    message = rs.getString(rsMessageColumn);
    if (message == null) {
      message = "";
    }
    
    //logger.debug(code + "," + language + "," + country + "," + variant + "," + message);
    
    return new DBMessageVO(code, language, country, variant, message);
  }
  
}
