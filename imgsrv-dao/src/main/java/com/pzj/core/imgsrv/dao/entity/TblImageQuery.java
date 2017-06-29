package com.pzj.core.imgsrv.dao.entity;

import com.pzj.framework.entity.PageableRequestBean;

public class TblImageQuery {

	private String name;

	private String createBy;

	private Long searchStartDate;

	private Long searchEndDate;

	private String searchKey;

	private String md5name;

	private Integer isHistoricalData;

	private String extension;

	/**分页参数*/
	private PageableRequestBean page = new PageableRequestBean();

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Long getSearchStartDate() {
		return searchStartDate;
	}

	public void setSearchStartDate(Long searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	public Long getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(Long searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getMd5name() {
		return md5name;
	}

	public void setMd5name(String md5name) {
		this.md5name = md5name;
	}

	public Integer getIsHistoricalData() {
		return isHistoricalData;
	}

	public void setIsHistoricalData(Integer isHistoricalData) {
		this.isHistoricalData = isHistoricalData;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public PageableRequestBean getPage() {
		return page;
	}

	public void setPage(PageableRequestBean page) {
		this.page = page;
	}

}
