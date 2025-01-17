package himedia.myportal.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
	private static Logger logger = 
			LoggerFactory.getLogger(MyInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		logger.debug("MyInterceptor.preHandle calls");
		//	preHandle의 리턴값이 false면
		//	-> 이후의 인터셉터와 컨트롤러는 실행되지 않음
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("MyInterceptor.postHandle calls");
		//	호출된 handler 정보 확인
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = 
					(HandlerMethod)handler;
			
			//	호출된 컨트롤러 메서드의 정보 확인
			String controllerName = 
					handlerMethod.getBeanType().getName();
			//	호출된 메서드 이름 확인
			String methodName = 
					handlerMethod.getMethod().getName();
			
			logger.debug("Controller: " + controllerName + 
					". Method: " + methodName);	
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler, 
			Exception ex)
			throws Exception {
		logger.debug("MyInterceptor.afterCompletion calls");
	}

}
