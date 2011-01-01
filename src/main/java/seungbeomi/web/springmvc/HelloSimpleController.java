package seungbeomi.web.springmvc;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import seungbeomi.web.springmvc.annotation.RequiredParams;
import seungbeomi.web.springmvc.annotation.ViewName;

@Controller
public class HelloSimpleController implements SimpleController {

	@RequestMapping("/hello")
	@ViewName("/WEB-INF/view/hello.jsp")
	@RequiredParams({"name"})
	public void control(Map<String, String> params, Map<String, Object> model) {
		model.put("message", "Hello " + params.get("name") + " by SimpleController!!");
	}

}
