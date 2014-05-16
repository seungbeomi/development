/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.message;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractMessageSource;

/**
 * 
 * @since 2013. 5. 1.
 * @author BS정보시스템/손승범
 */
public class DataSourceMessageSource extends AbstractMessageSource implements InitializingBean {
  
  private static final Logger logger = LoggerFactory.getLogger(DataSourceMessageSource.class);
  
  protected final Map<String, Map<Locale, MessageFormat>> cachedMessageFormats = new HashMap<String, Map<Locale, MessageFormat>>();
  
  protected Map<Locale, Properties> cachedMergedProperties = new HashMap<Locale, Properties>();
  
  protected Locale defaultLocale = new Locale(Locale.getDefault().getLanguage());
  
  protected DBMessageResourceDAO dbMessageResourceDAO = null;
  
  public void setDefaultLocale(Locale defaultLocale) {
    this.defaultLocale = defaultLocale;
  }
  
  public void setDbMessageResourceDAO(DBMessageResourceDAO dbMessageResourceDAO) {
    this.dbMessageResourceDAO = dbMessageResourceDAO;
  }
  
  public void afterPropertiesSet() {
    logger.debug("afterPropertiesSet");
    readMessagesFromDataSource();
  }
  
  public synchronized void reloadDataSourceMessage() {
    readMessagesFromDataSource();
  }
  
  protected synchronized void readMessagesFromDataSource() {
    logger.debug("readMessageFromDataSource");
    cachedMergedProperties.clear();
    cachedMessageFormats.clear();
    
    List<DBMessageVO> messages = dbMessageResourceDAO.findDBMessages();
    
    for (DBMessageVO message : messages) {
      if (message.code != null && message.message != null) {
        mapMessage(message);
      }
    }
    
    logger.debug("get MessageResource from DAO.");
  }
  
  protected void mapMessage(DBMessageVO message) {
    Locale locale = createLocale(message);
    
    Properties messages = getMessages(locale);
    
    messages.setProperty(message.getCode(), message.getMessage());
    //logger.debug("add Message[" + message.getMessage() + "] (code[" + message.getCode() + "], locale[" + locale + "])");
  }
  
  protected Locale createLocale(DBMessageVO message) {
    if (message.getLanguage() == null) {
      if (defaultLocale != null) {
        return defaultLocale;
      }
      
      logger.error("Can't resolve Locale.Define Locale" + " in MessageSource or Defaultlocale.");
      throw new IllegalArgumentException("Can't resolve Locale." + "Define Locale in MessageSource or Defaultlocale.");
    }
    
    if (message.getCountry() == null) {
      return new Locale(message.getLanguage());
    }
    
    if (message.getVariant() == null) {
      return new Locale(message.getLanguage(), message.getCountry());
    }
    
    return new Locale(message.getLanguage(), message.getCountry(), message.getVariant());
  }
  
  protected Properties getMessages(Locale locale) {
    Properties messages = cachedMergedProperties.get(locale);
    
    if (messages == null) {
      messages = new Properties();
      cachedMergedProperties.put(locale, messages);
    }
    
    return messages;
  }
  
  @Override
  protected synchronized String resolveCodeWithoutArguments(String code,
      Locale locale) {
    String msg = internalResolveCodeWithoutArguments(code, locale);
    if (msg == null) {
      logger.debug("could not resolve [" + code + "] for locale [" + locale + "]");
    }
    return msg;
  }
  
  protected String internalResolveCodeWithoutArguments(String code, Locale locale) {
    
    String msg = getMessages(locale).getProperty(code);
    
    if (msg != null) {
      return msg;
    }
    
    List<Locale> locales = getAlternativeLocales(locale);
    
    for (int i = 0; i < locales.size(); i++) {
      msg = getMessages(locales.get(i)).getProperty(code);
      if (msg != null) {
        return msg;
      }
    }
    
    return null;
  }
  
  protected List<Locale> getAlternativeLocales(Locale locale) {
    List<Locale> locales = new ArrayList<Locale>();
    
    if (locale.getVariant().length() > 0) {
      locales.add(new Locale(locale.getLanguage(), locale.getCountry()));
    }
    
    if (locale.getCountry().length() > 0) {
      locales.add(new Locale(locale.getLanguage()));
    }
    
    if (defaultLocale != null && !locale.equals(defaultLocale)) {
      if (defaultLocale.getVariant().length() > 0) {
        locales.add(defaultLocale);
      }
      
      if (defaultLocale.getCountry().length() > 0) {
        locales.add(new Locale(defaultLocale.getLanguage(),
            defaultLocale.getCountry()));
      }
      
      if (defaultLocale.getLanguage().length() > 0) {
        locales.add(new Locale(defaultLocale.getLanguage()));
      }
    }
    
    return locales;
  }
  
  @Override
  protected synchronized MessageFormat resolveCode(String code, Locale locale) {
    MessageFormat messageFormat = getMessageFormat(code, locale);
    
    if (messageFormat != null) {
      logger.debug("resolved [" + code + "] for locale [" + locale + "] => [" + messageFormat + "]");
      return messageFormat;
    }
    
    List<Locale> locales = getAlternativeLocales(locale);
    
    for (int i = 0; i < locales.size(); i++) {
      messageFormat = getMessageFormat(code, locales.get(i));
      if (messageFormat != null) {
        logger.debug("resolved [" + code + "] for locale [" + locale + "] => [" + messageFormat + "]");
        return messageFormat;
      }
    }
    
    if (messageFormat == null) {
      logger.debug("could not resolve [" + code + "] for locale [" + locale + "]");
    }
    
    return null;
  }
  
  protected MessageFormat getMessageFormat(String code, Locale locale) {
    Map<Locale, MessageFormat> localeMap = this.cachedMessageFormats.get(code);
    
    if (localeMap != null) {
      MessageFormat result = localeMap.get(locale);
      
      if (result != null) {
        return result;
      }
    }
    
    String msg = getMessages(locale).getProperty(code);
    
    if (msg != null) {
      if (localeMap == null) {
        localeMap = new HashMap<Locale, MessageFormat>();
        this.cachedMessageFormats.put(code, localeMap);
      }
      
      MessageFormat result = createMessageFormat(msg, locale);
      localeMap.put(locale, result);
      
      return result;
    }
    
    return null;
  }
  
}
