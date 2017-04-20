package com.pzj.core.image;

import java.io.InputStream;

import com.pzj.framework.context.Result;

public interface UploadImageService {
	/**
	 * 上传图片
	 * @param input
	 * @return
	 */
	public Result<Boolean> uploadImage( InputStream  input);

}
