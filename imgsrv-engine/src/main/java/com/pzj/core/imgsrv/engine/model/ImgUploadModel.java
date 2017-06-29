package com.pzj.core.imgsrv.engine.model;

import com.pzj.core.imgsrv.dao.entity.TblImage;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.toolkit.Check;

public class ImgUploadModel {
	
	private TblImage imgInfo;
	
	private byte[] imgIO;
	
	public ImgUploadModel(byte[] imgIO){
		this.imgIO = imgIO;
	}
	
	public ImgUploadModel(TblImage imgInfo, byte[] imgIO){
		this.imgInfo = imgInfo;
		this.imgIO = imgIO;
	}

	public TblImage getImgInfo() {
		if(Check.NuNObject(this.imgInfo)){
			imgInfo = new TblImage();
		}
		return imgInfo;
	}
	
	public void setImgInfo(TblImage imgInfo) {

		this.imgInfo = imgInfo;
	}

	public byte[] getImgIO() {
		return imgIO;
	}
	
	@Override
	public String toString(){
		String result = "{imgInfo : "+JSONConverter.toJson(this.getImgInfo());
		if(Check.NuNObject(this.getImgIO())){
			result += "}";
		}else{
			result += ",imgIO : data}";
		}
		
		return result;
	}
	
	

}
