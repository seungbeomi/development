package seungbeomi.web.springmvc.sample;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;

public class PersonDaoImpl extends JpaDaoSupport implements PersonDao {

	@Override
	public List<Person> list() {
		return getJpaTemplate().find("select p from Person p");
	}

	@Override
	public Person view(int id) {
		return (Person) getJpaTemplate().find(Person.class, id);
	}
	@Override
	public void save(Person p) {
		getJpaTemplate().persist(p);
	}

	@Override
	public void delete(int id) {
		Person p = new Person();
		p.setId(id);
		getJpaTemplate().remove(p);
	}

}
