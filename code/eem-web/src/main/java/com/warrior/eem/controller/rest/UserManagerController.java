package com.warrior.eem.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.constant.ResourceOperation;
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
		checkPerimisession(ResourceOperation.WRITE, null);
		service.createEntity(userVo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestParam Long userId, @RequestParam String newName) {
		checkPerimisession(ResourceOperation.WRITE, userId);
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
		checkPerimisession(ResourceOperation.WRITE, id);
		service.deleteEntity(id);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getEntity(@RequestParam Long id) {
		checkPerimisession(ResourceOperation.READ, id);
		UserVo userVo = (UserVo) service.getEntity(id);
		return Result.success(userVo);
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listEntities(@RequestBody(required = false) UserCdtVo cdt) {
		checkPerimisession(ResourceOperation.READ, null);
		PageVo pageVo = service.listEntities(cdt);
		return Result.success(pageVo.getCount(), pageVo.getDatas());
	}

	@RequestMapping(value = "set_role", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> setRole(@RequestParam Long userId, @RequestParam Long roleId) {
		checkPerimisession(ResourceOperation.WRITE, userId);
		service.setRole(userId, roleId);
		return Result.success();
	}

	@RequestMapping(value = "modify_pwd", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> modifyPassword(@RequestParam Long userId, @RequestParam String oldPwd,
			@RequestParam String newPwd) {
		checkPerimisession(ResourceOperation.WRITE, userId);
		service.modifyPassword(userId, oldPwd, newPwd);
		return Result.success();
	}

	private void checkPerimisession(ResourceOperation op, Long id) {
		//EemSession.checkPermission(User.class.getSimpleName(), op, id);TODO:懒加载异常
	}
}
