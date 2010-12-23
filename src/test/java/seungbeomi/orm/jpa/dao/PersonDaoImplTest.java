package seungbeomi.orm.jpa.dao;

import java.util.List;

import org.barista.common.test.AbstractDaoUnitTest;
import org.springframework.beans.factory.annotation.Autowired;

import seungbeomi.orm.jpa.domain.Person;

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

	public void testList() {
		assertNotNull(targetDao);

		List<Person> lPerson = targetDao.list();

		for (Person p : lPerson) {
			System.out.println(p);
		}
	}
}
