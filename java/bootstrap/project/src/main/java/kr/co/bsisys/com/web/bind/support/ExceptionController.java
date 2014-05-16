package kr.co.bsisys.com.web.bind.support;

import kr.co.bsisys.fw.exception.ServiceException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionController {

	@RequestMapping("/exception")
	public @ResponseBody String exception() {
		throw new IllegalStateException("Sorry!");
	}

	@RequestMapping("/global-exception")
	public @ResponseBody String serviceException() throws ServiceException {
		throw new ServiceException();
	}

	@ExceptionHandler
	public @ResponseBody String handle(IllegalStateException e) {
		return "IllegalStateException handled!";
	}

}
