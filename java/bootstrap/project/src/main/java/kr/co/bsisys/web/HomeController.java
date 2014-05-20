package kr.co.bsisys.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
  
  @Resource(name = "CmmnCodeManageService")
  EgovCcmCmmnCodeManageService ccmCmmnCodeManageService;
  
  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
  
  @Value("#{application[pageUnit]}")
  private int pageUnit;
  
  @Value("#{application[pageSize]}")
  private int pageSize;
  
  /**
   * Simply selects the home view to render by returning its name.
   * 
   * @throws Exception
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) throws Exception {
    logger.info("Welcome home! The client locale is {}.", locale);
    
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
    
    String formattedDate = dateFormat.format(date);
    
    model.addAttribute("serverTime", formattedDate);
    
    CmmnCodeVO searchVO = new CmmnCodeVO();
    ccmCmmnCodeManageService.selectCmmnCodeList(searchVO);
    
    return "home";
  }
  
}
