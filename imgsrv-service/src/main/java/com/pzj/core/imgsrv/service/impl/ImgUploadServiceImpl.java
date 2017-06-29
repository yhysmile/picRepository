package com.pzj.core.imgsrv.service.impl;

import java.util.ArrayList;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzj.core.imgsrv.dao.entity.TblImage;
import com.pzj.core.imgsrv.engine.ImgEngine;
import com.pzj.core.imgsrv.engine.exception.ImgUploadException;
import com.pzj.core.imgsrv.engine.exception.ImgVisitException;
import com.pzj.core.imgsrv.engine.model.ImgUploadModel;
import com.pzj.core.imgsrv.exception.ImgException;
import com.pzj.core.imgsrv.exception.ImgExceptionCode;
import com.pzj.core.imgsrv.exception.ImgParamException;
import com.pzj.core.imgsrv.model.ImageEntity;
import com.pzj.core.imgsrv.model.ImgStyleModel;
import com.pzj.core.imgsrv.model.ImgUploadInModel;
import com.pzj.core.imgsrv.model.ImgUploadOutModel;
import com.pzj.core.imgsrv.resolver.ImgUploadInResolver;
import com.pzj.core.imgsrv.resolver.ImgUploadOutResolver;
import com.pzj.core.imgsrv.service.ImgUploadService;
import com.pzj.core.imgsrv.utils.ImgConverterUtil;
import com.pzj.core.imgsrv.validator.ImgUploadValidator;
import com.pzj.core.oldimgsrv.service.OldImageService;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.toolkit.Check;


import net.sf.json.JSONObject;

@Service
public class ImgUploadServiceImpl implements ImgUploadService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ImgUploadInResolver imgUploadInResolver;

	@Autowired
	private ImgUploadOutResolver imgUploadOutResolver;

	@Autowired
	private OldImageService oldImageService;

	@Autowired
	private ImgEngine imgEngine;
	
	@Autowired
	private ImgUploadValidator imgUploadValidator;


	public Result<ArrayList<ImgUploadOutModel>> uploadImages(ImgUploadInModel model) {
		try {
			logger.info("上传图片,reqModel: {}",imgUploadInResolver.convertString(model));
			
			imgUploadValidator.validator(model);
			
			List<ImgUploadModel> models = imgUploadInResolver.convertMultipartFile(model);
			imgEngine.uploadList(models);
			ArrayList<ImgUploadOutModel> result = imgUploadOutResolver.convertUploadOut(models);
			return new Result<ArrayList<ImgUploadOutModel>>(result);

		} catch (Exception e) {
			logger.error("上传图片创建失败, reqModel: " + imgUploadInResolver.convertString(model), e);
			ImgException ex = null;
			if (!(e instanceof ImgException)) {
				ex = new ImgUploadException(e.getMessage(), e);
			} else {
				ex = (ImgException) e;
			}
			return new Result<ArrayList<ImgUploadOutModel>>(ex.getErrCode(), ex.getMessage());
		}
	}
	
	public Result<String> visitImage(String fileName, ImgStyleModel style) {

		try {
			if (Check.NuNObject(fileName)) {
				logger.error("访问图片失败,图片名称为空");
				throw new ImgParamException("访问图片失败,图片名称为空");
			}
			return new Result<String>(imgEngine.visit(fileName, style));

		} catch (Exception e) {
			logger.error("访问图片失败, reqModel: [fileName:{},style:{}", fileName, JSONConverter.toJson(style), e);
			ImgException ex = null;
			if (!(e instanceof ImgException)) {
				ex = new ImgVisitException(e.getMessage(), e);
			} else {
				ex = (ImgException) e;
			}
			return new Result<String>(ex.getErrCode(), ex.getMessage());
		}
	}

	public Result<JSONObject> uploadImages(List<FileItem> files) {

		try {
			logger.info("上传图片,reqModel: {}",imgUploadInResolver.convertString(files));
			List<ImgUploadModel> models = imgUploadInResolver.convertFileItem(files);		
			imgEngine.uploadList(models);
			JSONObject jo = imgUploadOutResolver.convertJSONObjectS(models);
			return new Result<JSONObject>(jo);

		} catch (Exception e) {
			logger.error("上传图片创建失败, reqModel: " + imgUploadInResolver.convertString(files), e);
			ImgException ex = null;
			if (!(e instanceof ImgException)) {
				ex = new ImgUploadException(e.getMessage(), e);
			} else {
				ex = (ImgException) e;
			}
			Result<JSONObject> result = new Result<JSONObject>(ex.getErrCode(), ex.getMessage());
			result.setData(imgUploadOutResolver.convertJSONObjectE());
			return result;
		}

	}

	public Result<String> uploadOldImage(int imgNum) {
		
		try {
			logger.info("老图片迁移,迁移条数: {}",imgNum);
			List<ImageEntity> all = oldImageService.getImgsByNum(imgNum);
			int uploadNum = 0;
			for (ImageEntity entity : all) {	
				boolean isNeedPNG = !ImgConverterUtil.INTANCE.isImgSuffix(entity.getExtendsion());
				String path = oldImageService.getImagePath(isNeedPNG,entity);
				oldImageService.upadteStatus(entity.getMd5name());
				if (Check.NuNString(path)) {				
					continue;
				} 	
			    TblImage model = imgUploadInResolver.convertOld(entity);		
				imgEngine.uploadLocal(model, path);
				if(isNeedPNG){
					ImgConverterUtil.INTANCE.delete(path);
				}
				uploadNum += 1;
				logger.info("数据迁移，导入图片{}",JSONConverter.toJson(entity));
				
			}
			logger.info("数据迁移，导入老图片：{}条",uploadNum);
			
			return new Result<String>("数据迁移，导入老图片："+uploadNum+"条");
			
		} catch (Exception e) {
			logger.error("数据迁移失败, reqModel: " + imgNum, e);
			ImgException ex = null;
			if (!(e instanceof ImgException)) {
				ex = new ImgUploadException(e.getMessage(), e);
			} else {
				ex = (ImgException) e;
			}
			return new Result<String>(ex.getErrCode(), ex.getMessage());
		}

	}

}
