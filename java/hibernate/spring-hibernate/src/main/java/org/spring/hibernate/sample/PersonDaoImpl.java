package org.spring.hibernate.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	@Transactional
	public void store(Person person) {
		hibernateTemplate.saveOrUpdate(person);
	}

	@Override
	@Transactional
	public void delete(int id) {
		Person person = hibernateTemplate.get(Person.class, id);
		hibernateTemplate.delete(person);
	}

	@Override
	@Transactional(readOnly = true)
	public Person findById(int id) {
		return hibernateTemplate.get(Person.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Person> findAll() {
		return hibernateTemplate.find("from Person");
	}
	
	
	
}
