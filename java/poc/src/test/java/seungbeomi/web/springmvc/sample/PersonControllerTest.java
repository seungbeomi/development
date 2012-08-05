package seungbeomi.web.springmvc.sample;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.barista.common.test.AbstractDispatcherServletTest;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

public class PersonControllerTest extends AbstractDispatcherServletTest {

	@Test
	public void sessionAttr() throws ServletException, IOException {
		setClasses(PersonTestController.class);

		// request GET - form()
		initRequest("/init");
		runService();

		HttpSession session = request.getSession();
		assertThat(session.getAttribute("person"), is(getModelAndView().getModel().get("person")));

		// request POST - submit()
		initRequest("/submit", RequestMethod.POST)
				.addParameter("id", "999")
				.addParameter("name", "spring");
		request.setSession(session);
		runService();

		System.out.println("<<< name : " + ((Person)session.getAttribute("person")).getName());

		assertThat(
				((Person) getModelAndView().getModel().get("person")).getName(),
				is("spring"));
		// assertThat(session.getAttribute("person"), is(nullValue()));
	}

	@Controller
	@SessionAttributes("person")
	static class PersonTestController {
		@RequestMapping(value = "/init", method = RequestMethod.GET)
		@ResponseStatus(value = HttpStatus.CREATED)
		public Person init(Model model) {
			Person p = new Person();
			p.setAddress("address");
			return p;
		}

		@RequestMapping(value = "/submit", method = RequestMethod.POST)
		@ResponseStatus(value = HttpStatus.CONTINUE)
		public void submit(@RequestParam Map<String, String> params,
				@ModelAttribute Person person, Model model,
				SessionStatus sessionStatus) {
			model.addAttribute("person", person);
			System.out.println("<<< crud ----------------------");
			//sessionStatus.setComplete();
		}
	}

	private Matcher nullValue() {

		return null;
	}

}
