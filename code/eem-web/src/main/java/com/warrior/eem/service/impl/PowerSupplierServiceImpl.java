package com.warrior.eem.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerSupplierDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.entity.PowerSupplier;
import com.warrior.eem.entity.vo.PowerCustomerOrSupplierCdtVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.PowerSupplierService;
import com.warrior.eem.util.EntityValidator;


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
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		PowerCustomerOrSupplierCdtVo cdt = (PowerCustomerOrSupplierCdtVo)conditions[0];
		Page page = new Page((int)conditions[1], (int)conditions[2]);
		SqlRequest req = new SqlRequest();
		req.setPage(page);
		Order order = new Order();
		order.addOrder("id", Order_Type.DESC);
		req.setOrder(order);
		if(cdt != null) {
			try {
				EntityValidator.checkEntity(cdt);
			} catch (IllegalAccessException | SecurityException e) {
				throw new EemException("解析电力供应商查询条件失败");
			}
			LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
			if(cdt.getName() != null && cdt.getName().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.like("name", "%" + cdt.getName() + "%"));
			}
			if(cdt.getProvince() != null && cdt.getProvince().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.equal("province", cdt.getProvince()));
			}
			if(cdt.getCity() != null && cdt.getCity().trim().length() > 0) {
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
	PowerSupplier convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return null;
	}

	@Override
	PowerSupplier convertVoToDoForCreate(Serializable vo) {
		return null;
	}
}
