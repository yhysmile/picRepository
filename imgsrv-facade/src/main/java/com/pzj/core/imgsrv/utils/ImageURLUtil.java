package com.pzj.core.imgsrv.utils;



import com.pzj.core.imgsrv.common.DirectionEnum;
import com.pzj.core.imgsrv.common.OperateTypeEnum;
import com.pzj.core.imgsrv.common.ZoomLimitEnum;
import com.pzj.core.imgsrv.common.ZoomTypeEnum;
import com.pzj.core.imgsrv.exception.ImgParamException;
import com.pzj.core.imgsrv.model.ImgStyleModel;
import com.pzj.framework.toolkit.Check;

public enum ImageURLUtil {
	INTANCE;

	/**
	 * 获取阿里云图片的全路径
	 * 
	 * @param ossUrl
	 *            阿里云地址
	 * @param model
	 *            图片相关信息
	 * @return
	 */
	public String getImageURL(String ossUrl, String fileName, ImgStyleModel style) {
		if (Check.NuNString(ossUrl)) {
			throw new ImgParamException("获取阿里云图片的全路径[ImageURLUtil.getImageURL],阿里云地址[ossUrl]为空");
		}

		if (Check.NuNString(fileName)) {
			throw new ImgParamException("[ImageURLUtil.getImageURL],图片文件名称[fileName]为空");
		}
		if(fileName.indexOf(".") < 0 ){
			fileName += ".png";
		}
		StringBuffer buff = new StringBuffer();
		buff.append(ossUrl).append("/").append(fileName);
		buff.append(getStyleURL(style));

		return buff.toString();

	}

	private String getStyleURL(ImgStyleModel style) {
		if (Check.NuNObject(style)) {
			return "";
		}
		StringBuffer buff = new StringBuffer();
		buff.append("?x-oss-process=");
		if(!Check.NuNString(style.getStyleName())){
			appendStyleName(buff,style.getStyleName());
		}else{
			appendHandleKey(buff);
			appendWidth(buff,style.getWidth());
			appendHeight(buff,style.getHeigth());
			if(style.isNeedHandle()){
				appendZoomType(buff,ZoomTypeEnum.getEnum(style.getZoomType()));
				appendZoomLimit(buff,ZoomLimitEnum.getEnum(style.getZoomLimit()));
				appendDirection(buff,OperateTypeEnum.getEnum(style.getOperateType()),DirectionEnum.getEnum(style.getDirection()),style.getSharpeningValue());
				appendImgQuality(buff,style.getImgQuality());
			}
		
		}
		
		return buff.toString().substring(0,buff.length()-1);
		
	
	}

	private void appendStyleName(StringBuffer buff, String styleName) {
		if (Check.NuNString(styleName)) {
			return;
		}

		buff.append("style/").append(styleName);
	}

	private void appendHandleKey(StringBuffer buff) {
		if (buff.indexOf("image/resize,") < 0) {
			buff.append("image/resize,");
		}

	}

	private void appendWidth(StringBuffer buff, int width) {
		if (width < 1) {
			return;
		}
		buff.append("w_").append(width).append(",");
	}

	private void appendHeight(StringBuffer buff, int height) {
		if (height < 1) {
			return;
		}
		buff.append("h_").append(height).append(",");
	}

	private void appendZoomType(StringBuffer buff, ZoomTypeEnum zoomType) {
		if (Check.NuNObject(zoomType)) {
			zoomType = ZoomTypeEnum.FIXED_W_ADAPTED_H;
		}
		switch (zoomType) {
		case SHORT_ZOOM_CENTER_CUT:
			buff.append("m_fill").append(",");
			break;
		case LONG_ZOOM_ABBREVIATED_FILL:
			buff.append("m_pad").append(",");
			break;
		case FIXED_W_ADAPTED_H:
			buff.append("m_lfit").append(",");
			break;
		case FIXED_H_ADAPTED_W:
			buff.append("m_lfit").append(",");
			break;
		case LIMIT_WH_SHORT_ZOOM:
			buff.append("m_lfit").append(",");
			break;
		case LIMIT_WH_LONG_ZOOM:
			buff.append("m_mfit").append(",");
			break;
		}
	}
	
	private void appendZoomLimit(StringBuffer buff, ZoomLimitEnum zoomLimit) {
		if (Check.NuNObject(zoomLimit)) {
			zoomLimit = ZoomLimitEnum.NO_FORBID_ZOOM;
		}
		switch (zoomLimit) {
		case NO_FORBID_ZOOM:
			buff.append("limit_0/auto-orient").append(",");
			break;
		case FORBID_ZOOM:
			buff.append("limit_1/auto-orient").append(",");
			break;
		}
	}
	
	private void appendDirection(StringBuffer buff, OperateTypeEnum operateType,DirectionEnum direction,final int sharpeningValue) {
	    if(Check.NuNObject(operateType)){
	    	operateType = OperateTypeEnum.NO_SHARPENING;
	    }
	    if(Check.NuNObject(direction)){
	    	direction = DirectionEnum.ORIGINAL_DEFAULT;
	    }
		
	    buff.append(direction.getDirection()).append("/sharpen").append(",");
		switch (operateType) {
			case NO_SHARPENING:
				buff.append("0/quality").append(",");		
				break;
			case SHARPENING:
				buff.append(sharpeningValue).append("/quality").append(",");
				break;
		}
		if( buff.indexOf("/quality") < 0){
			buff.append(direction).append("/quality").append(",");
		}
	}
	
	private void appendImgQuality(StringBuffer buff, int imgQuality) {
		if (imgQuality < 1) {
			imgQuality = 100;
		}
		buff.append("q_").append(imgQuality).append(imgQuality);
	}
	
	
	


}
