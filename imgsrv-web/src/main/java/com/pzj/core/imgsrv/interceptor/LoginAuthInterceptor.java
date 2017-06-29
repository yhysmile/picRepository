package com.pzj.core.imgsrv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginAuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//		String requestUri = request.getRequestURI();
		//		if (requestUri.indexOf(".jsp") < 0) {
		//			return true;
		//		}

		//		String userName = (String) request.getSession().getAttribute(LoginConstant.USER_SESSION_KEY);
		//		if (Check.NuNString(userName)) {
		//			response.sendRedirect(request.getContextPath() + "/index.jsp");
		//			return false;
		//		}

		return true;
	}
}
