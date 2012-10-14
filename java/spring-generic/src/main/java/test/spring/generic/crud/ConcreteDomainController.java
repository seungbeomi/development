package test.spring.generic.crud;

import javax.persistence.Entity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "concreteDomainController")
@RequestMapping("/entity")
public class ConcreteDomainController extends DomainController<Entity> {

	private String view = "entityIndex";

	private ConcreteDomainService domainService;

	public void setConcreteDomainService(ConcreteDomainService domainService) {
		this.domainService = domainService;
	}

	@Override
	public DomainService<Entity> getDomainService() {
		return domainService;
	}

	@Override
	protected String getMainView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

}
