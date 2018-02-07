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
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.entity.vo.PowerDataCdtVo;
import com.warrior.eem.entity.vo.PowerDataUpdateVo;
import com.warrior.eem.entity.vo.PowerDataVo;
import com.warrior.eem.exception.EemException;
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

	@Autowired
	private PowerDataService pdService;

	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody(required = false) PowerDataVo vo) {
		pdService.createEntity(vo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestBody(required = false) PowerDataUpdateVo updateVo) {
		pdService.updateEntity(updateVo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteEntity(String id) {
		pdService.deleteEntity(convertId(id));
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<PowerCustomer> getEntity(String id) {
		return Result.success((PowerCustomer) pdService.getEntity(convertId(id)));
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listEntities(@RequestBody(required = false) PowerDataCdtVo cdt,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "per_page", required = false) String perPage,
			@RequestParam(name = "sort_by", required = false) String sortBy,
			@RequestParam(name = "order", required = false) String order) {
		Integer pageNum = 1;
		if (page != null && page.trim().length() == 0) {
			try {
				pageNum = Integer.valueOf(page);
			} catch (NumberFormatException e) {
				throw new EemException("页码必须为数字");
			}
		}

		Integer perPageNum = 20;
		if (perPage != null && perPage.trim().length() == 0) {
			try {
				perPageNum = Integer.valueOf(perPage);
			} catch (NumberFormatException e) {
				throw new EemException("每页显示的个数参数必须为数字");
			}
		}
		String sortProp = "month";
		String orderProp = "DESC";
		if(sortBy != null && sortBy.trim().length() > 0) {
			sortProp = sortBy;
		}
		if(Order.ASC.equalsIgnoreCase(order)) {
			orderProp = "ASC";
		}
		PageVo vo = pdService.listEntities(cdt, pageNum, perPageNum, sortProp, orderProp);
		return Result.success(vo.getCount(), vo.getDatas());
	}

}
