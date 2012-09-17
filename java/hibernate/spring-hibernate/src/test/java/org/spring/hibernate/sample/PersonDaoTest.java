package org.spring.hibernate.sample;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonDaoTest {
	
	@Autowired
	private PersonDao personDao;

	@Test
	public void test() {
		assertNotNull(personDao);
		
		List<Person> findAll1 = personDao.findAll();
		assertThat(findAll1.size(), is(0));
		
		// insert
		Person person = new Person("seungbeomi");
		personDao.store(person);
		
		List<Person> findAll2 = personDao.findAll();
		assertThat(findAll2.size(), is(1));
		
		// select
		Person p = personDao.findById(1);
		assertThat(p.getName(), is("seungbeomi"));
		
		// update
		p.setName("modify");
		personDao.store(p);
		
		Person p2 = personDao.findById(1);
		assertThat(p2.getName(), is("modify"));
		
		// delete
		personDao.delete(p.getId());
		
		List<Person> findAll3 = personDao.findAll();
		assertThat(findAll3.size(), is(0));
		
	}

}
