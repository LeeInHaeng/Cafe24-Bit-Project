package com.cafe24.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.annotation.AuthUser;

public class AuthUserInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// 1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Method의 어노테이션 받아오기
		AuthUser auth = handlerMethod.getMethodAnnotation(AuthUser.class);
		
		// 4. Method에 어노테이션이 없으면
		// Class(Type)에 어노테이션을 받아오기
		if(auth==null) {
			auth = handlerMethod.getMethod().getAnnotation(AuthUser.class);
		}
		
		// 5. 어노테이션이 안 붙어있는 경우 (Method에도 없고 클래스에도 없음)
		if(auth==null) {
			return true;
		}
		
		// 6. 클래스나 메서드에 어노테이션이 붙어 있기 때문에 인증 여부를 확인
		HttpSession session = request.getSession();
		
		if(session == null || session.getAttribute("authUser")==null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return false;
		}else {
			return true;
		}
	}
}
