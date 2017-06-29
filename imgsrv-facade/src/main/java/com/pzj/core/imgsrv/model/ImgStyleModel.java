package com.pzj.core.imgsrv.model;

import java.io.Serializable;

import com.pzj.core.imgsrv.common.DirectionEnum;
import com.pzj.core.imgsrv.common.OperateTypeEnum;
import com.pzj.core.imgsrv.common.ZoomLimitEnum;
import com.pzj.core.imgsrv.common.ZoomTypeEnum;
import com.pzj.framework.converter.JSONConverter;

public class ImgStyleModel implements Serializable {

	private static ImgStyleModel defaultStyle = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5462655475801760497L;

	/**样式名称*/
	private String styleName;

	/**宽度*/
	private int width;

	/**高度*/
	private int heigth;

	/**缩放方式 {@linkplain ZoomTypeEnum}}*/
	private int zoomType;

	/**缩放限制 {@linkplain ZoomLimitEnum}}*/
	private int zoomLimit;

	/**适应方向 {@linkplain DirectionEnum}}*/
	private int direction;

	/**图片处理  {@linkplain OperateTypeEnum}*/
	private int operateType;

	/**锐化数值 {50-399}*/
	private int sharpeningValue;

	/**图片质量 {0-100}*/
	private int imgQuality;

	/**图片格式*/
	private String imgSuffix;

	public ImgStyleModel() {

	}

	public ImgStyleModel(String styleName) {
		this.styleName = styleName;
	}

	public ImgStyleModel(int width, int heigth) {
		this.width = width;
		this.heigth = heigth;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public int getZoomType() {
		return zoomType;
	}

	public void setZoomType(int zoomType) {
		this.zoomType = zoomType;
	}

	public int getZoomLimit() {
		return zoomLimit;
	}

	public void setZoomLimit(int zoomLimit) {
		this.zoomLimit = zoomLimit;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public int getImgQuality() {
		return imgQuality;
	}

	public void setImgQuality(int imgQuality) {
		this.imgQuality = imgQuality;
	}

	public String getImgSuffix() {
		return imgSuffix;
	}

	public void setImgSuffix(String imgSuffix) {
		this.imgSuffix = imgSuffix;
	}

	@Override
	public String toString() {
		return JSONConverter.toJson(this);
	}

	public int getSharpeningValue() {
		return sharpeningValue;
	}

	public void setSharpeningValue(int sharpeningValue) {
		this.sharpeningValue = sharpeningValue;
	}

	public boolean isNeedHandle() {
		return this.getZoomType() > 0 || this.getZoomLimit() > 0 || this.getDirection() > 0
				|| this.getOperateType() > 0 || imgQuality > 0;
	}

}
