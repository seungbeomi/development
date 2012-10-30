package kr.co.tsb.comp.batch.admin.web;

import java.util.List;

import javax.validation.Valid;

import kr.co.tsb.comp.batch.admin.dao.QrtzTriggersRepository;
import kr.co.tsb.comp.batch.admin.domain.QrtzTriggersPK;
import kr.co.tsb.comp.batch.admin.domain.QrtzTriggersVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QrtzTriggersController {

	@Autowired
	private QrtzTriggersRepository qrtzTriggersRepository;
	
	@RequestMapping(value="/qrtzTriggers", method=RequestMethod.GET)
	public String index(Model model) {
		List<QrtzTriggersVO> qrtzTriggers = qrtzTriggersRepository.findAll();
		model.addAttribute("qrtzTriggers", qrtzTriggers);
		return "qrtzTriggers/index";
	}
	
	@RequestMapping(value="/qrtzTriggers/{triggerName}", method=RequestMethod.GET)
	public String show(Model model, @PathVariable String triggerName) {
		QrtzTriggersVO qrtzJobDetail = qrtzTriggersRepository.findOne(new QrtzTriggersPK(triggerName));
		model.addAttribute("qrtzjobdetail", qrtzJobDetail);
		return "qrtzTriggers/show";
	}
	
	@RequestMapping(value="/qrtzTriggers/form", method=RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute("qrtzjobdetail", new QrtzTriggersVO());
		return "qrtzTriggers/form";
	}
	
	@RequestMapping(value="/qrtzTriggers/{triggerName}/edit", method=RequestMethod.GET)
	public String edit(@PathVariable String triggerName) {
		return "qrtzTriggers/edit";
	}
	
	@RequestMapping(value="/qrtzTriggers", method=RequestMethod.POST)
	public String create(@Valid QrtzTriggersVO qrtzTriggersVO, BindingResult result) {
		if (result.hasErrors()) {
			return "qrtzTriggers/form";
		}
		
		return "qrtzTriggers/create";
	}
	
	@RequestMapping(value="/qrtzTriggers", method=RequestMethod.PUT)
	public String update(@Valid QrtzTriggersVO qrtzTriggersVO, BindingResult result) {
		if (result.hasErrors()) {
			return "qrtzTriggers/edit";
		}
		return "qrtzTriggers/index";
	}
	
	@RequestMapping(value="/qrtzTriggers/{triggerName}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable String triggerName) {
		return "qrtzTriggers/index";
	}
	
}
