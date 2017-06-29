package com.pzj.core.imgsrv.model;

public class ImgQueryResultSModel {
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMd5name() {
		return md5name;
	}
	public void setMd5name(String md5name) {
		this.md5name = md5name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getIsHistoricalData() {
		return isHistoricalData;
	}
	public void setIsHistoricalData(Integer isHistoricalData) {
		this.isHistoricalData = isHistoricalData;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
    
}
