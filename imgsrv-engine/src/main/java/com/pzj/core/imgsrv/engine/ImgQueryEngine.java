package com.pzj.core.imgsrv.engine;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.imgsrv.dao.entity.TblImage;
import com.pzj.core.imgsrv.dao.entity.TblImageQuery;
import com.pzj.core.imgsrv.dao.read.TblImageRMapper;
import com.pzj.core.imgsrv.engine.model.ImgQueryResultEModel;

@Component(value = "imgQueryEngine")
public class ImgQueryEngine {
	
	@Resource(name = "tblImageRMapper")
	private TblImageRMapper tblImageRMapper;
	

	public ImgQueryResultEModel imglist(TblImageQuery queryParam) {
		int totalNum = tblImageRMapper.countByParam(queryParam);
		ImgQueryResultEModel result = new ImgQueryResultEModel.Bulider()
				.setCurrentPage(queryParam.getPage().getCurrentPage()).setPageSize(queryParam.getPage().getPageSize())
				.setTotal(totalNum).bulid();
		if (totalNum == 0) {
			return result;
		}

		List<TblImage> data = tblImageRMapper.selectByParam(queryParam);
		result.setData(data);

		return result;
	}
}
