package com.warrior.eem.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerSupplierDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.PowerSupplier;
import com.warrior.eem.service.PowerSupplierService;


/**
 * 电力提供商服务
 * @author seangan
 *
 */
@Service
public class PowerSupplierServiceImpl extends AbstractServiceImpl<PowerSupplier> implements PowerSupplierService {

	@Autowired
	private PowerSupplierDao pctDao;
	
	@Override
	IDao<PowerSupplier> getDao() {
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
