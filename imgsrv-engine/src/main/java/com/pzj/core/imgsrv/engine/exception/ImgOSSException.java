package com.pzj.core.imgsrv.engine.exception;

import com.pzj.core.imgsrv.exception.ImgException;
import com.pzj.core.imgsrv.exception.ImgExceptionCode;

public class ImgOSSException extends ImgException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7945793920875467519L;
	
	private final static int errCode = ImgExceptionCode.IMG_OSS_EXCEPTION;

	public ImgOSSException(final String message) {
		super(errCode, message);
		
	}
	
	public ImgOSSException(final String message, final Throwable cause) {
		super(errCode, message,cause);
	}
}
