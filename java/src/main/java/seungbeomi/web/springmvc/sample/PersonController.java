package seungbeomi.web.springmvc.sample;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("ttt")
public class PersonController {

	private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonDao personDao;

	@RequestMapping(value = "/crud", method = RequestMethod.POST)
	public String crud(@RequestParam Map<String, String> params,
			@ModelAttribute Person person,
			Model model) throws IOException,
			IllegalAccessException, InvocationTargetException {

		String mode = params.get("mode");
		Person p = new Person();
		BeanUtils.copyProperties(p, params);

		if ("add".equals(mode)) {
			personDao.save(p);
		}

		//response.getWriter().println("OK: id=" + p.getId() + "が保存されました。");
		model.addAttribute("status", "OK: id=" + p.getId() + "が保存されました。");

		model.addAttribute("ttt", p);
		return "personCrud";
	}

	@RequestMapping(value = "/personCrud", method = RequestMethod.GET)
	public String init(Model model) {

		Person p = new Person();
		p.setId(123);
		p.setName("seungbeomi");
		model.addAttribute("ttt", p);

		return "personCrud";
	}

}
