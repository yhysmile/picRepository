/*
 * TblImage.java
 
 * www.piaozhijia.coim
 */
package com.pzj.core.imgsrv.dao.entity;

/**
 * 图片信息
 * @author 票之家
 */

public class TblImage {
	
	/**图片ID*/
	private long id;
	
    /** 图片名称 */
    private String name;
    /** 图片MD5值 */
    private String md5name;
    /** 图片路径 */
    private String path;
    /** 是否是历史数据标识（1：历史数据  0：新数据） */
    private Integer isHistoricalData;
    /** 创建时间 */
    private Long createTime;
    /** 创建人 */
    private Long createBy;
    /** 扩展信息 */
    private String extension;
 
    /** 设置 图片名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 得到 图片名称 */
    public String getName() {
        return name;
    }
    /** 设置 图片MD5值 */
    public void setMd5name(String md5name) {
        this.md5name = md5name;
    }

    /** 得到 图片MD5值 */
    public String getMd5name() {
        return md5name;
    }
    /** 设置 图片路径 */
    public void setPath(String path) {
        this.path = path;
    }

    /** 得到 图片路径 */
    public String getPath() {
        return path;
    }
    /** 设置 是否是历史数据标识（1：历史数据  0：新数据） */
    public void setIsHistoricalData(Integer isHistoricalData) {
        this.isHistoricalData = isHistoricalData;
    }

    /** 得到 是否是历史数据标识（1：历史数据  0：新数据） */
    public Integer getIsHistoricalData() {
        return isHistoricalData;
    }
    /** 设置 创建时间 */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /** 得到 创建时间 */
    public Long getCreateTime() {
        return createTime;
    }
    /** 设置 创建人 */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /** 得到 创建人 */
    public Long getCreateBy() {
        return createBy;
    }
    /** 设置 扩展信息 */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /** 得到 扩展信息 */
    public String getExtension() {
        return extension;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
    
   
}
