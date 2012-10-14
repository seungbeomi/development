package test.spring.generic.core;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateGenericDao<E> implements GenericDao<E> {

	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public HibernateGenericDao() {
		/*
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		Type type = genericSuperclass.getActualTypeArguments()[0];
       if (type instanceof ParameterizedType) {
         this.entityClass = (Class) ((ParameterizedType) type).getRawType();
       } else {
         this.entityClass = (Class) type;
       }
       */
	}

	@Autowired(required=false)
	protected SessionFactory sessionFactory;

	public void add(E entity) {
		getCurrentSession().save(entity);
	}

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria getCriteria() {
        return sessionFactory.getCurrentSession().createCriteria(entityClass);
    }

	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		return getCurrentSession().createCriteria(entityClass)
				.list();
	}

	@SuppressWarnings("unchecked")
	public E getById(Serializable id){
		return (E) getCurrentSession().get(entityClass, id);
	}
	
	public void delete(E entity){
		getCurrentSession().delete(entity);
	}
	
	public void update(E entity){
		getCurrentSession().update(entity);
	}
	
	public void flush(){
		getCurrentSession().flush();
	}
	
	@SuppressWarnings("unchecked")
	public E merge(E entity){
		return (E) getCurrentSession().merge(entity);
	}

	public void save(E entity) {
		System.out.println(">>> save : " + entity.toString());
	}

    public void clear() {
        getCurrentSession().clear();
    }

    public void deleteById(Serializable id) {
		getCurrentSession().delete(getById(id));
	}

}
