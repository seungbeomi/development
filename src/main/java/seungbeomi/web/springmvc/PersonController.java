package seungbeomi.web.springmvc;

import org.barista.common.web.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;

import seungbeomi.orm.jpa.dao.PersonDao;
import seungbeomi.orm.jpa.domain.Person;

@RequestMapping("/person")
public class PersonController extends GenericController<Person, Integer, PersonDao>{


}
