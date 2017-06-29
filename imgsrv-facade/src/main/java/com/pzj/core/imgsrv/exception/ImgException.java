package com.pzj.core.imgsrv.exception;

import com.pzj.framework.exception.ServiceException;

public class ImgException extends ServiceException{
	private static final long serialVersionUID = 9013630919750866659L;

	private int errCode;

	public ImgException(final int errCode, final String message) {
		this(errCode, message, null);
	}

	public ImgException(int errCode, final String message, final Throwable cause) {
		super(message, cause);
		this.errCode = errCode;
		illegalErrorCode();
	}

	public int getErrCode() {
		return errCode;
	}

	protected final void illegalErrorCode() {
		if (errCode < 17000 || errCode > 18000) {
			throw new ServiceException("图片服务错误码不符合要求.");
		}
	}
	
	

}
