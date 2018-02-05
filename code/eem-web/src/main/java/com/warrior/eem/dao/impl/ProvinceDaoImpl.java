package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.entity.Province;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Repository("provinceDao")
public class ProvinceDaoImpl extends AbstractDaoImpl<Province> {

	@Override
	protected Class<Province> getEntityClass() {
		return Province.class;
	}
}
