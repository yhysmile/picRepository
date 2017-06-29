package com.pzj.core.oldimgsrv.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pzj.core.imgsrv.engine.properties.ImgPropertiesLoader;
import com.pzj.core.imgsrv.model.ImageEntity;
import com.pzj.core.oldimgsrv.utils.DButils;

@Component
public class SqliteImageDao {
	public static Logger logger = LoggerFactory.getLogger(SqliteImageDao.class);

	private String dbPath = ImgPropertiesLoader.getDbpath();


	@SuppressWarnings("finally")
	public int update(String sql) {
		logger.debug("update : " + sql);
		Connection conn = DButils.getConnection(dbPath);
		Statement stat = null;
		int ex = 0;
		try {
			stat = conn.createStatement();
			ex = stat.executeUpdate(sql);
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stat = null;
			conn = null;
			return ex;
		}
	}

	@SuppressWarnings("finally")
	public List<Map<String, Object>> select(String sql) {
		logger.debug("select : " + sql);
		Connection conn = DButils.getConnection(dbPath);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Statement stat = null;
		ResultSet rs = null;	
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					map.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
			}
			rs.close();
			stat.close();	
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
			stat = null;
			conn = null;
			return list;
		}
	}

	/**
	 * 分页的方法，执行分页SQL
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("finally")
	public List<ImageEntity> getPage(String sql) {
		logger.debug("select : " + sql);
		Connection conn = DButils.getConnection(dbPath);
		List<ImageEntity> list = new ArrayList<ImageEntity>();
		Statement stat = null;
		ResultSet rs = null;	
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				ImageEntity imageEntity = new ImageEntity();
				imageEntity.setId(rs.getInt("id"));
				imageEntity.setCreateTime(rs.getString("createTime"));
				imageEntity.setMd5name(rs.getString("md5name"));
				imageEntity.setName(rs.getString("name"));
				imageEntity.setStatus(rs.getInt("status"));
				imageEntity.setPath(rs.getString("path"));
				imageEntity.setExtendsion(rs.getString("extension"));
				list.add(imageEntity);
			}
			rs.close();
			stat.close();	
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
			stat = null;
			conn = null;
			return list;
		}
	}

	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/goujinbo/Desktop/image.db");
			Statement stat = conn.createStatement();
			stat.executeUpdate(
					"create table tbl_image(id integer PRIMARY KEY,md5name varchar(50), name varchar(50),extension varchar(10) ,path varchar(200),status int );");
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	/**
	 * 得到所有记录返回list(pageHelper中用到)
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("finally")
	public List<ImageEntity> getAll(String sql) {
		logger.debug("select : " + sql);
		Connection conn = DButils.getConnection(dbPath);
		
		List<ImageEntity> list = new ArrayList<ImageEntity>();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				ImageEntity imageEntity = new ImageEntity();
				imageEntity.setId(rs.getInt("id"));
				imageEntity.setCreateTime(rs.getString("createTime"));
				list.add(imageEntity);
			}
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
			stat = null;
			conn = null;
			return list;
		}
	}

	/**
	 * 更改图片审核状态
	 * 
	 * @param sql
	 */
	public void verify(String sql) {
		logger.debug("update : " + sql);
		Connection conn = DButils.getConnection(dbPath);
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			stat = null;
			conn = null;
		}
	}

	/**
	 * 删除记录
	 * 
	 * @param sql
	 */
	public void del(String sql) {
		logger.debug("delete : " + sql);
		Connection conn = DButils.getConnection(dbPath);
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.execute(sql);
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			stat = null;
			conn = null;
		}

	}
}