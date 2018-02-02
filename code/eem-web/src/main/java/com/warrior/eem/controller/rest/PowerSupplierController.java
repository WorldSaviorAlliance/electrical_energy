package com.warrior.eem.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.DemoDo;
import com.warrior.eem.entity.vo.PowerCustomerOrSupplierCdtVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.PowerSupplierService;

/**
 * power supperlier controller
 * @author seangan
 *
 */
@Controller
@RequestMapping("power_supperlier")
public class PowerSupplierController extends AbstractController {
	
	@Autowired
	private PowerSupplierService pssService;
	
	@RequestMapping(value = "info", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> createDemo(@RequestBody DemoDo demoVo) {
		throw new EemException("测试");
//		dService.createEntity(demoVo);
//		return Result.success();
	}
	
	@RequestMapping(value = "info", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> modifyDemo(@RequestBody DemoDo demoVo) {
		return Result.success();
	}
	
	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteDemo(String id) {
		return Result.success();
	}
	
	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public Result<DemoDo> getDemo(String id) {
		return Result.success((DemoDo)pssService.getEntity(Long.valueOf(id)));
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> listDemos(@RequestBody PowerCustomerOrSupplierCdtVo cdt,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "per_page", required = false) String perPage) {
		Integer pageNum = 1;
		if (page != null && page.trim().length() == 0) {
			try {
				pageNum = Integer.valueOf(page);
			} catch(NumberFormatException e) {
				throw new EemException("页码必须为数字");
			}
		}

		Integer perPageNum = 20;
		if (perPage != null && perPage.trim().length() == 0) {
			try {
				perPageNum = Integer.valueOf(perPage);
			} catch(NumberFormatException e) {
				throw new EemException("每页显示的个数参数必须为数字");
			}
		}

		return Result.success(pssService.listEntities(cdt, pageNum, perPageNum));
	}
}
