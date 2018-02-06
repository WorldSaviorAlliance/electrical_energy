package com.warrior.eem.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerDataDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.PowerData;
import com.warrior.eem.entity.vo.PowerCustomerOrSupplierCdtVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.PowerDataService;
import com.warrior.eem.util.EntityValidator;

/**
 * 电量服务
 * 
 * @author seangan
 *
 */
@Service
public class PowerDataServiceImpl extends AbstractServiceImpl<PowerData> implements PowerDataService {

	@Autowired
	private PowerDataDao pdDao;

	@Override
	IDao<PowerData> getDao() {
		return pdDao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		PowerCustomerOrSupplierCdtVo cdt = (PowerCustomerOrSupplierCdtVo) conditions[0];
		Page page = new Page((int) conditions[1], (int) conditions[2]);
		SqlRequest req = new SqlRequest();
		req.setPage(page);
		if (cdt != null) {
			try {
				EntityValidator.checkEntity(cdt);
			} catch (IllegalAccessException | SecurityException e) {
				throw new EemException("电力用户列表搜索条件解析失败");
			}
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
	PowerData convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return null;
	}

	@Override
	PowerData convertVoToDoForCreate(Serializable vo) {
		return null;
	}
}
