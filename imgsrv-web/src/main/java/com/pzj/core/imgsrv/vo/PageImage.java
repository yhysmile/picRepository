package com.pzj.core.imgsrv.vo;

import java.util.List;

public class PageImage<T> implements java.io.Serializable {

	private static final long serialVersionUID = -7805917712141898888L;

	private int currentPage;

	private int totalPage;

	private int pageSize;

	private List<T> datas;

	private int showStartPage;

	private int showEndPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getShowStartPage() {
		return showStartPage;
	}

	public void setShowStartPage(int showStartPage) {
		this.showStartPage = showStartPage;
	}

	public int getShowEndPage() {
		return showEndPage;
	}

	public void setShowEndPage(int showEndPage) {
		this.showEndPage = showEndPage;
	}

}
