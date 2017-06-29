package com.pzj.core.imgsrv.vo;


public class ImageVo implements java.io.Serializable {

	private static final long serialVersionUID = -4859006792650005331L;

	private String name;

	private String md5Name;

	private String path;

	private String createDate;

	private String extend;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMd5Name() {
		return md5Name;
	}

	public void setMd5Name(String md5Name) {
		this.md5Name = md5Name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

}
