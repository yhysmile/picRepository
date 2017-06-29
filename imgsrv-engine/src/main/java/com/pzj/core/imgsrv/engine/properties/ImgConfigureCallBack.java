/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.imgsrv.engine.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.disconf.client.common.update.IDisconfUpdatePipeline;


/*@Component("imgConfigureCallBack")
@Scope("singleton")
@DisconfUpdateService(confFileKeys = { "aliyun.properties","image.properties" }) */
public class ImgConfigureCallBack implements IDisconfUpdatePipeline {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void reloadDisconfFile(String arg0, String arg1) throws Exception {
		logger.info("重新加载图片服务配置文件---start");
		ImgPropertiesLoader.loadProperties();
		logger.info("重新加载图片服务配置文件---end");
		
	}


	public void reloadDisconfItem(String arg0, Object arg1) throws Exception {
		
	}
   


   
    

   


}
