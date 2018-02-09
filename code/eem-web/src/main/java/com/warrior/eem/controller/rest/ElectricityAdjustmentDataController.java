package com.warrior.eem.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.vo.ElectricityAdjustmentDataCondition;
import com.warrior.eem.entity.vo.ElectricityAdjustmentDataUpdateVO;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.service.ElectricityAdjustmentDataService;

@Controller
@RequestMapping(value = "/adjustment_data")
public class ElectricityAdjustmentDataController extends AbstractController {

	@Autowired
	private ElectricityAdjustmentDataService adjustService;

	@ResponseBody
	@RequestMapping(value = "/agreement_adjustment_data", method = RequestMethod.POST)
	public Result<Object> saveAndUpdateElectricityAdjustmentData(ElectricityAdjustmentDataUpdateVO adjustmentDataVo) {
		adjustService.saveOrUpdateElectricityAdjustmentData(adjustmentDataVo);
		return Result.success();
	}

	@ResponseBody
	@RequestMapping(value = "agreement_adjustment_data", method = RequestMethod.DELETE)
	public Result<Object> deleteElectricityAdjustmentData(@RequestParam(name = "id") Long id) {
		adjustService.deleteEntity(id);
		return Result.success();
	}

	@ResponseBody
	@RequestMapping(value = "agreement_adjustment_datas", method = RequestMethod.POST)
	public Result<Object> listElectricityAdjustmentData(
			@RequestBody(required = false) ElectricityAdjustmentDataCondition condition,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "per_page", required = false) String perPage) {
		Integer[] pageObject = buildPageInfo(page, perPage);
		PageVo pageVo = adjustService.listEntities(condition, pageObject[0], pageObject[1]);
		return Result.success(pageVo.getCount(), pageVo.getDatas());
	}
}
