package com.warrior.eem.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.vo.UserCdtVo;
import com.warrior.eem.entity.vo.UserVo;
import com.warrior.eem.service.UserService;

/**
 * 用户管理
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public class UserManagerController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody UserVo userVo) {
		userService.createEntity(userVo);
		return Result.success();
	}

	@RequestMapping(value = "update", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestBody UserVo userVo) {
		userService.updateEntity(userVo);
		return Result.success();
	}

	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteEntity(String id) {
		userService.deleteEntity(convertId(id));
		return Result.success();
	}

	@RequestMapping(value = "query", method = RequestMethod.GET)
	@ResponseBody
	public Result<User> getEntity(String id) {
		return Result.success((User) userService.getEntity(convertId(id)));
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listEntities(@RequestBody UserCdtVo cdt) {
		return Result.success(userService.listEntities(cdt));
	}
}
