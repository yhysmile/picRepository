package com.pzj.core.imgsrv.common;

public enum ZoomLimitEnum {
	NO_FORBID_ZOOM(1, "不禁止图片缩放"), FORBID_ZOOM(2, "禁止图片放大");

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
	private ZoomLimitEnum(int type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	public static boolean isValid(int zoomLimit) {
		for (ZoomLimitEnum limit : ZoomLimitEnum.values()) {
			if (limit.getType() == zoomLimit) {
				return true;
			}
		}
		return false;
	}
	
	public static ZoomLimitEnum getEnum(int zoomLimit) {
		for (ZoomLimitEnum limit : ZoomLimitEnum.values()) {
			if (limit.getType() == zoomLimit) {
				return limit;
			}
		}
		return null;
	}
}
