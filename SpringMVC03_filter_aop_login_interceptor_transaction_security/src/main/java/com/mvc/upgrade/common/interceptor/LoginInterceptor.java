package com.mvc.upgrade.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	/*
	디스패쳐서블릿에서 컨트롤러로 넘어갈때 
	핸들러매핑되는 순간 신호를 인터셉터해서 뺏어올거야
	세션에 저장해둔값이없다면 컨트롤러를 다른데로 갈 수 있게 하는 것
	
	
	요청->디스패쳐 서블릿-> 핸들러매핑을 통해서 컨트롤러로 보내줄때 인터셉터한다
	preHandle로 컨트롤러로 가게 하거나 못가게 하거나 한다.
	true면 해당 화면으로 연결해주고
	false면 응답되는 화면은 빈화면(응답하는 객체에 어떤 뷰도 없는 것)
	
	
	
	*/
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("[Interceptor] : preHandle");
		
		
		//spring 3.2 이상부터는 servlet-context.xml에서 <exclude-mapping-path>를 사용하여 설정 가능
		/*||로 구분해줘도된다*/
		//순서도 중요함 ! 
		//로그인폼으로 넘어가는애랑 로그인하는애는 true
		if (
				request.getRequestURI().contains("/loginform.do")||
				request.getRequestURI().contains("/ajaxlogin.do")||
				request.getRequestURI().contains("/test.do")||
				request.getRequestURI().contains("/registform.do")||
				request.getRequestURI().contains("/registres.do")
			) {
			return true;
		}
		
				
		//로그인되어 있다면 넘어가라
		if(request.getSession().getAttribute("login") != null) {
			return true;
		}
		else if(request.getSession().getAttribute("login") == null) {
			response.sendRedirect("loginform.do");
			return false;
		}

		return false;
	}

	
	//아래 역할알아보기 숙제
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(modelAndView != null) {
			logger.info("[Interceptor] : "+modelAndView.getViewName());
		}
		
		
		
		logger.info("[Interceptor] : postHandle");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("[Interceptor] : afterCompletion");
	}

}
