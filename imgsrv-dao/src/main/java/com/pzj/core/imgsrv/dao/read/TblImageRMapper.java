/*
 * TblImageMapper.java
 
 * www.piaozhijia.coim
 */
package com.pzj.core.imgsrv.dao.read;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.imgsrv.dao.entity.TblImage;
import com.pzj.core.imgsrv.dao.entity.TblImageQuery;


/**
 * Mapper接口.区域
 * @author 票之家
 */

public interface TblImageRMapper{    
    
    /**
     * 通用分页查询
     */
    List<TblImage> selectByParam(@Param("bParam") TblImageQuery bParam);
    
    /**
     * 通用查询个数统计
     * @return
     */
    Integer countByParam(@Param("bParam") TblImageQuery bParam);
    
    /**
     * 根据图片MD5值查询图片记录
     * @param md5name
     * @return
     */
    TblImage selectByMD5Name(@Param("md5name") String md5name);
    
    /**
     * 根据图片path值查询图片记录
     * @param md5name
     * @return
     */
    TblImage selectByPath(@Param("path") String path);
    
}
