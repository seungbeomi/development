package seungbeomi.orm.jpa.dao;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import seungbeomi.orm.jpa.domain.Person;

public class PersonDaoImpl extends JpaDaoSupport implements PersonDao {

	private static final String SQL_LIST = "select p from Person p";

	@SuppressWarnings("unchecked")
	public List<Person> list() {
		return getJpaTemplate().find(SQL_LIST);
	}

	@Override
	public Person get(int id) {
		return (Person) getJpaTemplate().find(Person.class, id);
	}

	@Override
	public void save(Person p) {
		Person input = this.get(p.getId());
		// insert
		if (input == null) {
			getJpaTemplate().persist(p);
		// update
		} else {
			BeanUtils.copyProperties(p, input);
			getJpaTemplate().persist(input);
		}
	}

	@Override
	public void delete(int id) {
		Person input = this.get(id);
		if (input != null) {
			getJpaTemplate().remove(input);
		}
	}

}
