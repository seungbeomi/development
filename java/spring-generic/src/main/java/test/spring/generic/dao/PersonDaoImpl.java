package test.spring.generic.dao;

import org.springframework.stereotype.Repository;

import test.spring.generic.core.HibernateGenericDao;
import test.spring.generic.domain.Person;

@Repository
public class PersonDaoImpl extends HibernateGenericDao<Person> implements PersonDao {

}
