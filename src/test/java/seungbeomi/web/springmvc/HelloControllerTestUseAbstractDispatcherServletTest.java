package seungbeomi.web.springmvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.barista.common.test.AbstractDispatcherServletTest;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class HelloControllerTestUseAbstractDispatcherServletTest extends AbstractDispatcherServletTest {

	@Test
	public void helloController() throws ServletException, IOException {
		ModelAndView mav = setRelativeLocations("HelloControllerTest-context.xml")
				.setClasses(HelloSpring.class)
				.initRequest("/hello", RequestMethod.GET)
				.addParameter("name", "Spring")
				.runService()
				.getModelAndView();

		assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
		assertThat((String) mav.getModel().get("message"), is("Hello Spring"));
	}
}
