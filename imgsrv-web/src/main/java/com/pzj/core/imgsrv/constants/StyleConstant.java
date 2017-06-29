package com.pzj.core.imgsrv.constants;

public class StyleConstant {

	//缩略方式
	//	指定缩略的模式：
	//	lfit：等比缩放，限制在设定在指定w与h的矩形内的最大图片。
	//	mfit：等比缩放，延伸出指定w与h的矩形框外的最小图片。
	//	fill：固定宽高，将延伸出指定w与h的矩形框外的最小图片进行居中裁剪。
	//	pad：固定宽高，缩略填充
	//	fixed：固定宽高，强制缩略

	//	按短边缩放，居中裁剪
	//	按长边缩放，缩略填充
	//等比缩放
	public static final String UNIFORM_SCALE = "lfit";
	//固定宽高
	public static final String FIXED_WIDTH_HEIGHT = "fixed";

	//缩略限制

	//适应方向

	//图片质量

	//保存格式

	//图片锐化
	public static final String SHARPEN = "sharpen";
}
