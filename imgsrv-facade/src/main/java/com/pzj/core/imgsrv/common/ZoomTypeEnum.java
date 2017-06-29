package com.pzj.core.imgsrv.common;

public enum ZoomTypeEnum {
	SHORT_ZOOM_CENTER_CUT(1, "按短边缩放，居中裁剪"), LONG_ZOOM_ABBREVIATED_FILL(2, "按长边缩放，缩略填充"),FIXED_W_ADAPTED_H(3,"固定宽度，高度自适应"),
	FIXED_H_ADAPTED_W(4,"固定高度，宽度自适应"),LIMIT_WH_SHORT_ZOOM(5,"限定宽高，按短边缩放"),LIMIT_WH_LONG_ZOOM(6,"限定宽高，按长边缩放");

	private int type;
	private String msg;

	public int getType() {
		return type;
	}

	public String getMsg() {
		return msg;
	}

	/**
	 * @param type
	 * @param msg
	 */
	private ZoomTypeEnum(int type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	public static boolean isValid(int zoomType) {
		for (ZoomTypeEnum type : ZoomTypeEnum.values()) {
			if (type.getType() == zoomType) {
				return true;
			}
		}
		return false;
	}
	
	public static ZoomTypeEnum  getEnum(int zoomType) {
		for (ZoomTypeEnum type : ZoomTypeEnum.values()) {
			if (type.getType() == zoomType) {
				return type;
			}
		}
		return null;
	}
}
