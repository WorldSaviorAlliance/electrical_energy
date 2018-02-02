package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.entity.PowerCustomer;

@Repository
public class PowerCustomerImpl extends AbstractDaoImpl<PowerCustomer> implements PowerCustomerDao {

	@Override
	protected Class<PowerCustomer> getEntityClass() {
		return PowerCustomer.class;
	}

}
