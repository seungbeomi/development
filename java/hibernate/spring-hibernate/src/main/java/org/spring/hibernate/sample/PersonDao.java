package org.spring.hibernate.sample;

import java.util.List;

public interface PersonDao {

	void store(Person person);
	void delete(int id);
	Person findById(int id);
	List<Person> findAll();
	
}
