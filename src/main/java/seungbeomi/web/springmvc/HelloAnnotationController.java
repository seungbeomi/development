package seungbeomi.web.springmvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import seungbeomi.web.springmvc.domain.Person;

@Controller
public class HelloAnnotationController {

	@Value("#{systemProperties['os.name']}")
	String osName;

	/*
	@RequestMapping("/hello-annotation")
	public String hello(@RequestParam("name") String name, ModelMap map) {
		map.put("message", "Hello " + name + " by HelloAnnotationController.");
		return "/WEB-INF/view/hello.jsp";
	}
	*/

	@RequestMapping("/hello-mvc")
	//public String hello(@ModelAttribute Person person, BindingResult bindingResult, ModelMap map) {
	public String hello(@ModelAttribute Person person, BindingResult bindingResult, Model model) {
		//map.put("message", "Hello @MVC : " + person.toString() + ", OS : " + osName);
		model.addAttribute("message", "Hello @MVC : " + person.toString() + ", OS : " + osName);
		return "/WEB-INF/view/hello.jsp";
	}

}
