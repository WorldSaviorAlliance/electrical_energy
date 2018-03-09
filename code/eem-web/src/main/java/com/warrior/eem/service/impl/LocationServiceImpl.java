package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.ProvinceDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.Province;
import com.warrior.eem.entity.vo.ProvinceVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.LocationService;

/**
 * 地理位置的服务
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Service
public class LocationServiceImpl extends AbstractServiceImpl<Province>implements LocationService {

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

	@Override
	public List<ProvinceVo> queryAll() {
		List<Province> provinces = dao.queryAll();
		List<ProvinceVo> vos = new ArrayList<>(provinces.size());
		for (Province province : provinces) {
			vos.add(new ProvinceVo(province.getId(), province.getName()));
		}
		return vos;
	}

	@Override
	@Transactional(readOnly = true)
	public ProvinceVo getEntityVo(Long id) {
		Province province = dao.getEntity(id);
		if (null == province) {
			throw new EemException("未找到id（" + id + "）对应的数据");
		}
		ProvinceVo vo = new ProvinceVo(province.getId(), province.getName());
		vo.setCities(province.getCities());
		return vo;
	}
}