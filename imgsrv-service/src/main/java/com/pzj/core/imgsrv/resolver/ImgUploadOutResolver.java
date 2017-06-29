package com.pzj.core.imgsrv.resolver;

import java.util.ArrayList;
import java.util.List;



import org.springframework.stereotype.Component;


import com.pzj.core.imgsrv.engine.model.ImgUploadModel;
import com.pzj.core.imgsrv.model.ImgUploadOutModel;
import com.pzj.framework.toolkit.Check;

import net.sf.json.JSONObject;

@Component
public class ImgUploadOutResolver  {

	public ArrayList<ImgUploadOutModel> convertUploadOut(List<ImgUploadModel> x) {
		ArrayList<ImgUploadOutModel> result = new ArrayList<ImgUploadOutModel>();
		if(Check.NuNCollections(x)){
			return result;
		}
		for(ImgUploadModel model : x){
			if(Check.NuNObject(model)){
				continue;
			}
			result.add(convert(model));
		}
		return result;
	}
	
	public JSONObject convertJSONObjectE(){
		JSONObject result = new JSONObject();
		result.put("status", "error");
		return result;
	}
	
	public JSONObject convertJSONObjectS(List<ImgUploadModel> models){
		JSONObject result = new JSONObject();
		if(Check.NuNCollections(models)){
			result.put("status", "error");
		}
	
		result.put("status", "success");
		result.put("data", getJsonData(models));
		return result;
	}
	private String getJsonData(List<ImgUploadModel> models){	
	    return models.get(0).getImgInfo().getPath();		
	}
	
	private ImgUploadOutModel convert(ImgUploadModel x){
		ImgUploadOutModel result = new ImgUploadOutModel(x.getImgInfo().getName(),x.getImgInfo().getPath());
		return result;

	}

}
