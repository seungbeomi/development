package test.spring.generic.dao;

import org.springframework.stereotype.Repository;

import test.spring.generic.core.HibernateGenericDao;
import test.spring.generic.domain.School;

@Repository
public class SchoolDaoImpl extends HibernateGenericDao<School> implements SchoolDao {

}
