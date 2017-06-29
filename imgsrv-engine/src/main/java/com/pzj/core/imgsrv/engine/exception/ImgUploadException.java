package com.pzj.core.imgsrv.engine.exception;

import com.pzj.core.imgsrv.exception.ImgException;
import com.pzj.core.imgsrv.exception.ImgExceptionCode;

public class ImgUploadException extends ImgException{

	private static final long serialVersionUID = 9013630919750866659L;
	
	private final static int errCode = ImgExceptionCode.IMG_UPLOAD_EXCEPTION;

	public ImgUploadException(String message, Throwable cause) {
		super(errCode, message, cause);
	}
	
	public ImgUploadException(String message) {
		super(errCode, message);
	}

}
