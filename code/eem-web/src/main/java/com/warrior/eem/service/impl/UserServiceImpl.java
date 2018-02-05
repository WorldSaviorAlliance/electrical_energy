package com.warrior.eem.service.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.impl.UserDaoImpl;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.User;
import com.warrior.eem.service.UserService;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService, InitializingBean {
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private UserDaoImpl userDao;

	@Override
	IDao<User> getDao() {
		return userDao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	User convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	User convertVoToDoForCreate(Serializable vo) {
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO 初始化角色和权限
		long cnt = userDao.countDos(null);
		logger.error("user count = " + cnt);
	}

}
