package com.pzj.core.imgsrv.service;


import com.pzj.core.imgsrv.model.ImgQueryResultSModel;
import com.pzj.core.imgsrv.model.ImgQuerySModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.entity.QueryResult;

public interface ImgService {
	
	/**
	 * 登录
	 */
	public Result<Boolean> login(String username,String password);
	
	/**
	 * 图片列表展示
	 * @param queryParam
	 * @return
	 */
	public Result<QueryResult<ImgQueryResultSModel>> imgList(ImgQuerySModel queryParam);

}
