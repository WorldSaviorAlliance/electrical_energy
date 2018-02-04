package com.warrior.eem.manager;

import javax.servlet.ServletConfig;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletConfigAware;

/**
 * 拦截器,用以数据库初始化数据
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public class DataInitManager implements InitializingBean, ServletConfigAware {

	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
