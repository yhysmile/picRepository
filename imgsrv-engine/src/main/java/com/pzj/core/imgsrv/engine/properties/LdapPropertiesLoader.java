package com.pzj.core.imgsrv.engine.properties;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.pzj.core.imgsrv.engine.exception.ImgSystemException;

public class LdapPropertiesLoader {

	private static Logger logger = LoggerFactory.getLogger(LdapPropertiesLoader.class);

	private static Properties ladpProperties;
	
	private static Boolean isFirstLoad = true;


	public static String getUrl() {
		if(isFirstLoad){
			loadProperties();
		}
		return ladpProperties.getProperty("ldap.url");
	}

	public static String getBase() {
		if(isFirstLoad){
			loadProperties();
		}
		return ladpProperties.getProperty("ldap.base");
	}

	public static String getUserDn() {
		if(isFirstLoad){
			loadProperties();
		}
		return ladpProperties.getProperty("ldap.userDn");
	}

	public static String getPassword() {
		if(isFirstLoad){
			loadProperties();
		}
		return ladpProperties.getProperty("ldap.password");
	}

	public static void loadProperties() {
		try {
			ladpProperties = PropertiesLoaderUtils.loadAllProperties("ldap.properties");

		} catch (IOException e) {
			logger.error("ladp配置文件加载失败", e);
			throw new ImgSystemException("ladp配置文件加载失败", e);

		}

	}

}
