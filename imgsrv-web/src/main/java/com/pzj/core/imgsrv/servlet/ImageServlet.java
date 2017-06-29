package com.pzj.core.imgsrv.servlet;






import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pzj.core.imgsrv.exception.ImgParamException;
import com.pzj.core.imgsrv.model.ImgStyleModel;
import com.pzj.core.imgsrv.service.ImgUploadService;
import com.pzj.core.imgsrv.utils.UriUtil;
import com.pzj.framework.context.Result;
import com.pzj.framework.toolkit.Check;





@WebServlet(description = "ImageServlet", urlPatterns = { "/ImageServlet/*" })
public class ImageServlet extends BaseServlet {

    private static final long  serialVersionUID = -8414805112219968278L;

    public static final Logger logger           = LoggerFactory.getLogger(ImageServlet.class);
   
   /* @Autowired
    private OldImageService oldImageService;*/
    
    @Autowired
    private ImgUploadService imageService;

    public ImageServlet() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String fileName = "";
    	try{
    		fileName = UriUtil.INTANCE.getFileName(request, this.getServletName());
    		if(Check.NuNString(fileName)){
                throw new ImgParamException("上传图片{ImageServlet.doGet}参数错误,图片名称["+fileName+"]");
    		}  		
    		logger.info("servlet[ImageServlet.doGet],Param:[URI={},servletName={},fileName={}]",request.getRequestURI(),this.getServletName(),fileName);
	     
	        String[] fileNames = fileName.split("/");
	        ImgStyleModel model = null;
	        if(fileNames.length > 1){
	        	int height = Integer.valueOf(fileNames[1]);
	        	model = new ImgStyleModel(height,height);
	        }
	        
        	Result<String> newURL = imageService.visitImage(fileNames[0], model);
        	logger.info("servlet[ImageServlet.doGet],Result:[ossURL={}]",newURL.getData());
        	
        	response.sendRedirect(newURL.getData());	        	
	        
	        
    	}catch(Exception ex){  		
    		logger.error("servlet[ImageServlet.doGet] error,[URI={},servletName={},fileName={}]",request.getRequestURI(),this.getServletName(),fileName,ex);
    	}

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
