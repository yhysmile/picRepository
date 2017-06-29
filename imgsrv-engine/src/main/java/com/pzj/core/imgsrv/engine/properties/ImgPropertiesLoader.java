package com.pzj.core.imgsrv.engine.properties;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.pzj.core.imgsrv.engine.exception.ImgSystemException;

public class ImgPropertiesLoader {
	
	private static Logger logger = LoggerFactory.getLogger(ImgPropertiesLoader.class);
	
	private static Properties ossProperties;
	
	private static Properties imgProperties;
	
	private static Boolean isFirstLoad = true;


	public static String getEndpoint() {
		if(isFirstLoad){
			loadProperties();
		}
		return ossProperties.getProperty("oss.endpoint");
	}

	public static String getAccessKeyId() {
		if(isFirstLoad){
			loadProperties();
		}
		return ossProperties.getProperty("oss.accessKeyId");
	}
	
	public static String getAccessKeySecret() {
		if(isFirstLoad){
			loadProperties();
		}
		return ossProperties.getProperty("oss.accessKeySecret");
	}

	public static String getBucketName() {
		if(isFirstLoad){
			loadProperties();
		}
		return ossProperties.getProperty("oss.bucketName");
	}
	
	public static String getVisitUrl(){
		return getEndpoint().replace("://", "://"+getBucketName()+".");
	}

	public static String getImgPath() {
		if(isFirstLoad){
			loadProperties();
		}
		return imgProperties.getProperty("img.basePath");
	}
	
	public static String getImgSuffixs(){
		if(isFirstLoad){
			loadProperties();
		}
		return imgProperties.getProperty("img.suffixs");
	}

	public static String getDbpath() {
		if(isFirstLoad){
			loadProperties();
		}
		return imgProperties.getProperty("img.dbpath");
	}
	
	public static String getImgSize() {
		if(isFirstLoad){
			loadProperties();
		}
		return imgProperties.getProperty("img.size");
	}

	public static String getDefaultStyleName(){
		if(isFirstLoad){
			loadProperties();
		}
		return ossProperties.getProperty("default.styleName");
	}

	public static void loadProperties(){
		try {
			ossProperties = PropertiesLoaderUtils.loadAllProperties("aliyun.properties");
		    imgProperties = PropertiesLoaderUtils.loadAllProperties("image.properties");
		    isFirstLoad = false;
		
		} catch (IOException e) {
			logger.error("图片服务加载配置文件出错", e);  
			throw new ImgSystemException("图片服务加载配置文件出错",e);
		
		}
		
	}
	
	
	

}
