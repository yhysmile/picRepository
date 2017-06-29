package com.pzj.core.imgsrv.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.pzj.core.imgsrv.exception.ImgParamException;
import com.pzj.core.imgsrv.model.ImgUploadInModel;
import com.pzj.framework.toolkit.Check;

@Component
public class ImgUploadValidator {

	public boolean validator(ImgUploadInModel in){
		if(Check.NuNObject(in)){
			throw new ImgParamException("上传图片参数错误");
		}
		
		if(Check.NuNObject(in.getCreateBy())){
			throw new ImgParamException("上传图片参数错误.创建人["+in.getCreateBy()+"]");
		}
		
		if(Check.NuNCollections(in.getFiles())){
			throw new ImgParamException("上传图片参数错误.图片文件["+in.getFiles()+"]");
		}
		
		for(MultipartFile file : in.getFiles()){
			if(file.getSize() == 0){
				throw new ImgParamException("上传图片参数错误.图片文件["+file.getOriginalFilename()+"]的流数据为["+file.getSize()+"]");
			}
			
		}
		return true;
	}
}
