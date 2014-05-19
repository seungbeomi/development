package kr.co.bsisys.com.web.bind.support;

import kr.co.bsisys.fw.exception.ControllerException;
import kr.co.bsisys.fw.exception.DaoException;
import kr.co.bsisys.fw.exception.ServiceException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ControllerAdvice;

//@ControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler
  public @ResponseBody
  String handleControllerException(ControllerException ex) {
    return "Handled ControllerException";
  }
  
  @ExceptionHandler
  public @ResponseBody
  String handleServiceException(ServiceException ex) {
    return "Handled BusinessException";
  }
  
  @ExceptionHandler
  public @ResponseBody
  String handleDaoException(DaoException ex) {
    return "Handled DaoException";
  }
  
}
