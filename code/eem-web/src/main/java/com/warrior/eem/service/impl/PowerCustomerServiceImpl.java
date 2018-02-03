package com.warrior.eem.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.vo.PowerCustomerOrSupplierCdtVo;
import com.warrior.eem.service.PowerCustomerService;

/**
 * 电力客户服务
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
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		PowerCustomerOrSupplierCdtVo cdt = (PowerCustomerOrSupplierCdtVo) conditions[0];
		Page page = new Page((int) conditions[1], (int) conditions[2]);
		SqlRequest req = new SqlRequest();
		req.setPage(page);
		if (cdt != null) {
			LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
			if (cdt.getName() != null && cdt.getName().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.like("name", cdt.getName() + "%"));
			}
			if (cdt.getProvince() != null && cdt.getProvince().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.equal("province", cdt.getProvince()));
			}
			if (cdt.getProvince() != null && cdt.getProvince().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.equal("city", cdt.getCity()));
			}
			req.setCdt(sqlCdt);
		}
		return req;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... condition) {
		return null;
	}

	@Override
	PowerCustomer convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return null;
	}
}
