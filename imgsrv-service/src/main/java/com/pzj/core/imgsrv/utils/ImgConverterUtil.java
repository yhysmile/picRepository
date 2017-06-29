package com.pzj.core.imgsrv.utils;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.imgsrv.engine.properties.ImgPropertiesLoader;
import com.pzj.framework.toolkit.Check;

public enum ImgConverterUtil {
	INTANCE;
	private static  Logger logger =  LoggerFactory.getLogger(ImgConverterUtil.class);
	
	public boolean isImgSuffix(String extendsion){
		if(Check.NuNString(extendsion)|| ImgPropertiesLoader.getImgSuffixs().toLowerCase().indexOf(extendsion.toLowerCase()) < 0){
			return false;
		}
		return true;
	}
	
	public void convertPNG(String source,  String result) {
		try {
			File f = new File(source);
			f.canRead();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, "png", new File(result));
		} catch (Exception e) {
			logger.error("图片转png失败，source:{},result:{}",source,result,e);
			e.printStackTrace();
		}
	}

	public void delete(String source) {
		try {
			File f = new File(source);
			if (f.exists()) {
				f.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String source = "D:/图片/image/2017-05-15/14948272091796853/1494827209179376.supportUpload";
		String formatName = "1494827209179376";
		String result = "D:/图片/image/2017-05-15/14948272091796853/1494827209179376.png";
		ImgConverterUtil.INTANCE.convertPNG(source,result);
	}

}
