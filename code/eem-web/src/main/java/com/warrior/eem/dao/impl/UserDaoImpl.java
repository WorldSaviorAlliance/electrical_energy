package com.warrior.eem.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import com.warrior.eem.dao.UserDao;
import com.warrior.eem.dao.support.Condition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getEntity(Serializable pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getReference(Serializable pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createDo(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDo(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDo(Serializable pk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<?> listDos(SqlRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> listDosBySql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countDos(Condition cdt) {
		// TODO Auto-generated method stub
		return 0;
	}

}
