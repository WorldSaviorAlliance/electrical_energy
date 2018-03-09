package com.warrior.eem.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.vo.ProvinceVo;
import com.warrior.eem.service.LocationService;

/**
 * 地理位置控制器
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Controller
@RequestMapping("location")
public class LocationController extends AbstractController {

	@Autowired
	private LocationService service;

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public @ResponseBody Result<Object> getEntity(@RequestParam Long id) {
		ProvinceVo vo = service.getEntityVo(id);
		return Result.success(vo);
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody Result<Object> list() {
		List<ProvinceVo> vos = service.queryAll();
		return Result.success(vos.size(), vos);
	}
}
