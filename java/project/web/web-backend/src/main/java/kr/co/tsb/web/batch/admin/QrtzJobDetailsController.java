package kr.co.tsb.web.batch.admin;

import javax.validation.Valid;

import kr.co.tsb.comp.batch.admin.dao.QrtzJobDetailsRepository;
import kr.co.tsb.comp.batch.admin.domain.QrtzJobDetailsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/qrtzjobdetails")
public class QrtzJobDetailsController {
	
	@Autowired
	private QrtzJobDetailsRepository qrtzJobDetailsRepository;
	
	@Autowired
	private QrtzJobDetailsValidator qrtzJobDetailsValidator;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(@RequestParam(defaultValue = "20") int pageSize) {
		return "qrtzjobdetails/index";
	}
	
	@RequestMapping(value="/{jobName}", method=RequestMethod.GET)
	public String show(@PathVariable String jobName, @RequestParam(defaultValue = "20") int pageSize, 
			BindingResult result, Model model) {
		return "qrtzjobdetails/show";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String form() {
		return "qrtzjobdetails/form";
	}
	
	@RequestMapping(value="/{jobName}/edit", method=RequestMethod.GET)
	public String edit(@PathVariable String jobName) {
		return "qrtzjobdetails/edit";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String create(@Valid QrtzJobDetailsVO qrtzJobDetailsVO, BindingResult result) {
		if (result.hasErrors()) {
			return "qrtzjobdetails/form";
		}
		
		return "qrtzjobdetails/create";
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public String update(@Valid QrtzJobDetailsVO qrtzJobDetailsVO, BindingResult result) {
		if (result.hasErrors()) {
			return "qrtzjobdetails/edit";
		}
		return "qrtzjobdetails/index";
	}
	
	@RequestMapping(value="/{jobName}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable String jobName) {
		return "qrtzjobdetails/index";
	}
	
}
