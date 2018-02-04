package com.warrior.eem.dao.impl;

import com.warrior.eem.dao.UserDao;
import com.warrior.eem.entity.User;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
