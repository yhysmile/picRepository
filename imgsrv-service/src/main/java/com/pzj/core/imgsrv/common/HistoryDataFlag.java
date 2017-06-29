package com.pzj.core.imgsrv.common;

public enum HistoryDataFlag {
	NOT_IS_HISTORY(0,"是历史数据"),IS_HISTORY(1,"是历史数据");
	private int flag;
	private String msg;
	private HistoryDataFlag(int flag,String msg){
		this.flag = flag;
		this.msg = msg;
		
	}
	public int getFlag() {
		return flag;
	}

	public String getMsg() {
		return msg;
	}

	

}
