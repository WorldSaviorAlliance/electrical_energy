package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
	private ConcurrentHashMap<Long, ProvinceVo> locationCache = new ConcurrentHashMap<>();
	
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
	@Transactional(readOnly = true)
	public List<ProvinceVo> queryAll() {
		List<ProvinceVo> vos;
		if (locationCache.isEmpty()) {
			List<Province> provinces = dao.queryAll();
			vos = new ArrayList<>(provinces.size());
			ProvinceVo vo;
			for (Province province : provinces) {
				vo = new ProvinceVo(province.getId(), province.getName());
				vo.setCities(province.getCities());
				vos.add(vo);
				locationCache.put(province.getId(), vo);
			}
		} else {
			vos = new ArrayList<>(locationCache.values());
		}
		return vos;
	}

	@Override
	@Transactional(readOnly = true)
	public ProvinceVo getEntityVo(Long id) {
		if (null == id) {
			throw new EemException("id不能为null");
		}
		ProvinceVo vo = locationCache.get(id);
		if (null == vo) {
			Province province = dao.getEntity(id);
			if (null == province) {
				throw new EemException("未找到id（" + id + "）对应的数据");
			}
			vo = new ProvinceVo(province.getId(), province.getName());
			vo.setCities(province.getCities());
			locationCache.put(id, vo);
		}
		return vo;
	}
}