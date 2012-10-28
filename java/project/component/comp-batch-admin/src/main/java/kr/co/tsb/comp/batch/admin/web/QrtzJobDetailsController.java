package kr.co.tsb.comp.batch.admin.web;

import java.util.List;

import javax.validation.Valid;

import kr.co.tsb.comp.batch.admin.dao.QrtzJobDetailsRepository;
import kr.co.tsb.comp.batch.admin.domain.QrtzJobDetailsPK;
import kr.co.tsb.comp.batch.admin.domain.QrtzJobDetailsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QrtzJobDetailsController {
	
	@Autowired
	private QrtzJobDetailsRepository qrtzJobDetailsRepository;
	
	@Autowired
	private QrtzJobDetailsValidator qrtzJobDetailsValidator;

	@RequestMapping(value="/qrtzjobdetails", method=RequestMethod.GET)
	public String index(Model model) {
		List<QrtzJobDetailsVO> qrtzjobdetails = qrtzJobDetailsRepository.findAll();
		model.addAttribute("qrtzjobdetails", qrtzjobdetails);
		return "qrtzjobdetails.index";
	}
	
	@RequestMapping(value="/qrtjobdetails/{jobName}", method=RequestMethod.GET)
	public String show(Model model, @PathVariable String jobName) {
		QrtzJobDetailsVO qrtzJobDetail = qrtzJobDetailsRepository.findOne(new QrtzJobDetailsPK(jobName));
		model.addAttribute("qrtzjobdetail", qrtzJobDetail);
		return "qrtzjobdetails.show";
	}
	
	@RequestMapping(value="/qrtzjobdetails/form", method=RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute("qrtzjobdetail", new QrtzJobDetailsVO());
		return "qrtzjobdetails.form";
	}
	
	@RequestMapping(value="/qrtzjobdetails/{jobName}/edit", method=RequestMethod.GET)
	public String edit(@PathVariable String jobName) {
		return "qrtzjobdetails.edit";
	}
	
	@RequestMapping(value="/qrtzjobdetails", method=RequestMethod.POST)
	public String create(@Valid QrtzJobDetailsVO qrtzJobDetailsVO, BindingResult result) {
		if (result.hasErrors()) {
			return "qrtzjobdetails.form";
		}
		
		return "qrtzjobdetails.create";
	}
	
	@RequestMapping(value="/qrtzjobdetails", method=RequestMethod.PUT)
	public String update(@Valid QrtzJobDetailsVO qrtzJobDetailsVO, BindingResult result) {
		if (result.hasErrors()) {
			return "qrtzjobdetails.edit";
		}
		return "qrtzjobdetails.index";
	}
	
	@RequestMapping(value="/qrtzjobdetails/{jobName}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable String jobName) {
		return "qrtzjobdetails.index";
	}
	
}
