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
import com.warrior.eem.entity.constant.ResourceOperation;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.entity.vo.PowerCustomerOrSupplierCdtVo;
import com.warrior.eem.entity.vo.PowerCustomerUpdaterVo;
import com.warrior.eem.entity.vo.PowerCustomerVo;
import com.warrior.eem.service.PowerCustomerService;

/**
 * 电力客户 controller
 * 
 * @author seangan
 *
 */
@Controller
@RequestMapping("power_customer")
public class PowerCustomerController extends AbstractController {
	private static final String RES_NAME = PowerCustomer.class.getSimpleName();

	@Autowired
	private PowerCustomerService pcsService;

	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createEntity(@RequestBody(required = false) PowerCustomerVo powerCustomerVo) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, null);
		pcsService.createEntity(powerCustomerVo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> updateEntity(@RequestBody(required = false) PowerCustomerUpdaterVo powerCustomerVo) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, powerCustomerVo.getId());
		pcsService.updateEntity(powerCustomerVo);
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteEntity(String id) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, convertId(id));
		pcsService.deleteEntity(convertId(id));
		return Result.success();
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<PowerCustomer> getEntity(String id) {
		checkPerimisession(RES_NAME, ResourceOperation.READ, convertId(id));
		return Result.success((PowerCustomer) pcsService.getEntity(convertId(id)));
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listEntities(@RequestBody(required = false) PowerCustomerOrSupplierCdtVo cdt,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "per_page", required = false) String perPage) {
		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		Integer[] pageInfo = buildPageInfo(page, perPage);
		PageVo vo = pcsService.listEntities(cdt, pageInfo[0], pageInfo[1]);
		return Result.success(vo.getCount(), vo.getDatas());
	}

}
