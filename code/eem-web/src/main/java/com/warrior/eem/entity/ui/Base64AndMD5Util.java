package com.warrior.eem.entity.ui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * MD5加密工具
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class Base64AndMD5Util {
	/**
	 * 利用MD5进行加密
	 * 
	 * @param str
	 *            待加密的字符串
	 * @return 加密后的字符串
	 */
	public static String encodeByBase64AndMd5(String str) {
		// 确定计算方法
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			byte[] b64 = Base64.getEncoder().encode(md5.digest());
			return new String(b64); // 加密后的字符串
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
}
