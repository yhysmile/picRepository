package com.pzj.core.imgsrv.engine.exception;

import com.pzj.core.imgsrv.exception.ImgException;
import com.pzj.core.imgsrv.exception.ImgExceptionCode;

public class ImgVisitException extends ImgException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5281961723254814010L;
	private final static int errCode = ImgExceptionCode.IMG_VISIT_EXCEPTION;

	public ImgVisitException(String message, Throwable cause) {
		super(errCode, message, cause);
	}
	
	public ImgVisitException(String message) {
		super(errCode, message);
	}
}
