package seungbeomi.orm.jpa.dao;

import java.util.List;

import seungbeomi.orm.jpa.domain.Person;

public interface PersonDao {

	List<Person> list();

	Person get(int id);

	void save(Person p);

	void delete(int id);

}
