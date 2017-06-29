package com.pzj.core.imgsrv.engine;



import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.PutObjectResult;
import com.pzj.core.imgsrv.dao.entity.TblImage;
import com.pzj.core.imgsrv.engine.exception.ImgOSSException;
import com.pzj.core.imgsrv.engine.model.ImgUploadModel;
import com.pzj.core.imgsrv.engine.properties.ImgPropertiesLoader;
import com.pzj.core.imgsrv.model.ImgStyleModel;
import com.pzj.core.imgsrv.utils.ImageURLUtil;
import com.pzj.framework.toolkit.Check;

@Component(value = "OSSEngine")
public class OSSEngine {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void upload(ImgUploadModel image){
		OSSClient ossClient = null;
		InputStream in = null;
		try{
			ossClient = getOSSClient();
			logger.info("OSSEngine.upload,imageName={},size={}",image.getImgInfo().getPath(),image.getImgIO().length);
			// 上传文件流	
			in = new ByteArrayInputStream(image.getImgIO());
			PutObjectResult result = ossClient.putObject(ImgPropertiesLoader.getBucketName(),
					image.getImgInfo().getPath(),
					in);
			
			logger.info("OSSEngine.upload,imageName={},requestID={},eTag={}",image.getImgInfo().getPath(),result.getRequestId(),result.getETag());
			
		    
			
		}catch(OSSException  e){
			logger.error("图片{}上传[OSSEngine.upload]失败，错误原因:[OSS上传出错，errorMsg:{},errorCode:{},requestID:{},hostID:{}]",image.getImgInfo().getPath(),e.getErrorMessage(), e.getErrorCode(),e.getRequestId(),e.getHostId());  
			throw new ImgOSSException("图片上传失败,错误原因:[OSS上传出错],图片名称["+image.getImgInfo().getMd5name()+"]",e);
			
		}catch(ClientException  e){
			logger.error("图片{}上传失败[OSSEngine.upload],错误原因:[网络连接问题]",image.getImgInfo().getPath(), e);  
			throw new ImgOSSException("图片上传失败,错误原因:[网络连接问题],图片名称["+image.getImgInfo().getMd5name()+"]",e);
			
		}catch(Exception  e){
			logger.error("图片{}上传失败",image.getImgInfo().getPath(), e);  
			throw new ImgOSSException("图片上传失败,错误原因:[系统异常],图片名称["+image.getImgInfo().getMd5name()+"]",e);
		}finally{
			if(!Check.NuNObject(in)){
				// 关闭client
				IOUtils.safeClose(in);
				in = null;
			}
			if(!Check.NuNObject(ossClient)){
				// 关闭client
				ossClient.shutdown();	
			}
		}		
	}
	
	
	public void uploadLocal(TblImage imgInfo,String localPath){
		OSSClient ossClient = null;
		try{
			
			ossClient = getOSSClient();
			// 上传文件流		
			PutObjectResult result = ossClient.putObject(ImgPropertiesLoader.getBucketName(), imgInfo.getPath(), new File(localPath));
			
			logger.debug("OSSEngine.upload, imageName={},eTag={}",imgInfo.getPath(),result.getETag());
			
		    
			
		}catch(OSSException  e){
			logger.error("图片{}上传[OSSEngine.uploadLocal]失败，错误原因:[OSS上传出错，errorMsg:{},errorCode:{},requestID:{},hostID:{}]",imgInfo.getPath(),e.getErrorMessage(), e.getErrorCode(),e.getRequestId(),e.getHostId());  
			throw new ImgOSSException("图片上传失败[OSSEngine.uploadLocal],OSS上传出错,图片名称["+imgInfo.getMd5name()+"]",e);
			
		}catch(ClientException  e){
			logger.error("图片{}上传失败[OSSEngine.uploadLocal],错误原因:[网络连接问题]", e);  
			throw new ImgOSSException("OSSEngine[uploadLocal]oss上传失败,图片名称["+imgInfo.getMd5name()+"]",e);
			
		}finally{
			if(!Check.NuNObject(ossClient)){
				// 关闭client
				ossClient.shutdown();	
			}
		}		
	}
	
	public String visit(String fileName, ImgStyleModel style){
		return ImageURLUtil.INTANCE.getImageURL(ImgPropertiesLoader.getVisitUrl(), fileName, style);
		
	}
	
	
	private OSSClient getOSSClient(){		
		return new OSSClient(ImgPropertiesLoader.getEndpoint(), ImgPropertiesLoader.getAccessKeyId(), ImgPropertiesLoader.getAccessKeySecret());	
	}
	
	

}
