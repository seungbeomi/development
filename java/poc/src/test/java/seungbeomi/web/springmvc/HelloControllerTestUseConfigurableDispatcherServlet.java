package seungbeomi.web.springmvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.barista.common.test.ConfigurableDispatcherServlet;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.servlet.ModelAndView;

public class HelloControllerTestUseConfigurableDispatcherServlet {

	@Test
	public void testSayHello() throws Exception {

		// Method 1
		ConfigurableDispatcherServlet servlet = new ConfigurableDispatcherServlet();
		servlet.setRelativeLocations(getClass(), "HelloControllerTest-context.xml");

		servlet.setClasses(HelloSpring.class);
		servlet.init(new MockServletConfig("spring"));

		//-------

		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();

		req.setMethod("GET");
		req.setRequestURI("/hello");
		req.addParameter("name", "Spring");

		servlet.service(req, res);
		//-------

		ModelAndView mav = servlet.getModelAndView();
		assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
		assertThat((String)mav.getModel().get("message"), is("Hello Spring"));

		// Method 2
		/*
		ApplicationContext ac = new GenericXmlApplicationContext("/seungbeomi/web/springmvc/HelloControllerTest-context.xml");
		HelloController controller = ac.getBean(HelloController.class);

		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();

		req.setMethod("GET");
		req.setRequestURI("/hello");
		req.addParameter("name", "Spring");

		ModelAndView mav = controller.handleRequest(req, res);
		assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
		assertThat((String)mav.getModel().get("message"), is("Hello Spring"));
		*/
	}

}
