package test.spring.generic.crud;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.OrderBy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.tree.AbstractEntity;
dom4j.tree.AbstractEntity;

public class DomainServiceImpl<T extends AbstractEntity> implements
		DomainService<T> {

	private final Log LOG = LogFactory.getLog(getClass());

	private Class<T> persistentClass;

	private GenericRepository<T> rep;

	public void setGenericRepository(GenericRepository<T> rep) {
		this.rep = rep;
	}

	@Override
	public Class<T> getPersistentClass() {
		return persistentClass; 
	}

	@SuppressWarnings("unchecked")
	public DomainServiceImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public ResponseGridViewModel<T> getFilteredEntitiy(
			RequestGridViewModel gridViewModel) {
		ResponseGridViewModel<T> gridResponse = new ResponseGridViewModel<T>();

		Map<String, Object> crits = new HashMap<String, Object>();

		int maxResults = gridViewModel.getRows();
		int firstResult = ((gridViewModel.getPage() - 1) * maxResults);

		// criterias
		if (gridViewModel.getSearch() && !gridViewModel.getCriteria().isEmpty()) {
			crits.putAll(gridViewModel.getCriteria());
		}

		if (!gridViewModel.getCriteria().containsKey("active")) {
			crits.put("active", Boolean.TRUE);
		}

		// ordering
		String orderByField = (StringUtils.isEmpty(gridViewModel.getSort())) ? "id"
				: gridViewModel.getSort();
		OrderBy orderBy = ("desc".equals(gridViewModel.getOrder())) ? OrderBy
				.desc(orderByField) : OrderBy.asc(orderByField);

		List<T> entities = rep.findByCriteria(getPersistentClass(), crits,
				firstResult, maxResults, orderBy);
		gridResponse.setGridData(entities);

		// page counting
		Integer totalRecords = rep.findByCriteria(getPersistentClass(), crits);

		int totalPages = (totalRecords / maxResults)
				+ (((totalRecords % maxResults) == 0) ? 0 : 1);
		gridResponse.setTotalPages(totalPages);
		gridResponse.setTotalRecords(totalRecords);

		int currentPage = gridViewModel.getPage();

		if (currentPage > totalPages) {
			currentPage = 1;
		}

		if (totalPages == 1) {
			currentPage = 1;
		}

		if (totalPages == 0) {
			currentPage = 0;
		}

		gridResponse.setCurrPage(currentPage);

		return gridResponse;
	}

	@Override
	public void removeEntity(Long entityId) {
		LOG.debug("remove entity ID : " + entityId);

		T entity = rep.findById(getPersistentClass(), entityId);

		if (entity != null) {
			rep.remove(entity);
		}
	}

	@Override
	public void insert(T entity) {
		rep.add(entity);
	}

	@Override
	public void update(T entity) {
		rep.update(entity);
	}

}
