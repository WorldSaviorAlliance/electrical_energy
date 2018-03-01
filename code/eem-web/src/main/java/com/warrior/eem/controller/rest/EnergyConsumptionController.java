package com.warrior.eem.controller.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.service.PowerDataService;

/**
 * 能耗管理相关统计controller
 * 
 * @author seangan
 * @version 1.0.0
 */
@Controller
@RequestMapping("energy_consumpt")
public class EnergyConsumptionController extends AbstractController {

	@Autowired
	private PowerDataService pdService;

	/**
	 * 统计当前月份的电量 电费信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "cmpq_statistics", method = RequestMethod.GET)
	@ResponseBody
	public Result<Map<String, BigDecimal>> statisticsCurrentMonthPowerInfo() {
		return Result.success(pdService.statisticsCurrentMonthPowerInfo());
	}

	/**
	 * 统计年度电量
	 * 
	 * @return
	 */
	@RequestMapping(value = "ypq_statistics", method = RequestMethod.GET)
	@ResponseBody
	public Result<Map<String, BigDecimal>> statisticsYearPowerQuantity() {
		return Result.success(pdService.statisticsYearPowerQuantity());
	}

	/**
	 * 统计年度电费
	 * 
	 * @return
	 */
	@RequestMapping(value = "ypp_statistics", method = RequestMethod.GET)
	@ResponseBody
	public Result<Map<String, BigDecimal>> statisticsYearPowerPrice() {
		return Result.success(pdService.statisticsYearPowerPrice());
	}

	/**
	 * 按月统计月度电量列表信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "mpq_list", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<Map<String, Object>>> listMonthPowerQuantity() {
		return Result.success(pdService.listMonthPowerQuantity());
	}
}
