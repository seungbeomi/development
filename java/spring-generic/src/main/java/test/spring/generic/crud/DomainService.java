package test.spring.generic.crud;

import org.dom4j.tree.AbstractEntity;

public interface DomainService<T extends AbstractEntity> {

	ResponseGridViewModel<T> getFilteredEntitiy(RequestGridViewModel gridViewModel);
	void removeEntity(Long entityId);
	void insert(T entity);
	void update(T entity);
	Class<T> getPersistentClass();

}
