package seungbeomi.web.springmvc;

import java.io.IOException;

import javax.servlet.ServletException;

import org.barista.common.test.AbstractDispatcherServletTest;
import org.junit.Test;

public class SimpleHandlerAdapterTest extends AbstractDispatcherServletTest {

	@Test
	public void simpleHandlerAdapter() throws ServletException, IOException {

		setClasses(SimpleHandlerAdapter.class, HelloSimpleController.class);
		initRequest("/hello").addParameter("name", "Spring").runService();

		assertViewName("/WEB-INF/view/hello.jsp");
		assertModel("message", "Hello Spring by SimpleController!!");
	}

}
