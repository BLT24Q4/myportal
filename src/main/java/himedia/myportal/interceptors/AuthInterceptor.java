package himedia.myportal.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import himedia.myportal.services.UserService;
import himedia.myportal.services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//	컨트롤러에서 요청을 가로채서
//	필요할 경우 로그인 페이지로 전송
public class AuthInterceptor implements HandlerInterceptor {
	private static final Logger logger = 
		LoggerFactory.getLogger(AuthInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("AuthInterceptor");
		//	ApplicationContext 얻어오기
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());
		//	UserServiceBean 받아오기
		UserService userService = context.getBean(UserServiceImpl.class);
		
		if (userService.isAuthenticated(request)) {
			//	인증된 사용자면 통과
			logger.debug("인증된 사용자");
			return true;
		} else {
			//	인증된 사용자가 아니면 로그인 페이지로 리다이렉트
			logger.debug("인증되지 않은 사용자 -> 로그인 페이지로 리다이렉트");
			response.sendRedirect(
					request.getContextPath() + "/users/login");
			return false;	//	요청을 뒤로 보내는 것을 중단함
		}
		
	}

}
