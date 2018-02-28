package com.warrior.eem.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.warrior.eem.entity.ui.Direct;

public class ToolUtil {
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		return basePath;
	}

	public static String getProjectPath() {
		return "/eem";
	}

	public static String getProjectName() {
		return "售电业务管理平台";
	}

	public static ModelAndView gotoDirect(Map<String, Object> model, String name, String direct) {
		Direct d = new Direct(name, direct);
		model.put(Constant.DIRECT, d.toJson());
		String redirectPage = "template";
		return new ModelAndView(redirectPage, model);
	}

	public static boolean contain(String src, String des) {
		return src.toLowerCase().contains(des.toLowerCase());
	}

	/**
	 * 获取系统的当前时间,以毫秒为单位
	 * 
	 * @author cold_blade
	 * @return 当前的系统时间
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 判断当前字串是否为空
	 * 
	 * @author cold_blade
	 * @param str
	 * @return null或""返回true,否则返回false
	 */
	public static boolean isStringEmpty(String str) {
		return null == str ? true : str.isEmpty();
	}

	/**
	 * 格式化日期("yyyy-MM-dd HH:mm:ss")
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
}
