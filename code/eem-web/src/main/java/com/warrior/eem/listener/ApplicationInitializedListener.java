package com.warrior.eem.listener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.warrior.eem.service.AuthorityService;
import com.warrior.eem.service.UserService;

@Component
public class ApplicationInitializedListener implements ApplicationListener<ContextRefreshedEvent> {
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		if (e.getApplicationContext().getParent() == null) {
			if (!userService.createAdminIfAbsent()) {// 默认新增一个admin用户
				logger.info("Failed to insert admin user.");
			}
			if (!authorityService.initDefaultDataIfAbsent()) {// 初始化权限表
				logger.info("Failed to initialize authority table.");
			}
		}
	}
}
