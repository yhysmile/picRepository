package com.pzj.core.imgsrv.model;

import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.PageableRequestBean;

public class ImgQuerySModel {
	private String searchKey;

	private Long searchStartDate;

	private Long searchEndDate;

	private PageableRequestBean page;

	private String styleName;

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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

	public PageableRequestBean getPage() {
		return page;
	}

	public void setPage(PageableRequestBean page) {
		this.page = page;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public static void main(String[] args) {
		ImgQuerySModel obj = new ImgQuerySModel();
		PageableRequestBean page = new PageableRequestBean();
		page.setCurrentPage(1);
		page.setPageSize(20);
		obj.setPage(page);
		obj.setStyleName("defaultStyle");
		System.out.println(JSONConverter.toJson(obj));
	}

}
