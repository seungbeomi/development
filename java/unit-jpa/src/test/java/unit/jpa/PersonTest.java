package unit.jpa;

//import org.hibernate.validator.ClassValidator;
//import org.hibernate.validator.InvalidValue;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

public class PersonTest {
	
	private static Validator validator;
	private Set<ConstraintViolation<Person>> violations;

	@BeforeClass
	public static void beforeClass() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	@Test
	public void jsr303test() {
		Person p = new Person();
		p.setName("ssdfgh");
		validate(p);
	}

	// for hibernate-validator-3.1.0.GA
	private void validate(Person p) {
		this.violations = validator.validate(p);
		
		Iterator<ConstraintViolation<Person>> iter = violations.iterator();
		StringBuilder sb = new StringBuilder();
		while (iter.hasNext()) {
			ConstraintViolation<Person> violation = iter.next();
			sb.append("RootBeanClass : " + violation.getRootBeanClass());
			sb.append("\nRootBean : " + violation.getRootBean());
			sb.append("\nPropertyPath : " + violation.getPropertyPath());
			sb.append("\nMessageTemplate : " + violation.getMessageTemplate());
		}
		System.out.println(sb);
		
		/*
		ClassValidator<Person> validator = new ClassValidator<Person>(Person.class);
		InvalidValue[] messages = validator.getInvalidValues(p);
		for (InvalidValue message : messages) {
			System.out.println("ERROR: " + message);
		}
		*/
	}
}
