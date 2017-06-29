package com.pzj.core.imgsrv.engine.model;

import java.util.List;

import com.pzj.core.imgsrv.dao.entity.TblImage;

public class ImgQueryResultEModel {
	private List<TblImage> data;

	final private int total;

	/** 当前页个数*/
	final private int currentPage;

	/** 页数*/
	final private int pageSize;
	
	public ImgQueryResultEModel(final int total, final int currentPage, final int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.total = total;
	}

	public List<TblImage> getData() {
		return data;
	}

	public void setData(List<TblImage> data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public static class Bulider {
		private int total;

		/** 当前页个数*/
		private int currentPage;

		/** 页数*/
		private int pageSize;

		public Bulider setTotal(int total) {
			this.total = total;
			return this;
		}

		public Bulider setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
			return this;
		}

		public Bulider setPageSize(int pageSize) {
			this.pageSize = pageSize;
			return this;
		}

		public ImgQueryResultEModel bulid() {
			return new ImgQueryResultEModel(this);
		}
	}

	private ImgQueryResultEModel(Bulider bulider) {
		this.currentPage = bulider.currentPage;
		this.pageSize = bulider.pageSize;
		this.total = bulider.total;
	}

}
