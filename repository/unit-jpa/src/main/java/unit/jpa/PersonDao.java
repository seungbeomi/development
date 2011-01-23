package unit.jpa;

import java.util.List;

public interface PersonDao {

	Person find(int id);

	List<Person> findAll();

	void save(Person p);
}
