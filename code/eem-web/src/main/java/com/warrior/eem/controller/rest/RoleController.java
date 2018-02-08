package com.warrior.eem.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.vo.RoleCdtVo;
import com.warrior.eem.entity.vo.RoleVo;

/**
 * 角色管理
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Controller
@RequestMapping("role")
public final class RoleController extends AbstractController {

	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody(required = false) RoleVo vo) {
		// TODO:
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestBody(required = false) RoleVo vo) {
		// TODO:
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteEntity(long id) {
		// TODO:
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getEntity(long id) {
		// TODO:
		return Result.success();
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> list(@RequestBody(required = false) RoleCdtVo cdt) {
		return Result.success();
	}
}
