package com.pzj.core.imgsrv.restful;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pzj.core.imgsrv.exception.ImgExceptionCode;
import com.pzj.core.imgsrv.model.ImgUploadInModel;
import com.pzj.core.imgsrv.model.ImgUploadOutModel;
import com.pzj.core.imgsrv.service.ImgUploadService;
import com.pzj.framework.context.Result;
import com.pzj.framework.toolkit.Check;

@RestController
public class ImgUploadController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ImgUploadService imageService;
	
	@RequestMapping(value = "upload", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result<ArrayList<ImgUploadOutModel>> upload(@RequestParam("file") MultipartFile[] files){
		long start = System.currentTimeMillis();
		if(Check.NuNArray(files)){
			new Result<ArrayList<ImgUploadOutModel>>(ImgExceptionCode.IMG_PARAM_EXCEPTION,"上传文件为空");
		}
		ImgUploadInModel model = new ImgUploadInModel(123456789l,files);
		Result<ArrayList<ImgUploadOutModel>> result = imageService.uploadImages(model);
		logger.info("图片上传效率：{}",  System.currentTimeMillis() - start);
		return result;
	}
	
	@RequestMapping(value = "uploadHistorical", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> uploadHistoricaldata(@RequestParam(value="imgNum", required=false)int imgNum){
		Result<String> result = imageService.uploadOldImage(imgNum);
		
		return result;
	}
}
