/*
 * TblImageMapper.java
 
 * www.piaozhijia.coim
 */
package com.pzj.core.imgsrv.dao.write;

import java.util.List;

import com.pzj.core.imgsrv.dao.entity.TblImage;

/**
 * Mapper接口.区域
 * @author 票之家
 */

public interface TblImageWMapper {  
	
	/**新增*/
	int insert(TblImage entity);
	
	/**批量新增*/
	int insertBatch(List<TblImage> entitys);
	
	
	/**根据主键删除*/
	int deleteByPrimaryKey(long id);
    
    
}
