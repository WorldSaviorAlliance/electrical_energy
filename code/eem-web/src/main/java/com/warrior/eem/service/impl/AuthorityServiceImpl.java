package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.AuthorityDao;
import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.Authority;
import com.warrior.eem.entity.constant.EntityFactory;
import com.warrior.eem.service.AuthorityService;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Service
public final class AuthorityServiceImpl extends AbstractServiceImpl<Authority>implements AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	IDao<Authority> getDao() {
		return authorityDao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	Authority convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return null;
	}

	@Override
	Authority convertVoToDoForCreate(Serializable vo) {
		return null;
	}

	@Override
	@Transactional
	public boolean initDefaultDataIfAbsent() {
		if (authorityDao.countDos(null) > 0) {
			return true;
		}
		List<Authority> authorities = EntityFactory.getDefaultAuthorities();
		for (Authority elem : authorities) {
			authorityDao.createDo(elem);
		}
		return authorityDao.countDos(null) == authorities.size();
	}
}
