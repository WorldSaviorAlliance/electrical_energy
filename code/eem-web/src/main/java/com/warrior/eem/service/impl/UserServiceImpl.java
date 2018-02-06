package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.impl.UserDaoImpl;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Sql_Operator;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.constant.UserStatus;
import com.warrior.eem.entity.ui.Base64AndMD5Util;
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
	@Transactional
	public void afterPropertiesSet() throws Exception {
		// TODO 初始化角色和权限
		if (checkExistAdminUser()) {
			logger.info("admin already exit");
			return;
		}
		createEntity(buildAdmin());
		if (!checkExistAdminUser()) {
			logger.error("Insert admin failed");
		}
	}

	private boolean checkExistAdminUser() {
		SimpleCondition scdt = new SimpleCondition("name", Sql_Operator.EQ, "admin");
		return userDao.countDos(scdt) > 0;
	}
	
	private User buildAdmin() {
		User admin = new User();
		admin.setName("admin");
		admin.setPassword(Base64AndMD5Util.encodeByBase64AndMd5("admin"));
		admin.setNickName("admin");
		admin.setStatus(UserStatus.ACTIVE);
		Timestamp time = new Timestamp(System.currentTimeMillis()); 
		admin.setAddTime(time);
		admin.setLastLoginTime(time);
		return admin;
	}
}
