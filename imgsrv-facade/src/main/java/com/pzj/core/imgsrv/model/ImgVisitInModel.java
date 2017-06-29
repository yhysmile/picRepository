package com.pzj.core.imgsrv.model;

import java.io.Serializable;

import com.pzj.framework.converter.JSONConverter;

public class ImgVisitInModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -856918827135809251L;

	/**图片名称*/
	private String fileName;

	/**图片展示样式*/
	private ImgStyleModel style;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ImgStyleModel getStyle() {
		return style;
	}

	public void setStyle(ImgStyleModel style) {
		this.style = style;
	}

	@Override
	public String toString() {
		return JSONConverter.toJson(this);
	}
	
	
	
	
	

}
