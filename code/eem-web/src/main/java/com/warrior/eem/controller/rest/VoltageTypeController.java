package com.warrior.eem.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.VoltageType;
import com.warrior.eem.entity.constant.ResourceOperation;
import com.warrior.eem.entity.vo.BaseTypeCdtVo;
import com.warrior.eem.entity.vo.BaseTypeVo;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.service.VoltageTypeService;

/**
 * 电压等级控制器
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Controller
@RequestMapping("voltage_type")
public class VoltageTypeController extends AbstractController {
	private static final String RES_NAME = VoltageType.class.getSimpleName();
	
	@Autowired
	private VoltageTypeService service;

	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody BaseTypeVo vo) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, null);
		service.createEntity(vo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestBody BaseTypeVo vo) {
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
	public Result<Object> list(@RequestBody(required = false) BaseTypeCdtVo cdt) {
		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		PageVo pageVo = service.listEntities(cdt);
		return Result.success(pageVo.getCount(), pageVo.getDatas());
	}
}
