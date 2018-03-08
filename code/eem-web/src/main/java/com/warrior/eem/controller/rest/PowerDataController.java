package com.warrior.eem.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.PowerData;
import com.warrior.eem.entity.constant.ResourceOperation;
import com.warrior.eem.entity.vo.ContractAndPracticalReqVo;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.entity.vo.PowerDataCdtVo;
import com.warrior.eem.entity.vo.PowerDataVo;
import com.warrior.eem.entity.vo.PowerMonthPriceReqVo;
import com.warrior.eem.service.PowerDataService;

/**
 * 电量管理controller
 * 
 * @author seangan
 *
 */
@Controller
@RequestMapping("power_data")
public class PowerDataController extends AbstractController {
	private static final String RES_NAME = PowerData.class.getSimpleName();
	
	@Autowired
	private PowerDataService pdService;

	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody(required = false) PowerDataVo vo) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, null);
		pdService.createEntity(vo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestBody(required = false) PowerDataVo vo) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, vo.getId());
		pdService.updateEntity(vo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteEntity(String id) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, convertId(id));
		pdService.deleteEntity(convertId(id));
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<PowerCustomer> getEntity(String id) {
		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		return Result.success((PowerCustomer) pdService.getEntity(convertId(id)));
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listEntities(@RequestBody(required = false) PowerDataCdtVo cdt,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "per_page", required = false) String perPage,
			@RequestParam(name = "sort_by", required = false) String sortBy,
			@RequestParam(name = "order", required = false) String order) {
		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		Integer[] pageInfo = buildPageInfo(page, perPage);
		String sortProp = "month";
		String orderProp = "DESC";
		if (sortBy != null && sortBy.trim().length() > 0) {
			sortProp = sortBy;
		}
		if (Order.ASC.equalsIgnoreCase(order)) {
			orderProp = "ASC";
		}
		PageVo vo = pdService.listEntities(cdt, pageInfo[0], pageInfo[1], sortProp, orderProp);
		return Result.success(vo.getCount(), vo.getDatas());
	}

	/**
	 * 统计年/月 实际预测偏差列表数据
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "statis_list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> statisContractAndpracticalData(
			@RequestBody(required = false) ContractAndPracticalReqVo param) {
//		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		PageVo pv = pdService.listContractAndpracticalData(param);
		return Result.success(pv.getCount(), pv.getDatas());
	}

	/**
	 * 月度清算列表数据
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "price_list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listPowerMonthPriceData(
			@RequestParam(name = "order", defaultValue = "DESC", required = false) String order,
			@RequestBody(required = false) PowerMonthPriceReqVo param) {
//		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		PageVo pv = pdService.listPowerMonthPriceData(param, order);
		return Result.success(pv.getCount(), pv.getDatas());
	}

}
