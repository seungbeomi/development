package seungbeomi.web.springmvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import seungbeomi.web.springmvc.annotation.RequiredParams;
import seungbeomi.web.springmvc.annotation.ViewName;

public class SimpleHandlerAdapter implements HandlerAdapter {

	public boolean supports(Object handler) {
		return (handler instanceof SimpleController);
	}

	public long getLastModified(HttpServletRequest request, Object handler) {
		return -1;
	}

	public ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Method method = ReflectionUtils.findMethod(handler.getClass(), "control", Map.class, Map.class);
		ViewName viewName = AnnotationUtils.getAnnotation(method, ViewName.class);
		RequiredParams requiredParams = AnnotationUtils.getAnnotation(method, RequiredParams.class);

		Map<String, String> params = new HashMap<String, String>();
		for (String param : requiredParams.value()) {
			String value = request.getParameter(param);
			if (value == null) {
				throw new IllegalStateException();
			}
			params.put(param, value);
		}

		Map<String, Object> model = new HashMap<String, Object>();
		((SimpleController) handler).control(params, model);

		return new ModelAndView(viewName.value(), model);
	}

}
