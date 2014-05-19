/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.web;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import kr.co.bsisys.biz.svc.EmpSvc;
import kr.co.bsisys.biz.vo.Emp;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * EMP 컨트롤러
 * 
 * @since 2014. 5. 1.
 * @author BS정보시스템/손승범
 */
@Controller
@RequestMapping("/emp")
public class EmpController {
  
  @Inject
  EmpSvc empSvc;
  
  ///////////////////////////////////////////////////////////////////////////
  // SELECT
  
  // 전체리스트조회
  @RequestMapping(method = RequestMethod.GET)
  public String select(Model model) {
    List<Emp> result = empSvc.findAll();
    model.addAttribute("empList", result);
    return "emp/index";
  }
  
  // 단건조회(상세)
  @RequestMapping(value = "/{empNo}", method = RequestMethod.GET)
  public String selectOne(Model model, @PathVariable("empNo") int empNo) {
    Emp result = empSvc.findOne(empNo);
    model.addAttribute("emp", result);
    return "emp/show";
  }
  
  ///////////////////////////////////////////////////////////////////////////
  // INSERT
  
  // 입력폼
  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String insert(Model model, @ModelAttribute("emp") Emp emp) {
    return "emp/insert";
  }
  
  // 입력
  @Transactional
  @RequestMapping(method = RequestMethod.POST)
  public String insert(Model model, @ModelAttribute("emp") @Valid Emp emp, BindingResult result) {
    if (result.hasErrors()) {
      return "emp/insert";
    }
    empSvc.save(emp);
    return "redirect:/emp/" + emp.getEmpNo() + "?form"; // 수정
  }
  
  ///////////////////////////////////////////////////////////////////////////
  // UPDATE
  
  //수정폼
  @RequestMapping(value = "/{empNo}", params = "form", method = RequestMethod.GET)
  public String edit(Model model, @PathVariable("empNo") int empNo, @ModelAttribute("emp") @Valid Emp emp) {
    // 비즈니스로직
    Emp result = empSvc.findOne(empNo);
    model.addAttribute("emp", result);
    return "emp/update";
  }
  
  // 수정
  @Transactional
  @RequestMapping(value = "/{empNo}", params = "form", method = RequestMethod.PUT)
  public String update(@PathVariable("empNo") String empNo, @ModelAttribute("emp") @Valid Emp emp,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "emp/update";
    }
    empSvc.save(emp);
    return "redirect:/emp";
  }
  
  // /////////////////////////////////////////////////////////////////////////
  // DELETE
  
  // 삭제
  @Transactional
  @RequestMapping(value = "/{empNo}", method = RequestMethod.DELETE)
  public String delete(@PathVariable("empNo") int empNo) {
    empSvc.delete(empNo);
    return "redirect:/emp";
  }
}
