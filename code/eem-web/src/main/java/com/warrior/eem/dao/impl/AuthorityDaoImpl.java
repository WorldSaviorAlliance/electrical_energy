package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.AuthorityDao;
import com.warrior.eem.entity.Authority;
import com.warrior.eem.util.ToolUtil;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Repository
public final class AuthorityDaoImpl extends AbstractDaoImpl<Authority>implements AuthorityDao {

	@Override
	protected Class<Authority> getEntityClass() {
		return Authority.class;
	}

	@Override
	public Authority queryAuthority(long id) {
		Authority authority = getReference(id);
		if (ToolUtil.isStringEmpty(authority.getFunc())) {
			authority = getEntity(id);
		}
		return authority;
	}
}
