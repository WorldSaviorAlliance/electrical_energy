package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.ProvinceDao;
import com.warrior.eem.entity.Province;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Repository
public class ProvinceDaoImpl extends AbstractDaoImpl<Province> implements ProvinceDao {

	@Override
	protected Class<Province> getEntityClass() {
		return Province.class;
	}
}
