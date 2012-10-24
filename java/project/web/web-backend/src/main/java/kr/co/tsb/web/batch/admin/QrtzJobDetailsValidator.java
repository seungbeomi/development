package kr.co.tsb.web.batch.admin;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class QrtzJobDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		
	}
	
	
}
