package com.warrior.eem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.ProvinceDao;
import com.warrior.eem.entity.Province;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Repository
public class ProvinceDaoImpl extends AbstractDaoImpl<Province>implements ProvinceDao {

	@Override
	protected Class<Province> getEntityClass() {
		return Province.class;
	}

	@Override
	public List<Province> queryAll() {
		List<?> result = getEntityManager().createNativeQuery("select * from province", getEntityClass())
				.getResultList();
		List<Province> provinces = new ArrayList<>(result.size());
		for (Object obj : result) {
			provinces.add((Province) obj);
		}
		return provinces;
	}
}
