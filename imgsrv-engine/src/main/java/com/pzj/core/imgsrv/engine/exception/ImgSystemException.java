package com.pzj.core.imgsrv.engine.exception;

import com.pzj.core.imgsrv.exception.ImgException;
import com.pzj.core.imgsrv.exception.ImgExceptionCode;

public class ImgSystemException extends ImgException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1898289414255279625L;
	/**
	 * 
	 */

	
	private final static int errCode = ImgExceptionCode.IMG_SYSTEM_EXCEPTION;

	public ImgSystemException(final String message) {
		super(errCode, message);
		
	}
	
	public ImgSystemException(final String message, final Throwable cause) {
		super(errCode, message,cause);
	}
}
