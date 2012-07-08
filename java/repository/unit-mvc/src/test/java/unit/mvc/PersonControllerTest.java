package unit.mvc;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.barista.common.test.AbstractDispatcherServletTest;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class PersonControllerTest extends AbstractDispatcherServletTest {

	@Test
	public void testPersonController() throws ServletException, IOException {
		ModelAndView mav = setServletConfig("spring")
			.setClasses(PersonController.class)
			.initRequest("/person", RequestMethod.GET)
			.runService()
			.getModelAndView();
		
		assertThat(mav.getViewName(), is("person/form"));
		
		mav.clear();
		mav = setServletConfig("spring")
			.setClasses(PersonController.class)
			.initRequest("/person", RequestMethod.POST)
			.addParameter("name", "spring")
			.runService()
			.getModelAndView();
		
		assertThat(mav.getViewName(), is("redirect:/person/1"));
		
		mav.clear();
		mav = setServletConfig("spring")
			.setClasses(PersonController.class)
			.initRequest("/person/1", RequestMethod.GET)
			.runService()
			.getModelAndView();
		
		Person person = (Person) mav.getModel().get("person");
		assertThat(person.getName(), is("spring"));
		assertThat(mav.getViewName(), is("person/view"));
		
	}
}
