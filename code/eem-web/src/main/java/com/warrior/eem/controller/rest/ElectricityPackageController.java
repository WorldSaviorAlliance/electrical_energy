package com.warrior.eem.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.warrior.eem.common.Result;
import com.warrior.eem.entity.ElectricityPackage;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.vo.ElectricityPackageVo;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.service.ElectricityPackageService;
import com.warrior.eem.service.UserService;

/**
 * 套餐控制器
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Controller
@RequestMapping("electricity_package")
public class ElectricityPackageController extends AbstractController {
	@Autowired
	private ElectricityPackageService service;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public @ResponseBody Result<Object> getEntity(long pkgId, long userId) {
		ElectricityPackage pkg = (ElectricityPackage) service.getEntity(pkgId);
		if (null == pkg) {
			return Result.failure("获取套餐失败");
		}
		User user = (User) userService.getEntity(userId);
		if (null == user) {
			return Result.failure("Session过期");
		}
		ElectricityPackageVo vo = pkg.convert();
		vo.setHandled(user.containsElectricityPackage(pkg.getId()));
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
	public @ResponseBody Result<Object> list(long userId) {
		List<ElectricityPackageVo> vos = new ArrayList<>();
		if (-1 == userId) {
			PageVo pageVo = service.listEntities();
			for (Object elem : pageVo.getDatas()) {
				vos.add(((ElectricityPackage) elem).convert());
			}
		} else {
			User user = (User) userService.getEntity(userId);
			if (null == user) {
				return Result.failure("未找到指定用户");
			}
			for (ElectricityPackage elem : user.getElectricityPackages()) {
				vos.add(elem.convert());
			}
		}
		return Result.success(vos.size(), vos);
	}

	@RequestMapping(value = "handle_elec_pkg", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> handlePackage(long pkgId, long userId) {
		if (service.handleElectricityPackage(pkgId, userId)) {
			return Result.success();
		}
		return Result.failure("办理套餐失败");
	}

	@RequestMapping(value = "cancel_elec_pkg", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> cancelPackage(long pkgId, long userId) {
		if (service.cancelElectricityPackage(pkgId, userId)) {
			return Result.success();
		}
		return Result.failure("取消套餐失败");
	}
}
