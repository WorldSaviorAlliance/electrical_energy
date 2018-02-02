package com.warrior.eem.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.warrior.eem.entity.ui.Direct;

public class ToolUtil {
	public static String getBasePath(HttpServletRequest request)
	{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		return basePath;
	}
	
	public static String getProjectPath()
	{
		return "/eem";
	}
	
	public static String getProjectName()
	{
		return "售电业务管理平台";
	}
	
	public static ModelAndView gotoDirect(Map<String, Object> model, String name, String direct)
	{
		Direct d = new Direct(name, direct);
		model.put(Constant.DIRECT, d.toJson());
		String redirectPage = "template";
		return new ModelAndView(redirectPage, model);
	}
	
	public static boolean contain(String src, String des)
	{
		return src.toLowerCase().contains(des.toLowerCase());
	}
}
