package com.warrior.eem.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.ElectricityPackage;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.constant.ResourceOperation;
import com.warrior.eem.entity.vo.ElectricityPackageVo;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.service.ElectricityPackageService;
import com.warrior.eem.service.UserService;
import com.warrior.eem.shiro.session.EemSession;

/**
 * 套餐控制器
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Controller
@RequestMapping("electricity_package")
public class ElectricityPackageController extends AbstractController {
	private static final String RES_NAME = ElectricityPackage.class.getSimpleName();
	
	@Autowired
	private ElectricityPackageService service;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public @ResponseBody Result<Object> getEntity(@RequestParam Long pkgId) {
		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		ElectricityPackageVo vo = (ElectricityPackageVo) service.getEntity(pkgId);
		User user = EemSession.getCurrentUser();
		if (null != user) {
			vo.setHandled(userService.containsElectricityPackage(user.getId(), pkgId));
		}
		return Result.success(vo);
	}

	/**
	 * 获取所有套餐或指定用户已办理套餐列表
	 * 
	 * @param userId
	 *            -1表示获取所有套餐,否则为获取指定用户已办理套餐
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody Result<Object> list(@RequestParam Long userId) {
		checkPerimisession(RES_NAME, ResourceOperation.READ, null);
		PageVo pageVo;
		if (-1 == userId) {
			pageVo = service.listEntities();
		} else {
			List<ElectricityPackageVo> vos = userService.getElectricityPackages(userId);
			pageVo = new PageVo();
			pageVo.setCount(Long.valueOf(vos.size()));
			pageVo.setDatas(vos);
		}
		return Result.success(pageVo.getCount(), pageVo.getDatas());
	}

	@RequestMapping(value = "handle_elec_pkg", method = RequestMethod.POST)
	public @ResponseBody Result<Object> handlePackage(@RequestParam Long pkgId) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, pkgId);
		User user = EemSession.getCurrentUser();
		if (null == user) {
			return Result.failure("Session expired");
		}
		userService.handleElectricityPackage(user.getId(), pkgId);
		return Result.success();
	}

	@RequestMapping(value = "cancel_elec_pkg", method = RequestMethod.POST)
	public @ResponseBody Result<Object> cancelPackage(@RequestParam Long pkgId) {
		checkPerimisession(RES_NAME, ResourceOperation.WRITE, pkgId);
		User user = EemSession.getCurrentUser();
		if (null == user) {
			return Result.failure("Session expired");
		}
		userService.cancelElectricityPackage(user.getId(), pkgId);
		return Result.success();
	}
}
