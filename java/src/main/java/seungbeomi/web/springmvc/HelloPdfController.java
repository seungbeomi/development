package seungbeomi.web.springmvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloPdfController implements Controller {

	@Autowired
	HelloPdfView helloPdfView;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String message = request.getParameter("message");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", message);

		return new ModelAndView(this.helloPdfView, model);
	}

}
