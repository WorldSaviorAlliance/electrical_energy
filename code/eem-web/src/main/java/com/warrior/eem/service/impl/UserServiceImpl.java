package com.warrior.eem.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.impl.UserDaoImpl;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.User;
import com.warrior.eem.service.UserService;

public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

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

}
