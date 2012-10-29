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

	@RequestMapping(value="/qrtzJobDetails", method=RequestMethod.GET)
	public String index(Model model) {
		List<QrtzJobDetailsVO> qrtzJobDetails = qrtzJobDetailsRepository.findAll();
		model.addAttribute("qrtzJobDetails", qrtzJobDetails);
		return "qrtzJobDetails.index";
	}
	
	@RequestMapping(value="/qrtJobdetails/{jobName}", method=RequestMethod.GET)
	public String show(Model model, @PathVariable String jobName) {
		QrtzJobDetailsVO qrtzJobDetail = qrtzJobDetailsRepository.findOne(new QrtzJobDetailsPK(jobName));
		model.addAttribute("qrtzjobdetail", qrtzJobDetail);
		return "qrtzJobDetails.show";
	}
	
	@RequestMapping(value="/qrtzJobDetails/form", method=RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute("qrtzjobdetail", new QrtzJobDetailsVO());
		return "qrtzJobDetails.form";
	}
	
	@RequestMapping(value="/qrtzJobDetails/{jobName}/edit", method=RequestMethod.GET)
	public String edit(@PathVariable String jobName) {
		return "qrtzJobDetails.edit";
	}
	
	@RequestMapping(value="/qrtzJobDetails", method=RequestMethod.POST)
	public String create(@Valid QrtzJobDetailsVO qrtzJobDetailsVO, BindingResult result) {
		if (result.hasErrors()) {
			return "qrtzJobDetails.form";
		}
		
		return "qrtzJobDetails.create";
	}
	
	@RequestMapping(value="/qrtzJobDetails", method=RequestMethod.PUT)
	public String update(@Valid QrtzJobDetailsVO qrtzJobDetailsVO, BindingResult result) {
		if (result.hasErrors()) {
			return "qrtzJobDetails.edit";
		}
		return "qrtzJobDetails.index";
	}
	
	@RequestMapping(value="/qrtzJobDetails/{jobName}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable String jobName) {
		return "qrtzJobDetails.index";
	}
	
}
