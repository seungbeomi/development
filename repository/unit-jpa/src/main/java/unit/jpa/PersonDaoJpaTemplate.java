package unit.jpa;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;

public class PersonDaoJpaTemplate extends JpaDaoSupport implements PersonDao {

	public Person find(int id) {
		return getJpaTemplate().find(Person.class, id);
	}

	public List<Person> findAll() {
		return getJpaTemplate().find("select p from Person p");
	}

	public void save(Person p) {
		getJpaTemplate().persist(p);
	}

}
