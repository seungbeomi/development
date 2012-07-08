package unit.jpa;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PersonDaoImplTest {

	@Autowired
	private PersonDao personDao;

	@Test
	public void personcrud() {
		assertNotNull(personDao);

		Person p = new Person();
		p.setName("jpa");

		personDao.save(p);

		List<Person> lp = personDao.findAll();
		assertThat(lp.size(), is(1));

		Person found = personDao.find(1);
		assertThat(found.getName(), is("jpa"));

	}
}
