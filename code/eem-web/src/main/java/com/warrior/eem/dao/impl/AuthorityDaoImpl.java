package com.warrior.eem.dao.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<Authority> queryAll() {
		List<?> result = getEntityManager().createNativeQuery("select * from authority", getEntityClass())
				.getResultList();
		List<Authority> authorities = new ArrayList<>(result.size());
		for (Object obj : result) {
			authorities.add((Authority) obj);
		}
		return authorities;
	}
}
