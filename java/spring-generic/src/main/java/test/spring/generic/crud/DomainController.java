package test.spring.generic.crud;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.tree.AbstractEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public abstract class DomainController<T extends AbstractEntity> {  

	private final Log LOG = LogFactory.getLog(getClass());  
	  
	 public abstract DomainService<T> getDomainService();  
	  
	 /** 
	  * Generic main page controller 
	  *  
	  * @return ModelAndView 
	  */  
	 @RequestMapping(value = "/management", method = RequestMethod.GET)  
	 public ModelAndView main() {  
	  LOG.debug("main page");  
	  return new ModelAndView(getMainView());  
	 }  
	  
	 protected abstract String getMainView();  
	  
	 /** 
	  * Generic entity list controller (refreshing, sorting, filtering) 
	  *  
	  * @param gridViewModel 
	  * @param response 
	  * @return ResponseGridViewModel - model in JSON for grid filling 
	  */  
	 @RequestMapping(value = "/management/index", method = RequestMethod.POST)  
	 @ResponseBody  
	 public ResponseGridViewModel<T> index(@RequestBody RequestGridViewModel gridViewModel,  
	   HttpServletResponse response) {  
	  prepareGridViewModel(gridViewModel);  
	  return getDomainService().getFilteredEntitiy(gridViewModel);  
	 }  
	  
	 /** 
	  * Process filtering criteria 
	  *  
	  * @param gridViewModel 
	  */  
	 protected void prepareGridViewModel(RequestGridViewModel gridViewModel) {  
	  if (!gridViewModel.getCriteria().isEmpty()) {  
	   Map<String, Object> criteriaMap = new HashMap<String, Object>();  
	  
	   for (String key : gridViewModel.getCriteria().keySet()) {  
	    Object value = gridViewModel.getCriteria().get(key);  
	    try {  
	     Method m = null;  
	  
	     try {  
	      m = getDomainService().getPersistentClass().getMethod(constructGetter(key));  
	     } catch (NoSuchMethodException e) {  
	      // it's not GET  
	      // let's try IS  
	      m = getDomainService().getPersistentClass().getMethod(constructIsGetter(key));  
	     }  
	  
	  
	     if (m.getReturnType().equals(value.getClass())) {  
	      criteriaMap.put(key, value);  
	     } else if (Integer.class.equals(m.getReturnType())) {  
	      criteriaMap.put(key, Integer.valueOf((String) value));  
	     } else if (Long.class.equals(m.getReturnType())) {  
	      criteriaMap.put(key, Long.valueOf((String) value));  
	     } else if (Boolean.class.equals(m.getReturnType())) {  
	      criteriaMap.put(key, Boolean.valueOf((String) value));  
	     }  
	  
	    } catch (Exception e) {  
	     // skip method - out of the scope  
	    }  
	   }  
	  
	   gridViewModel.setCriteria(criteriaMap);  
	  }  
	 }  
	  
	 private String constructGetter(String strKey) {  
	  return "get" + WordUtils.capitalize(strKey);  
	 }  
	  
	 private String constructIsGetter(String strKey) {  
	  return "is" + WordUtils.capitalize(strKey);  
	 }  
	  
	 /** 
	  * Insert generic controller 
	  *  
	  * @param entity 
	  * @param response 
	  * @throws IOException 
	  */  
	 @RequestMapping(value = "/management/insert", method = RequestMethod.POST)  
	 protected void doInsert(@RequestBody T entity, HttpServletResponse response) throws IOException {  
	  
	  entity.setId(null);  
	  LOG.debug("add entity : " + entity);  
	  
	  // validation entity  
	  Map<String, String> failures = validateOnInsert(entity);  
	  if (!failures.isEmpty()) {  
	   response.getWriter().write(validationMessages(failures));  
	  } else {  
	   getDomainService().insert(entity);  
	  }  
	 }  
	  
	 /** 
	  * Update generic controller 
	  *  
	  * @param entity 
	  * @param response 
	  * @throws IOException 
	  */  
	 @RequestMapping(value = "/management/update", method = RequestMethod.POST)  
	 protected void doUpdate(@RequestBody T entity, HttpServletResponse response) throws IOException {  
	  LOG.debug("update entity : " + entity);  
	  
	  // validation entity  
	  Map<String, String> failures = validateOnUpdate(entity);  
	  if (!failures.isEmpty()) {  
	   response.getWriter().write(validationMessages(failures));  
	  } else {  
	   getDomainService().update(entity);  
	  }  
	 }  
	  
	 /** 
	  * Delete generic controller 
	  *  
	  * @param entityId 
	  * @param response 
	  */  
	 @RequestMapping(value = "/management/delete", method = RequestMethod.POST)  
	 public void doDelete(@RequestParam("id") long entityId, HttpServletResponse response) {  
	  LOG.debug("remove item ID : " + entityId);  
	  getDomainService().removeEntity(entityId);  
	 }  
	  
	 protected String validationMessages(Map<String, String> failures) {  
	  StringBuffer sb = new StringBuffer();  
	  
	  for (String failureMsg : failures.values()) {  
	   if (sb.length() > 0) {  
	    sb.append("\",");  
	   }  
	   sb.append("\"").append(failureMsg).append("\"");  
	  }  
	  
	  if (failures.size() > 0) {  
	   sb.insert(0, "{\"error\":[");  
	   sb.append("]}");  
	  }  
	  
	  return sb.toString();  
	 }  
	  
	 /** 
	  * Generic entity validation on insert - could be overridden 
	  *  
	  * @param entity 
	  * @return 
	  */  
	 protected Map<String, String> validateOnInsert(T entity) {  
	  return new HashMap<String, String>();  
	 }  
	  
	 /** 
	  * Generic entity validation on update - could be overridden 
	  *  
	  * @param entity 
	  * @return 
	  */  
	 protected Map<String, String> validateOnUpdate(T entity) {  
	  return new HashMap<String, String>();  
	 }  
	  
	}  