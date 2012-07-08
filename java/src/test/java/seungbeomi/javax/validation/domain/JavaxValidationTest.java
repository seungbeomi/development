package seungbeomi.javax.validation.domain;

import org.barista.common.test.AbstractValidationTest;
import org.junit.Test;

public class JavaxValidationTest extends AbstractValidationTest<Person>{

	@Test
	public void testValidation() {
		assertValid(new Person(1, "seungbeomi"));
	}

}
