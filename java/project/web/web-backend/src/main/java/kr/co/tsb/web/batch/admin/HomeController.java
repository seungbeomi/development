package kr.co.tsb.web.batch.admin;

import java.util.List;
import java.util.Locale;

import kr.co.tsb.comp.batch.admin.dao.QrtzTriggersRepository;
import kr.co.tsb.comp.batch.admin.domain.QrtzTriggersVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private QrtzTriggersRepository qrtzTriggersRepository;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		List<QrtzTriggersVO> batch = indexQrtzTriggers();
		model.addAttribute("batch", batch);
		
		return "base";
	}
	
	private List<QrtzTriggersVO> indexQrtzTriggers() {
		List<QrtzTriggersVO> result = qrtzTriggersRepository.findAll();
		return result;
	}
	
}
