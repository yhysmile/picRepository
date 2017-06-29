package com.pzj.core.imgsrv.resolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pzj.core.imgsrv.dao.entity.TblImage;
import com.pzj.core.imgsrv.dao.entity.TblImageQuery;
import com.pzj.core.imgsrv.engine.OSSEngine;
import com.pzj.core.imgsrv.engine.model.ImgQueryResultEModel;
import com.pzj.core.imgsrv.engine.properties.ImgPropertiesLoader;
import com.pzj.core.imgsrv.model.ImgQueryResultSModel;
import com.pzj.core.imgsrv.model.ImgQuerySModel;
import com.pzj.core.imgsrv.model.ImgStyleModel;
import com.pzj.core.imgsrv.utils.ImageURLUtil;
import com.pzj.framework.entity.QueryResult;
import com.pzj.framework.toolkit.Check;

@Component("imgQueryResolver")
public class ImgQueryResolver {
	
	@Autowired
	private OSSEngine ossEngine;

	public TblImageQuery convertIn(ImgQuerySModel source){
		if(Check.NuNObject(source)){
			return null;
		}
		TblImageQuery target = new TblImageQuery();
		target.setSearchKey(source.getSearchKey());
		target.setSearchStartDate(source.getSearchStartDate());
		target.setSearchEndDate(source.getSearchEndDate());
		target.setPage(source.getPage());
		return target;		
	}
	
	public QueryResult<ImgQueryResultSModel> convertOut(ImgQueryResultEModel source,String styleName){
		QueryResult<ImgQueryResultSModel> result = new QueryResult<ImgQueryResultSModel>(source.getCurrentPage(),
				source.getPageSize());
		result.setTotal(source.getTotal());
		List<ImgQueryResultSModel> resultData = new ArrayList<ImgQueryResultSModel>();
		if (!Check.NuNObject(source.getData())) {
			ImgStyleModel styleModel = null;
			if(!Check.NuNString(styleName)){
				styleModel = new ImgStyleModel(ImgPropertiesLoader.getDefaultStyleName());
			}
		
			for (TblImage entity : source.getData()) {
				if(Check.NuNObject(entity)){
					continue;
				}
				resultData.add(convertEntity(entity,styleModel));
			}
		}

		result.setRecords(resultData);

		return result;
		
		
	}
	
	public ImgQueryResultSModel convertEntity(TblImage source,ImgStyleModel style){
		ImgQueryResultSModel target = new ImgQueryResultSModel();
		target.setCreateBy(source.getCreateBy());
		target.setCreateTime(source.getCreateTime());
		target.setExtension(source.getExtension());
		target.setId(source.getId());
		target.setIsHistoricalData(source.getIsHistoricalData());
		target.setMd5name(source.getMd5name());
		target.setName(source.getName());
		target.setPath(ossEngine.visit(source.getPath(),style ));
		
		return target;
	}
}
