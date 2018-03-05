package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.AuthorityDao;
import com.warrior.eem.entity.Authority;

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
}
