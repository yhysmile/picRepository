package com.pzj.core.imgsrv.service.impl;

import java.util.ArrayList;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzj.core.imgsrv.dao.entity.TblImageQuery;
import com.pzj.core.imgsrv.engine.ImgEngine;
import com.pzj.core.imgsrv.engine.ImgQueryEngine;
import com.pzj.core.imgsrv.engine.LadpEngine;
import com.pzj.core.imgsrv.engine.exception.ImgUploadException;
import com.pzj.core.imgsrv.engine.exception.ImgVisitException;
import com.pzj.core.imgsrv.engine.model.ImgQueryResultEModel;
import com.pzj.core.imgsrv.exception.ImgException;
import com.pzj.core.imgsrv.model.ImgQueryResultSModel;
import com.pzj.core.imgsrv.model.ImgQuerySModel;
import com.pzj.core.imgsrv.resolver.ImgQueryResolver;
import com.pzj.core.imgsrv.service.ImgService;
import com.pzj.framework.context.Result;
import com.pzj.framework.entity.QueryResult;



@Service
public class ImgServiceImpl implements ImgService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LadpEngine ladpEngine;
	
	@Autowired
	private ImgQueryEngine imgQueryEngine;
	
	@Resource(name = "imgQueryResolver")
	private ImgQueryResolver imgQueryResolver;


	public Result<Boolean> login(String username, String password) {
		try {

			Boolean result = ladpEngine.verify(username, password);
			return new Result<Boolean>(result);

		} catch (Exception e) {

			logger.error("登录图片服务失败, username: {},password: {}", username, password, e);
			ImgException ex = null;
			if (!(e instanceof ImgException)) {
				ex = new ImgUploadException(e.getMessage(), e);
			} else {
				ex = (ImgException) e;
			}
			return new Result<Boolean>(ex.getErrCode(), ex.getMessage());

		}
	}

	public Result<QueryResult<ImgQueryResultSModel>> imgList(ImgQuerySModel queryModel) {

		try {
			TblImageQuery queryParam = imgQueryResolver.convertIn(queryModel);
            
			ImgQueryResultEModel resultEModel = imgQueryEngine.imglist(queryParam);
			QueryResult<ImgQueryResultSModel> resultData = imgQueryResolver.convertOut(resultEModel, queryModel.getStyleName());
			return new Result<QueryResult<ImgQueryResultSModel>>(resultData);

		} catch (Exception e) {

			logger.error("展示图片列表失败, 查询参数queryModel:{}", queryModel, e);
			ImgException ex = null;
			if (!(e instanceof ImgException)) {
				ex = new ImgVisitException(e.getMessage(), e);
			} else {
				ex = (ImgException) e;
			}
			return new Result<QueryResult<ImgQueryResultSModel>>(ex.getErrCode(), ex.getMessage());

		}
	}

}
