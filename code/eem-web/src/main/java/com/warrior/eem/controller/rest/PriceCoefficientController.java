package com.warrior.eem.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.PriceCoefficient;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.entity.vo.PriceCoefficientCdtVo;
import com.warrior.eem.entity.vo.PriceCoefficientVo;
import com.warrior.eem.service.PriceCoefficientService;

/**
 * 电价系数控制器
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Controller
@RequestMapping("price_coefficient")
public class PriceCoefficientController extends AbstractController {

	@Autowired
	private PriceCoefficientService service;

	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody(required = false) PriceCoefficientVo vo) {
		service.createEntity(vo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestBody(required = false) PriceCoefficientVo vo) {
		service.updateEntity(vo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteEntity(long id) {
		service.deleteEntity(id);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<PriceCoefficient> getEntity(long id) {
		return Result.success((PriceCoefficient) service.getEntity(id));
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> list(@RequestBody(required = false) PriceCoefficientCdtVo cdt) {
		PageVo pageVo = service.listEntities(cdt);
		return Result.success(pageVo.getCount(), pageVo.getDatas());
	}
}
