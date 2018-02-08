package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.RoleDao;
import com.warrior.eem.entity.Role;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Repository
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {

	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}
}
