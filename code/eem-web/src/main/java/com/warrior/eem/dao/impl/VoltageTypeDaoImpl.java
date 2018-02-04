package com.warrior.eem.dao.impl;

import com.warrior.eem.dao.VoltageTypeDao;
import com.warrior.eem.entity.VoltageType;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public class VoltageTypeDaoImpl extends AbstractDaoImpl<VoltageType> implements VoltageTypeDao {

	@Override
	protected Class<VoltageType> getEntityClass() {
		return VoltageType.class;
	}
}
