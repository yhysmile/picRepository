package com.pzj.core.oldimgsrv.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	static MessageDigest md;

	static {
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (Exception localException) {
		}
	}

	public static String digest(String msg) {
		byte[] rlt = md.digest(msg.getBytes());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rlt.length; i++) {
			String d = "00" + Integer.toHexString(rlt[i]);
			sb.append(d.substring(d.length() - 2));
		}
		return sb.toString();
	}

	public static String digest32(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/* 61 */byte[] byteArray = messageDigest.digest();

		/* 63 */StringBuffer md5StrBuff = new StringBuffer();

		/* 65 */for (int i = 0; i < byteArray.length; i++) {
			/* 66 */if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				/* 67 */md5StrBuff.append("0").append(
				/* 68 */Integer.toHexString(0xFF & byteArray[i]));
			else {
				/* 70 */md5StrBuff.append(Integer
						.toHexString(0xFF & byteArray[i]));
			}
		}
		/* 73 */return md5StrBuff.toString();
	}

	public static void main(String[] args) {
		System.out.println(digest("123456"));
	}
}
