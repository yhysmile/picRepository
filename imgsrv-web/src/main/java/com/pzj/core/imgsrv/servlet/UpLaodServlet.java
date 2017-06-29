package com.pzj.core.imgsrv.servlet;

import java.io.File;


import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.pzj.core.imgsrv.service.ImgUploadService;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;

import net.sf.json.JSONObject;



@WebServlet("/UpLaodServlet")
public class UpLaodServlet extends BaseServlet {

	private static final long serialVersionUID = 4997998197210097241L;

	public static final Logger logger = LoggerFactory.getLogger(UpLaodServlet.class);

	@Lazy
	@Autowired
	private ImgUploadService imageService;

	public UpLaodServlet() {
		super();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = null;
		try {

			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();

			JSONObject jo = new JSONObject();
			jo.put("code", "20001");
			jo.put("msg", "请求方式错误，请使用post请求");

			if (logger.isDebugEnabled()) {
				logger.debug(jo.toString());
			}

			out.write(jo.toString());
			out.flush();

		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Result<JSONObject> result = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			List<FileItem> files = getUploadFile(request);
			result = imageService.uploadImages(files);
			logger.info("老接口图片上传结果：{}",JSONConverter.toJson(result));
		} catch (Exception e) {
			logger.error("上传失败", e);
			
		} finally {
			out.print(result.getData().toString());
			out.flush();
			out.close();
		}
	}

	private List<FileItem> getUploadFile(HttpServletRequest request) {

		List<FileItem> list = null;
		try {
			String path = request.getSession().getServletContext().getRealPath("/");
			DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024, new File(path));
			ServletFileUpload upload = new ServletFileUpload(factory);
			list = upload.parseRequest(request);
		} catch (FileUploadException e) {
			logger.error("获取上传文件失败", e);
		}
		return list;
	}

}
