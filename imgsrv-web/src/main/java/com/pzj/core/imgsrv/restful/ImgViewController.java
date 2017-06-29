package com.pzj.core.imgsrv.restful;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pzj.core.imgsrv.model.ImgQueryResultSModel;
import com.pzj.core.imgsrv.model.ImgQuerySModel;
import com.pzj.core.imgsrv.model.ImgStyleModel;
import com.pzj.core.imgsrv.service.ImgService;
import com.pzj.core.imgsrv.vo.ImageVo;
import com.pzj.core.imgsrv.vo.PageImage;
import com.pzj.framework.context.Result;
import com.pzj.framework.entity.PageableRequestBean;
import com.pzj.framework.entity.QueryResult;

@Controller
public class ImgViewController {

	@Autowired
	private ImgService imgService;

	@RequestMapping(value = "/index")
	public ModelAndView imglist(ImgQuerySModel queryModel, ModelAndView view) {
		if (null != queryModel && null == queryModel.getPage()) {
			queryModel = new ImgQuerySModel();
			PageableRequestBean page = new PageableRequestBean();
			page.setCurrentPage(1);
			page.setPageSize(20);
			queryModel.setPage(page);
		}
		Result<QueryResult<ImgQueryResultSModel>> result = imgService.imgList(queryModel);

		if (result.isOk() && null != result.getData() && null != result.getData().getRecords()) {
			List<ImgQueryResultSModel> records = result.getData().getRecords();
			List<ImageVo> imageVos = new ArrayList<ImageVo>();
			ImageVo imageVo = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (ImgQueryResultSModel record : records) {
				String name = record.getName();
				String md5name = record.getMd5name();
				String path = record.getPath();
				Long createTime = record.getCreateTime();
				String extension = record.getExtension();

				Date date = new Date(createTime);
				String time = sdf.format(date);
				imageVo = new ImageVo();
				imageVo.setName(name);
				imageVo.setMd5Name(md5name);
				imageVo.setPath(path);
				imageVo.setCreateDate(time);
				imageVo.setExtend(extension);
				imageVos.add(imageVo);
			}
			int currentPage = 1;
			int pageSize = 20;
			int totalPage = 0;
			int showStartPage = 0;
			int showEndPage = 0;
			if (null != queryModel && null != queryModel.getPage()) {
				pageSize = queryModel.getPage().getPageSize();
				currentPage = queryModel.getPage().getCurrentPage();
				totalPage = (int) result.getData().getTotalPage();
				if (totalPage > 10) {
					int tempMaxCurPage = currentPage, tempMinCurPage = currentPage;
					int count = 9;
					while (tempMaxCurPage < totalPage && count-- > 0) {
						tempMaxCurPage++;
					}
					while (tempMinCurPage > 0 && count-- > 0) {
						tempMinCurPage--;
					}
					//					System.out.println("tempMaxCurPage:" + tempMaxCurPage + ";tempMinCurPage:" + tempMinCurPage);

					showStartPage = tempMinCurPage;
					showEndPage = tempMaxCurPage;
				} else if (totalPage > 0) {
					showStartPage = 1;
					showEndPage = totalPage;
				}
			}
			PageImage<ImageVo> pageImage = new PageImage<ImageVo>();
			pageImage.setCurrentPage(currentPage);
			pageImage.setPageSize(pageSize);
			pageImage.setTotalPage(totalPage);
			pageImage.setShowStartPage(showStartPage);
			pageImage.setShowEndPage(showEndPage);
			pageImage.setDatas(imageVos);
			view.addObject("pageImage", pageImage);

		}
		view.setViewName("index.jsp");
		return view;
	}

	@RequestMapping(value = "/imgDetail")
	public ModelAndView detailImage(ImgStyleModel styleModel, ModelAndView view, String imgName) {
		String imgUrl = "";
		if (null != imgName && !"".equals(imgName)) {
			ImgQuerySModel queryModel = new ImgQuerySModel();
			PageableRequestBean page = new PageableRequestBean();
			page.setCurrentPage(1);
			page.setPageSize(20);
			queryModel.setPage(page);
			queryModel.setSearchKey(imgName);
			Result<QueryResult<ImgQueryResultSModel>> result = imgService.imgList(queryModel);
			if (result.isOk() && null != result.getData().getRecords() && !result.getData().getRecords().isEmpty()) {
				ImgQueryResultSModel imgModel = result.getData().getRecords().get(0);
				imgUrl = imgModel.getPath();
			}
		}
		view.addObject("imgUrl", imgUrl);
		view.addObject("styleModel", styleModel);
		view.setViewName("detailImage.jsp");
		return view;
	}
}
