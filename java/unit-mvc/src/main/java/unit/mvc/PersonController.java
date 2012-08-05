package unit.mvc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/person")
public class PersonController {

	private Map<Long, Person> people = new ConcurrentHashMap<Long, Person>();
	
	@RequestMapping(method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute(new Person());
		return "person/form";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String register(@Valid Person person, BindingResult result) {
		if (result.hasErrors()) {
			return "person/form";
		}
		this.people.put(person.assignId(), person);
		return "redirect:/person/" + person.getId();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String getView(@PathVariable Long id, Model model) {
		Person person = this.people.get(id);
		if (person == null) {
			throw new ResourceNotFoundException(id);
		}
		model.addAttribute(person);
		return "person/view";
	}
}
