package com.pzj.core.imgsrv.servlet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.WebApplicationContextUtils;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		super.init();
		WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

}
