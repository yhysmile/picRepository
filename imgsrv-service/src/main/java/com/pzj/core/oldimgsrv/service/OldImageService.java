package com.pzj.core.oldimgsrv.service;

import java.util.List;

import com.pzj.core.imgsrv.model.ImageEntity;

public interface OldImageService {

		 
		 /**
		  * 获取图片路径
		  * @param string
		  * @param basePath
		  * @return
		  * @throws Exception
		  */
		 public String selectImagePath(String string) throws Exception;
		 
		 /**
		  * 获取图片路径
		  * @param entity
		  * @return
		  */
		 public String getImagePath(Boolean isNeedPNG,ImageEntity entity);
		 
		 /**
		  * 图片分页
		  * @param pageNum
		  * @param rowNum
		  * @param status
		  * @return
		  */
		 public List<ImageEntity> getPage(int pageNum, int rowNum, String status);
		 
		 /**
		  * 获取所有图片数据
		  * @return
		  */
		 public List<ImageEntity> getAll();
		 
		 /**
		  * 获取图片数据
		  * @return
		  */
		 public List<ImageEntity> getImgsByNum(int imgNum);
		 
		 /**
		  * 图片审核
		  * @param md5name
		  */
		 public void upadteStatus(String md5name);

		 /**
		  * 删除图片数据
		  * @param md5name
		  */
		 public void del(String md5name) ;
}
