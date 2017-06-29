package com.pzj.core.imgsrv.exception;

public class ImgParamException extends ImgException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7834699535601691785L;
	private static int errCode = 17001;

	public ImgParamException(String message) {
		super(errCode, message);
	}
	
	public ImgParamException(final String message, final Throwable cause) {
		super(errCode,message, cause);
	}

}
