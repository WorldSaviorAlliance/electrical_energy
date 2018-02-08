package com.warrior.eem.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	private UserService userService;

	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody(required = false) UserVo userVo) {
		userService.createEntity(userVo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestBody(required = false) UserVo userVo) {
		User user = userService.updateUser(userVo);
		User curUser = EemSession.getCurrentUser();
		if (null != curUser && curUser.getId() == user.getId()) {
			EemSession.setCurrentUser(user);// 更新当前session中的用户信息
		}
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteEntity(String id) {
		userService.deleteEntity(convertId(id));
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<User> getEntity(String id) {
		return Result.success((User) userService.getEntity(convertId(id)));
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listEntities(@RequestBody(required = false) UserCdtVo cdt) {
		PageVo pageVo = userService.listEntities(cdt);
		return Result.success(pageVo.getCount(), pageVo.getDatas());
	}
}
