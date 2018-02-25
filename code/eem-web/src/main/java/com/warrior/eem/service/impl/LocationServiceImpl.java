package com.warrior.eem.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.ProvinceDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.Province;
import com.warrior.eem.service.LocationService;

/**
 * 地理位置的服务
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Service
public class LocationServiceImpl extends AbstractServiceImpl<Province> implements LocationService {

	@Autowired
	private ProvinceDao dao;

	@Override
	IDao<Province> getDao() {
		return dao;
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
	Province convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return null;
	}

	@Override
	Province convertVoToDoForCreate(Serializable vo) {
		return null;
	}
}
