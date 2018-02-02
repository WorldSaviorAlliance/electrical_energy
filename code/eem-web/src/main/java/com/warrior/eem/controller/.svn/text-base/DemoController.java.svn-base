package com.warrior.eem.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.vo.DemoVo;
import com.warrior.eem.service.DemoService;

/**
 * demo test controller
 * @author seangan
 *
 */
@Controller
@RequestMapping("demo")
public class DemoController extends AbstractController {
	
	@Autowired
	private DemoService dService;
	
	@RequiresPermissions("demo:create")
	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createDemo() {
		dService.createDemo();
		return Result.success();
	}
	
	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> modifyDemo() {
		return Result.success();
	}
	
	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteDemo() {
		return Result.success();
	}
	
	@RequiresPermissions("demo:get1")
	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<DemoVo> getDemo(String id) {
		return Result.success(dService.getDemoVo(Long.valueOf(id)));
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> listDemos() {
		return Result.success(dService.listDemoVo());
	}
	
	@RequestMapping(value = "login_launcher", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> login() {
		try {
			SecurityUtils.getSubject().login(new UsernamePasswordToken("ganbin", "ganbin"));
		} catch(AuthenticationException e) {
			return Result.failure("用户名或密码错误");
		}
		return Result.success();
	}
}
