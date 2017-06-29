package com.pzj.core.imgsrv.engine;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.imgsrv.dao.entity.TblImage;
import com.pzj.core.imgsrv.dao.read.TblImageRMapper;
import com.pzj.core.imgsrv.dao.write.TblImageWMapper;
import com.pzj.core.imgsrv.engine.model.ImgUploadModel;
import com.pzj.core.imgsrv.engine.properties.ImgPropertiesLoader;
import com.pzj.core.imgsrv.model.ImgStyleModel;
import com.pzj.framework.toolkit.Check;

@Component(value = "imgEngine")
public class ImgEngine {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "tblImageWMapper")
	private TblImageWMapper  tblImageWMapper;
	
	@Resource(name = "tblImageRMapper")
	private TblImageRMapper  tblImageRMapper;
	
	@Resource(name = "OSSEngine")
	private OSSEngine ossEngine;
	
	/**
	 * 操作步骤：
	 * 1.上传到阿里云
	 * 2.写入记录
	 * 
	 * @param book
	 * @return
	 */
	@Transactional(value = "imgsrv.transactionManager", propagation = Propagation.REQUIRED, timeout = 3)
	public void upload(ImgUploadModel image) {
		TblImage imageInfo = tblImageRMapper.selectByMD5Name(image.getImgInfo().getMd5name());
		if(Check.NuNObject(imageInfo)){
			tblImageWMapper.insert(image.getImgInfo());
			ossEngine.upload(image);
		}else{
			image.setImgInfo(imageInfo);
		}
       
	}
	
	/**
	 * 操作步骤：
	 * 1.上传到阿里云
	 * 2.写入记录
	 * 
	 * @param book
	 * @return
	 */
	@Transactional(value = "imgsrv.transactionManager", propagation = Propagation.REQUIRED, timeout = 3)
	public void uploadLocal(TblImage imgInfo,String localPath) {
		TblImage imageInfo = tblImageRMapper.selectByMD5Name(imgInfo.getMd5name());
		if(Check.NuNObject(imageInfo)){
			tblImageWMapper.insert(imgInfo);
			ossEngine.uploadLocal(imgInfo,localPath);
		}else{
			imgInfo.setId(imageInfo.getId());
		}
       
	}
	
	public void uploadList(List<ImgUploadModel> images) {	   
		if(!Check.NuNCollections(images)){
			for(ImgUploadModel model : images){
				upload(model);
			}
		}
	}
	
	public String visit(String fileName,ImgStyleModel style){
		if(fileName.indexOf(".") < 0 ){
			fileName += ".png";
		}
		String suffix = fileName.substring(fileName.indexOf(".")+1, fileName.length());
		if(ImgPropertiesLoader.getImgSuffixs().indexOf(suffix) < 0){
			fileName = fileName.substring(0,fileName.indexOf("."))+".png";
		}
		logger.debug("ImgEngine.visit[filenName={}]",fileName);

		TblImage imageInfo = tblImageRMapper.selectByPath(fileName);
		if(Check.NuNObject(imageInfo)){
			return "";
		}
		return ossEngine.visit(fileName, style);
		
	}
	
	
	
}
