package test.spring.generic.core;

import org.springframework.beans.factory.annotation.Autowired;

public class GenericServiceImpl<E> implements GenericService<E> {

	@Autowired GenericDao<E> genericDao;
	
	@Override
	public void save(E entity) {
		System.out.println(">>> Service save : " + entity);
		genericDao.save(entity);
	}

}
