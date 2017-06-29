package com.pzj.core.imgsrv.common;

public enum DirectionEnum {

	ORIGINAL_DEFAULT(0, "按原图默认"), ROTATE_ZOOM(1, "先旋转后缩略");

	private int direction;
	private String msg;

	public int getDirection() {
		return direction;
	}

	public String getMsg() {
		return msg;
	}

	/**
	 * @param direction
	 * @param msg
	 */
	private DirectionEnum(int direction, String msg) {
		this.direction = direction;
		this.msg = msg;
	}

	public static boolean isValid(int direction) {
		for (DirectionEnum directionEnum : DirectionEnum.values()) {
			if (directionEnum.getDirection() == direction) {
				return true;
			}
		}
		return false;
	}
	
	public static DirectionEnum  getEnum(int direction) {
		for (DirectionEnum directionEnum : DirectionEnum.values()) {
			if (directionEnum.getDirection() == direction) {
				return directionEnum;
			}
		}
		return null;
	}
}
