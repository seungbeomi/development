package seungbeomi.web.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloAnnotationController {

	@RequestMapping("/hello-annotation")
	public String hello(@RequestParam("name") String name, ModelMap map) {
		map.put("message", "Hello " + name);
		return "/WEB-INF/view/hello.jsp";
	}

}
