package seungbeomi.web.springmvc;

import java.util.Date;
import java.util.List;

import org.barista.common.test.AbstractDaoUnitTest;
import org.springframework.beans.factory.annotation.Autowired;

import seungbeomi.web.springmvc.sample.Person;
import seungbeomi.web.springmvc.sample.PersonDao;



public class PersonDaoImplTest extends AbstractDaoUnitTest {

	private static final String SCHEMA_NAME = "PUBLIC";

	@Autowired
	private PersonDao targetDao;

	@Override
	protected String[] doGetConfigLocations() {
		return new String[] {
				"classpath:DaoTestCase.xml",
				"/seungbeomi/web/springmvc/PersonDaoImplTest-context.xml"
		};
	}

	@Override
	protected void setUpData() throws Exception {
		setSchemaName(SCHEMA_NAME);
		super.setUpData();
	}

	public void testAdd() throws Throwable {
		System.out.println("# testAdd");

		Person p = new Person();
		p.setId(123);
		p.setName("spring");
		p.setAddress("tokyo");

		targetDao.save(p);

		Person reload = targetDao.view(p.getId());
		System.out.println("<<< " + reload);
	}

	/*
	public void testList() throws Throwable {
		System.out.println("# testList()");
		List<Person> lPerson = targetDao.list();

		for (Person p : lPerson) {
			System.out.println(p);
		}
	}

	public void testGet() throws Throwable {
		System.out.println("# testGet");
		int id = 1;
		Person result = targetDao.view(id);
		System.out.println("get : " + result);
	}

	public void testUpdate() throws Throwable {
		System.out.println("# testUpdate");


	}

	public void testDelete() throws Throwable {
		System.out.println("# testDelete");
		List<Person> before = targetDao.list();
		System.out.println("before : " + before.size());

		targetDao.delete(1);

		List<Person> after = targetDao.list();
		System.out.println("after : " + after.size());
	}
	*/

}
