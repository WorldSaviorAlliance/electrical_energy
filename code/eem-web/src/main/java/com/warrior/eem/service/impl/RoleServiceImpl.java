package com.warrior.eem.service.impl;

import java.io.Serializable;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.Role;
import com.warrior.eem.service.RoleService;

/**
 * 角色服务
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public class RoleServiceImpl extends AbstractServiceImpl<Role> implements RoleService {

	@Override
	IDao<Role> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	Role convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Role convertVoToDoForCreate(Serializable vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
