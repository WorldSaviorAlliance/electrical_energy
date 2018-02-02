package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.PowerSupplierDao;
import com.warrior.eem.entity.PowerSupplier;

@Repository
public class PowerSupplierImpl extends AbstractDaoImpl<PowerSupplier> implements PowerSupplierDao {

	@Override
	protected Class<PowerSupplier> getEntityClass() {
		return PowerSupplier.class;
	}

}
