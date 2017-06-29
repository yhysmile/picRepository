package com.pzj.core.imgsrv.engine.exception;

import com.pzj.core.imgsrv.exception.ImgException;
import com.pzj.core.imgsrv.exception.ImgExceptionCode;

public class ImgMD5Exception extends ImgException{
	
	private final static int errCode = ImgExceptionCode.IMG_MD5_EXCEPTION;

	public ImgMD5Exception(final String message) {
		super(errCode, message);
		
	}
	
	public ImgMD5Exception(final String message, final Throwable cause) {
		super(errCode, message,cause);
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 7428084978401080361L;

}
