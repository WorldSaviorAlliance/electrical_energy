package com.warrior.eem.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.vo.PowerCustomerOrSupplierCdtVo;
import com.warrior.eem.entity.vo.PowerCustomerUpdaterVo;
import com.warrior.eem.entity.vo.PowerCustomerVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.PowerCustomerService;

/**
 * demo test controller
 * 
 * @author seangan
 *
 */
@Controller
@RequestMapping("power_customer")
public class PowerCustomerController extends AbstractController {

	@Autowired
	private PowerCustomerService pcsService;

	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody PowerCustomerVo powerCustomerVo) {
		pcsService.createEntity(powerCustomerVo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestBody PowerCustomerUpdaterVo powerCustomerVo) {
		pcsService.updateEntity(powerCustomerVo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteEntity(String id) {
		pcsService.deleteEntity(convertId(id));
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<PowerCustomer> getEntity(String id) {
		return Result.success((PowerCustomer) pcsService.getEntity(convertId(id)));
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listEntities(@RequestBody(required = false) PowerCustomerOrSupplierCdtVo cdt,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "per_page", required = false) String perPage) {
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

		return Result.success(pcsService.listEntities(cdt, pageNum, perPageNum));
	}

}
