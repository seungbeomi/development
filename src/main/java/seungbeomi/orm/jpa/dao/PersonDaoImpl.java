package seungbeomi.orm.jpa.dao;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import seungbeomi.orm.jpa.domain.Person;

public class PersonDaoImpl extends JpaDaoSupport implements PersonDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> list() {
		return getJpaTemplate().find("select p from Person p");
	}

}
