package unit.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager em;

	public Person find(int id) {
		return em.find(Person.class, id);
	}

	public List<Person> findAll() {
		return em.createQuery("select p from Person p", Person.class).getResultList();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Person p) {
		em.persist(p);
	}

}
