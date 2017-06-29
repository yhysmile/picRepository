package com.pzj.core.oldimgsrv.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pzj.core.imgsrv.exception.ImgException;
import com.pzj.core.imgsrv.exception.ImgExceptionCode;

/**
 * 链接数据库公共类
 * 
 * @author Administrator
 *
 */
public class DButils {

	static {

		try {// 加载sqlite驱动
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ImgException(ImgExceptionCode.IMG_SYSTEM_EXCEPTION, "加载org.sqlite.JDBC失败");
		}

	}

	/**
	 * 得到Connection对象
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(String imgPath) {
		try {

			return DriverManager.getConnection("jdbc:sqlite:" + imgPath);		

		} catch (Exception e) {
			e.printStackTrace();
			throw new ImgException(ImgExceptionCode.IMG_SYSTEM_EXCEPTION, "连接image.db数据库失败，地址:" + imgPath, e);
		}
	}

}
