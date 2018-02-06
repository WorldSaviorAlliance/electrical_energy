package com.warrior.eem.controller.rest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.vo.LogonVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.util.EntityValidator;

/**
 * 登录controller模块
 * 
 * @author seangan
 *
 */
@Controller
public class LogonController extends AbstractController {

	@RequestMapping(value = "logon", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> logon(@RequestBody(required = false) LogonVo vo) {
		try {
			EntityValidator.checkEntity(vo);
		} catch (IllegalAccessException | SecurityException e) {
			throw new EemException("解析登录信息失败，请联系管理员");
		}
		SecurityUtils.getSubject().login(new UsernamePasswordToken(vo.getUserName(), vo.getPassword()));
		// TODO 用户信息给页面
		return Result.success();
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> logout() {
		SecurityUtils.getSubject().logout();
		return Result.success();
	}
}
