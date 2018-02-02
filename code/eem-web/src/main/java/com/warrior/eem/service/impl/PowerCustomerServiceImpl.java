package com.warrior.eem.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.service.PowerCustomerService;


/**
 * 
 * @author seangan
 *
 */
@Service
public class PowerCustomerServiceImpl extends AbstractServiceImpl<PowerCustomer> implements PowerCustomerService {

	@Autowired
	private PowerCustomerDao pctDao;
	
	@Override
	IDao<PowerCustomer> getDao() {
		return pctDao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable condition) {
		return null;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable condition) {
		return null;
	}
}
