package com.pzj.core.imgsrv.utils;

import javax.servlet.http.HttpServletRequest;

public enum UriUtil {
	INTANCE;

	public String getFileName(HttpServletRequest request, String servletName) {
		String uri = request.getRequestURI();
		if (servletName.indexOf(".") > -1) {
			servletName = servletName.substring(servletName.lastIndexOf(".") + 1);
		}
		return uri.substring(uri.indexOf(servletName) + servletName.length() + 1, uri.length());

	}

}
