package com.pzj.core.imgsrv.common;

public enum OperateTypeEnum {
	NO_SHARPENING(0, "不锐化"), SHARPENING(1, "锐化");

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
	private OperateTypeEnum(int type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	public static boolean isValid(int operateType) {
		for (OperateTypeEnum type : OperateTypeEnum.values()) {
			if (type.getType() == operateType) {
				return true;
			}
		}
		return false;
	}
	
	public static OperateTypeEnum  getEnum(int operateType) {
		for (OperateTypeEnum type : OperateTypeEnum.values()) {
			if (type.getType() == operateType) {
				return type;
			}
		}
		return null;
	}
}
