package com.pzj.core.imgsrv.engine.exception;

import com.pzj.core.imgsrv.exception.ImgException;
import com.pzj.core.imgsrv.exception.ImgExceptionCode;

public class ImgResolverException extends ImgException {
	
	private final static int errCode = ImgExceptionCode.IMG_PARAM_RESOLVER_EXCEPTION;
	
	public ImgResolverException(final String message) {
		super(errCode, message);
		
	}
	
	public ImgResolverException(final String message, final Throwable cause) {
		super(errCode, message,cause);
	}

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -906864901338737550L;

}
