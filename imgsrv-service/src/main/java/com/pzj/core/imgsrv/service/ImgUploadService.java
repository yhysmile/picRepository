package com.pzj.core.imgsrv.service;

import java.util.ArrayList;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.pzj.core.imgsrv.model.ImgStyleModel;
import com.pzj.core.imgsrv.model.ImgUploadInModel;
import com.pzj.core.imgsrv.model.ImgUploadOutModel;

import com.pzj.framework.context.Result;

import net.sf.json.JSONObject;

public interface ImgUploadService {
	/**
	 * 上传图片
	 * @param input
	 * @return
	 */
	public Result<ArrayList<ImgUploadOutModel>> uploadImages(ImgUploadInModel model);
	
	/**
	 * 上传图片（兼容老接口）
	 * @param input
	 * @return
	 */
	public Result<JSONObject> uploadImages(List<FileItem> files);
	
	
	/**
	 * 获取新图片的阿里云地址
	 * @param fileName
	 * @return
	 */
	public Result<String> visitImage(String fileName,ImgStyleModel style);
	
	/**
	 * 历史数据迁移
	 */
	public Result<String> uploadOldImage(int imgNum);
	
	
	
}
