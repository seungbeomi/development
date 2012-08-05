package seungbeomi.orm.jpa.dao;

import java.util.Date;
import java.util.List;

import org.barista.common.test.AbstractDaoUnitTest;
import org.springframework.beans.factory.annotation.Autowired;

import seungbeomi.orm.jpa.domain.Person;
import seungbeomi.orm.jpa.domain.PersonDetail;

public class PersonDaoImplTest extends AbstractDaoUnitTest {

	private static final String SCHEMA_NAME = "PUBLIC";

	@Autowired
	private PersonDao targetDao;

	@Override
	protected String[] doGetConfigLocations() {
		return new String[] {
				"classpath:DaoTestCase.xml",
				"classpath:test-context-person.xml"
		};
	}

	@Override
	protected void setUpData() throws Exception {
		setSchemaName(SCHEMA_NAME);
		super.setUpData();
	}

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

		int id = 9999;
		String name = "insert";

		Person p = new Person();
		p.setId(id);
		p.setName(name);
		PersonDetail pd = new PersonDetail();
		pd.setId(p.getId());
		pd.setBirthday(new Date());
		pd.setAddress("ADDRESS" + id);
		p.setDetail(pd);

		// create
		targetDao.save(p);

		Person reload = targetDao.view(id);
		System.out.println("create : " + reload);

		// update
		p.setName("update");
		pd.setAddress("updateAddress" + id);
		//targetDao.save(p); // auto update

		reload = targetDao.view(id);
		System.out.println("update : " + reload);

	}

	public void testDelete() throws Throwable {
		System.out.println("# testDelete");
		List<Person> before = targetDao.list();
		System.out.println("before : " + before.size());

		targetDao.delete(1);

		List<Person> after = targetDao.list();
		System.out.println("after : " + after.size());
	}

}
