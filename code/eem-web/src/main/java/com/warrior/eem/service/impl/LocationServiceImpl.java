package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.entity.City;
import com.warrior.eem.entity.Province;
import com.warrior.eem.service.IService;

@Service("location")
public class LocationServiceImpl implements IService, InitializingBean {

	@Resource(name = "cityDao")
	private IDao<City> cityDao;
	
	@Resource(name = "provinceDao")
	private IDao<Province> provinceDao;
	
	@Override
	public void createEntity(Serializable e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEntity(Serializable e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEntity(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Serializable getEntity(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> listEntities(Serializable... conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countEntity(Serializable... conditions) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		long cityCnt = cityDao.countDos(null);
		long provinceCnt = provinceDao.countDos(null);
		if (0 == cityCnt || 0 == provinceCnt) {
			// TODO 初始化城市和省份到数据库
		}
	}

}
