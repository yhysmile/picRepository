package com.pzj.core.oldimgsrv.service.impl;

import java.io.File;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pzj.core.imgsrv.engine.properties.ImgPropertiesLoader;
import com.pzj.core.imgsrv.model.ImageEntity;
import com.pzj.core.imgsrv.utils.ImgConverterUtil;
import com.pzj.core.oldimgsrv.dao.SqliteImageDao;
import com.pzj.core.oldimgsrv.service.OldImageService;
import com.pzj.core.oldimgsrv.utils.DateUtils;
import com.pzj.core.oldimgsrv.utils.ImageUtils;
import com.pzj.framework.toolkit.Check;

@Service
public class OldImageServiceImpl implements OldImageService {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SqliteImageDao sqliteImageDao ;


	
	public String getImagePath(Boolean isNeedPNG,ImageEntity entity){
		String imgBasepath = ImgPropertiesLoader.getImgPath();
		if(!imgBasepath.endsWith("/")){
        	imgBasepath = imgBasepath +"/";
        }
		String path = imgBasepath+entity.getPath() + "/" + entity.getName()+"."+ entity.getExtendsion();
		if (!new File(path).exists()) {
			return null;
		}
		
		if(isNeedPNG){		
			String targetPath =  imgBasepath+entity.getPath() + "/" + entity.getName()+"."+"png";
			ImgConverterUtil.INTANCE.convertPNG(path,targetPath);
			path = targetPath;
			
		}
		return path;

		
	}

	public String selectImagePath(String string)  {

		String[] data = string.split("/");
        String imageSize = ImgPropertiesLoader.getImgSize();
        String imgBasepath = ImgPropertiesLoader.getImgPath();
		if (data.length == 2) {
			if (!imageSize.contains("," + data[1] + ",")) {
				data = new String[] { data[0] };
			}
		}

		String size = data.length == 2 ? "_" + data[1] + "." : ".";

		String sql = "select * from tbl_image where md5name = '" + data[0] + "'";

		List<Map<String, Object>> maps = this.sqliteImageDao.select(sql);

		if ((null == maps) || (maps.size() < 1)) {
			return "";
		}

		Map<String, Object> map = maps.get(0);
		String path = map.get("path") + "/" + map.get("name") + size + map.get("extension");

		if (!new File(imgBasepath + "/" + path).exists()) {
		    return null;
		}
		// return basePath + "/" + path;
		return imgBasepath + path;
	}

	/**
	 * 分页方法，分页SQL 按时间降序排序，即最新上传的图片显示在上面
	 * 
	 * @param pageNum
	 *            当前第几页
	 * @param rowNum
	 *            每页显示几行
	 * @return
	 */
	public List<ImageEntity> getPage(int pageNum, int rowNum, String status) {
		StringBuilder sr = new StringBuilder();
		int a = pageNum * rowNum - rowNum;
		if ("0".equals(status) || "1".equals(status)) {
			sr.append("select id, createTime,md5name,name,status from tbl_image ");
			sr.append(" where status='" + status + "' order by id desc limit '" + a + "','" + rowNum + "'");
		} else {
			sr.append("select id, createTime,md5name,name,status from tbl_image order by id desc limit '" + a + "','"
					+ rowNum + "'");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("SELECT:" + sr);
		}

		return this.sqliteImageDao.getPage(sr.toString());
	}

	/**
	 * 获取全部数据
	 * 
	 * @return
	 */
	public List<ImageEntity> getAll() {
		String sql = "select id, createTime from tbl_image ";

		if (logger.isDebugEnabled()) {
			logger.debug("SELECT " + sql);
		}

		return this.sqliteImageDao.getAll(sql);
	}
	
	 public List<ImageEntity> getImgsByNum(int imgNum){
			String sql = "select id, createTime,md5name,name,path,status,extension  from tbl_image "
				      + " where status <> 3 or status is null"
	                  + " order by id desc limit '0','"+imgNum+"'";
 
			if (logger.isDebugEnabled()) {
				logger.debug("SELECT " + sql);
			}

			return this.sqliteImageDao.getPage(sql); 
	 }

	/**
	 * 图片状态修改为3，代表没有更新到新图片服务上，路径处理有处理
	 * 
	 * @param basePath
	 */
	public void upadteStatus(String md5name) {

		StringBuilder sr = new StringBuilder();
		sr.append(" update tbl_image set status = 3 ");
		sr.append(" where md5name = ");
		sr.append("'");
		sr.append(md5name);
		sr.append("'");

		if (logger.isDebugEnabled()) {
			logger.debug("UPDATE " + sr);
		}

		this.sqliteImageDao.verify(sr.toString());
	}

	/**
	 * 执行删除某条记录
	 * 
	 * @param basePath
	 */
	public void del(String md5name) {

		StringBuilder sr = new StringBuilder();
		sr.append(" delete from tbl_image ");
		sr.append(" where md5name = ");
		sr.append("'");
		sr.append(md5name);
		sr.append("'");

		if (logger.isDebugEnabled()) {
			logger.debug("DELETE " + sr);
		}

		this.sqliteImageDao.del(sr.toString());

	}

}
