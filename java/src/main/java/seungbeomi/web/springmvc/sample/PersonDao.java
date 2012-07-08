package seungbeomi.web.springmvc.sample;

import java.util.List;

public interface PersonDao {

	List<Person> list();

	Person view(int id);

	void save(Person p);

	void delete(int id);

}
