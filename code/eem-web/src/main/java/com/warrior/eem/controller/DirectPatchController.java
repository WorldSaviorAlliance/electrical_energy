/**
 * 处理跳转页面的类
 */
package com.warrior.eem.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.warrior.eem.util.Constant;
import com.warrior.eem.util.ToolUtil;

@Controller
public class DirectPatchController {
	private final Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping("/login")
	public ModelAndView index()
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Hello every one!!!");
		}
		logger.debug("Hello every one!!!");
		logger.info("Hello every one!!!");
		String redirectPage = "login";
		return new ModelAndView(redirectPage);
	}
	
	@RequestMapping("/home")
	public ModelAndView home(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_HOME, Constant.DIR_HOME);
	}
	
	/**
	 * 电源商列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/dys")
	public ModelAndView dys(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_DYS, Constant.CUSTOMER + Constant.DIR_DYS);
	}
	
	/**
	 * 电力用户列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/dlyh")
	public ModelAndView dlyh(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_DLYH, Constant.CUSTOMER + Constant.DIR_DLYH);
	}
	
	/**
	 * 购电合约列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/gdhy")
	public ModelAndView gdhy(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_GDHY, Constant.CONTRACT + Constant.DIR_GDHY);
	}
	
	/**
	 * 售电电合约列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/sdhy")
	public ModelAndView sdhy(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_SDHY, Constant.CONTRACT + Constant.DIR_SDHY);
	}
	
	/**
	 * 电量调整列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/dltz")
	public ModelAndView dltz(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_DLTZ, Constant.CONTRACT + Constant.DIR_DLTZ);
	}
	
	/**
	 * 月结算电量列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/yjsdl")
	public ModelAndView yjsdl(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_YJSDL, Constant.ELECTRICITY + Constant.DIR_YJSDL);
	}
	
	/**
	 * 电量月度清算
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/dlydqs")
	public ModelAndView dlydqs(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_DLYDQS, Constant.ELECTRICITY + Constant.DIR_DLYDQS);
	}
	
	/**
	 * 月度电量偏差
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/yddlpc")
	public ModelAndView yddlpc(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_YDDLPC, Constant.ELECTRICITY + Constant.DIR_YDDLPC);
	}
	
	/**
	 * 年度电量偏差
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/nddlpc")
	public ModelAndView nddlpc(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_NDDLPC, Constant.ELECTRICITY + Constant.DIR_NDDLPC);
	}
	
	
	/**
	 * 电压等级
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/dydj")
	public ModelAndView dydj(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_DYDJ, Constant.SETTING + Constant.DIR_DYDJ);
	}
	
	/**
	 * 电价系数
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/djxs")
	public ModelAndView djxs(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_DJXS, Constant.SETTING + Constant.DIR_DJXS);
	}
	
	/**
	 * 用户管理
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/yhgl")
	public ModelAndView yhgl(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_YHGL, Constant.SETTING + Constant.DIR_YHGL);
	}
	
	/**
	 * 角色管理
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/jsgl")
	public ModelAndView jsgl(Map<String, Object> model, HttpServletRequest request)
	{
		return ToolUtil.gotoDirect(model, Constant.DIR_JSGL, Constant.SETTING + Constant.DIR_JSGL);
	}
	
	
}
