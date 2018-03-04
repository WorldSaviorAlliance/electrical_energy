package com.warrior.eem.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.entity.vo.UserCdtVo;
import com.warrior.eem.entity.vo.UserVo;
import com.warrior.eem.service.UserService;
import com.warrior.eem.shiro.session.EemSession;

/**
 * 用户管理
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Controller
@RequestMapping("user")
public class UserManagerController extends AbstractController {

	@Autowired
	private UserService service;

	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody(required = false) UserVo userVo) {
		service.createEntity(userVo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestParam Long userId, @RequestParam String newName) {
		User user = service.modifyName(userId, newName);
		User curUser = EemSession.getCurrentUser();
		if (null != curUser && curUser.getId() == user.getId()) {
			EemSession.setCurrentUser(user);// 更新当前session中的用户信息
		}
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteEntity(@RequestParam Long id) {
		service.deleteEntity(id);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getEntity(@RequestParam Long id) {
		User user = (User) service.getEntity(id);
		if (null == user) {
			return Result.failure("获取用户信息失败");
		}
		return Result.success(user.convert());
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listEntities(@RequestBody(required = false) UserCdtVo cdt) {
		PageVo pageVo = service.listEntities(cdt);
		List<UserVo> vos = new ArrayList<UserVo>();
		for (Object obj : pageVo.getDatas()) {
			vos.add(((User) obj).convert());
		}
		return Result.success(pageVo.getCount(), vos);
	}

	@RequestMapping(value = "set_role", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> setRole(@RequestParam Long userId, @RequestParam Long roleId) {
		service.setRole(userId, roleId);
		return Result.success();
	}

	@RequestMapping(value = "modify_pwd", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> modifyPassword(@RequestParam Long userId, @RequestParam String oldPwd,
			@RequestParam String newPwd) {
		service.modifyPassword(userId, oldPwd, newPwd);
		return Result.success();
	}
}
