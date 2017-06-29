package com.pzj.core.imgsrv.common;

public enum CutTypeEnum {
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
	private CutTypeEnum(int type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	public static boolean isValid(int cutType) {
		for (CutTypeEnum type : CutTypeEnum.values()) {
			if (type.getType() == cutType) {
				return true;
			}
		}
		return false;
	}
}
