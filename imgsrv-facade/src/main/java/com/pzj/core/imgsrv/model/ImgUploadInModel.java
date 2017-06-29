package com.pzj.core.imgsrv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pzj.framework.toolkit.Check;


public class ImgUploadInModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7526071249014319078L;
	/**创建人*/
	private  Long createBy;
	
	private List<MultipartFile> files;
	
	public ImgUploadInModel(){
		
	}
	
	public ImgUploadInModel(Long createBy,MultipartFile[] files){
		this.createBy = createBy;
		this.files = Arrays.asList(files);
	}

	public Long getCreateBy() {
		return createBy;
	}
	
	public void setCreateBy(Long createBy){
		this.createBy = createBy;
	}
	
	public void setFiles(MultipartFile[] files){
		this.files = Arrays.asList(files);
	}

	public List<MultipartFile> getFiles(){

		return files;
	}
	
	public void addFile(MultipartFile file){
		getFiles().add(file);
	}


	
	
    
	
	
	

}
