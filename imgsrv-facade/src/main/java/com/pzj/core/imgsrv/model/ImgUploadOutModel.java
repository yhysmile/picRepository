package com.pzj.core.imgsrv.model;

import java.io.Serializable;

public class ImgUploadOutModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6222303491482911322L;
	
	private final String fileName;
	
	private final String fileMd5;
	
	public ImgUploadOutModel(String fileName,String fileMd5){
		this.fileName = fileName;
		this.fileMd5 = fileMd5;
	}

	public String getFileName() {
		return fileName;
	}


	public String getFileMd5() {
		return fileMd5;
	}

}
