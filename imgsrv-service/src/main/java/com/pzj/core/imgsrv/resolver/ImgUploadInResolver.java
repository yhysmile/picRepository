package com.pzj.core.imgsrv.resolver;


import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.pzj.core.imgsrv.common.HistoryDataFlag;
import com.pzj.core.imgsrv.dao.entity.TblImage;
import com.pzj.core.imgsrv.engine.exception.ImgMD5Exception;
import com.pzj.core.imgsrv.engine.exception.ImgResolverException;
import com.pzj.core.imgsrv.engine.model.ImgUploadModel;
import com.pzj.core.imgsrv.engine.properties.ImgPropertiesLoader;
import com.pzj.core.imgsrv.model.ImageEntity;
import com.pzj.core.imgsrv.model.ImgUploadInModel;
import com.pzj.core.imgsrv.utils.MD5FileUtil;
import com.pzj.framework.idgen.IDGenerater;
import com.pzj.framework.toolkit.Check;

@Component
public class ImgUploadInResolver {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private IDGenerater idGenerater;

	public List<ImgUploadModel> convertMultipartFile(ImgUploadInModel x) throws IOException {
		List<ImgUploadModel> result = new ArrayList<ImgUploadModel>();
		if(Check.NuNObject(x) || Check.NuNCollections(x.getFiles())){
			return result;
		}
		String name = "";
		for(MultipartFile item : x.getFiles()){
            if(item.getSize() <=0){
            	throw new ImgResolverException("图片文件流为空");
            }
			ImgUploadModel model = convert(item.getInputStream(),item.getOriginalFilename(),x.getCreateBy());
			if(Check.NuNObject(model))
				continue;
			result.add(model);
		}
		return result;
	}
	
	public List<ImgUploadModel> convertFileItem(List<FileItem> x) {
		List<ImgUploadModel> result = new ArrayList<ImgUploadModel>();
		if(Check.NuNCollections(x)){
			return result;
		}
		String name = "";
		try{		
			for(FileItem item : x){
				if(Check.NuNString(item.getContentType())){
					continue;
				}
				if(item.getSize() < 1){
			         throw new Exception("文件["+item.getName()+"]的字节流为空");
				}
				
				name = item.getName();
				ImgUploadModel model = convert(item.getInputStream(),item.getName(),123456789l);
				if(Check.NuNObject(model))
					continue;
				result.add(model);
			}
		}catch(Exception e){
			 logger.error("ImgUploadInResolver[convertFileItem]获取图片文件流失败,图片名称[{}]",name, e);  
	         throw new ImgMD5Exception("ImgUploadInResolver[convertFileItem]获取图片文件流失败,图片名称["+name+"]",e);
		}
		return result;
	}
	
    public TblImage convertOld(ImageEntity entity){
    	try{		
    		TblImage image = new TblImage();
   		
    		image.setId(entity.getId());
    		image.setCreateBy(123456789l);
    		if(!Check.NuNString(entity.getCreateTime())){
    			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    			image.setCreateTime(format.parse(entity.getCreateTime()).getTime());
    		}else{
    			image.setCreateTime(new Date().getTime());
    		}
    		image.setExtension(ImgPropertiesLoader.getBucketName());
    		image.setIsHistoricalData(HistoryDataFlag.IS_HISTORY.getFlag());
    		image.setMd5name(entity.getMd5name());
    		image.setName(entity.getName());
    		image.setPath(entity.getMd5name()+".png");
    		return image;
    	}catch(Exception e){
    		 logger.error("ImgUploadInResolver[convertOld]实体转换错误,图片名称[{}]",entity.getName(), e);  
	         throw new ImgResolverException("ImgUploadInResolver[convertOld]获取图片文件流失败,图片名称["+entity.getName()+"]",e);
    	}
    	
	
	}
	public ImgUploadModel convert(InputStream fileIO, String fileName, Long createBy){
		
		ImgUploadModel result = new ImgUploadModel(MD5FileUtil.getInputStreamBytes(fileIO));
		TblImage image = result.getImgInfo();
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
		if(ImgPropertiesLoader.getImgSuffixs().indexOf(suffix) < 0){
			suffix = "png";	
		}
		image.setCreateBy(createBy);
		image.setCreateTime(new Date().getTime());
		image.setExtension(ImgPropertiesLoader.getBucketName());
		image.setId(idGenerater.nextId());
		image.setIsHistoricalData(HistoryDataFlag.NOT_IS_HISTORY.getFlag());
		image.setMd5name(MD5FileUtil.getMD5String(result.getImgIO()));
		image.setName(fileName);
		image.setPath(image.getId()+"."+suffix);
		return result;
	}
	
	public  String convertString(List<FileItem> x){
		if(Check.NuNCollections(x)){
			return "";
		}
		StringBuffer buff = new StringBuffer();
		buff.append("[");
		for(FileItem file : x){
			buff.append("{\n\t").append(file.getFieldName()).append("},\n");
		}
		buff.append("]");
		return buff.toString();
	}
	
	public  String convertString(ImgUploadInModel x){
		if(Check.NuNObject(x) ){
			return "";
		}
		StringBuffer buff = new StringBuffer();
		buff.append("{createBy:").append(x.getCreateBy()).append(",").append("\n");
		if(!Check.NuNCollections(x.getFiles())){
			buff.append("files:").append("[").append("\n\t");
			for(MultipartFile file : x.getFiles()){
				buff.append("{\n\t").append(file.getOriginalFilename()).append("},\n");
			}
			buff.append("]\n");
		}
		
		buff.append("}");
		return buff.toString();
	}
	
	

}
