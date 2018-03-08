package com.warrior.eem.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.Role;
import com.warrior.eem.entity.constant.ResourceOperation;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.entity.vo.RoleCdtVo;
import com.warrior.eem.entity.vo.RoleVo;
import com.warrior.eem.service.RoleService;

/**
 * 角色管理
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Controller
@RequestMapping("role")
public final class RoleController extends AbstractController {
	private static final String RES_NAME = Role.class.getSimpleName();
	
	@Autowired
	private RoleService service;

	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody(required = false) RoleVo vo) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, null);
		service.createEntity(vo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestBody(required = false) RoleVo vo) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, vo.getId());
		service.updateEntity(vo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteEntity(@RequestParam Long id) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, id);
		service.deleteEntity(id);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getEntity(@RequestParam Long id) {
		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		return Result.success(service.getEntity(id));
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> list(@RequestBody(required = false) RoleCdtVo cdt) {
		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		PageVo pageVo = service.listEntities(cdt);
		return Result.success(pageVo.getCount(), pageVo.getDatas());
	}

	@RequestMapping(value = "list_authority", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listAuthorities() {
		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		PageVo pageVo = service.listAuthorities();
		return Result.success(pageVo.getCount(), pageVo.getDatas());
	}
}
