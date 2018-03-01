package com.warrior.eem.listener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.warrior.eem.service.AuthorityService;
import com.warrior.eem.service.ElectricityPackageService;
import com.warrior.eem.service.PriceCoefficientService;
import com.warrior.eem.service.RoleService;
import com.warrior.eem.service.UserService;

@Component
public class ApplicationInitializedListener implements ApplicationListener<ContextRefreshedEvent> {
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private ElectricityPackageService elecPkgService;

	@Autowired
	private PriceCoefficientService priceCoefService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		if (e.getApplicationContext().getParent() == null) {
			if (!userService.createAdminIfAbsent()) {// 默认新增一个admin用户
				logger.info("Failed to insert admin user.");
			}
			if (!authorityService.initDefaultDataIfAbsent()) {// 初始化权限表
				logger.info("Failed to initialize authority table.");
			}
			if (!roleService.initAdminRole()) {// 初始化管理员角色
				logger.info("Failed to initialize admin role.");
			}
			if (!elecPkgService.initDefaultDataIfAbsent()) {// 初始化套餐
				logger.info("Failed to initialize electricity packages.");
			}
			if (!priceCoefService.initDefaultDataIfAbsent()) {// 初始化电价系数
				logger.info("Failed to initialize price coefficient.");
			}
		}
	}
}
