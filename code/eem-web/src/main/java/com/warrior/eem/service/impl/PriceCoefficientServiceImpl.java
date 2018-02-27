package com.warrior.eem.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PriceCoefficientDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.entity.PriceCoefficient;
import com.warrior.eem.entity.constant.PowerConsts;
import com.warrior.eem.entity.vo.PriceCoefficientCdtVo;
import com.warrior.eem.entity.vo.PriceCoefficientVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.PriceCoefficientService;
import com.warrior.eem.util.EntityValidator;
import com.warrior.eem.util.ToolUtil;

/**
 * 电价系数的服务实现
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Service
public final class PriceCoefficientServiceImpl extends AbstractServiceImpl<PriceCoefficient>
		implements PriceCoefficientService {

	@Autowired
	private PriceCoefficientDao dao;

	@Override
	IDao<PriceCoefficient> getDao() {
		return dao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		PriceCoefficientCdtVo vo = (PriceCoefficientCdtVo) conditions[0];
		try {
			EntityValidator.checkEntity(vo);
		} catch (IllegalAccessException | SecurityException e) {
			throw new EemException("电价系数搜索查询条件解析失败");
		}
		if (!vo.isCoefficientValid()) {
			throw new EemException("起始系数大于结束系数");
		}
		LogicalCondition cdt = LogicalCondition.emptyOfTrue();
		String name = vo.getName();
		if (!ToolUtil.isStringEmpty(name)) {
			cdt = cdt.and(SimpleCondition.like("name", "%" + name + "%"));
		}
		int startCoefficient = vo.getStartCoefficient();
		if (startCoefficient != PowerConsts.INVALID_PRICE_COEFFICIENT) {
			int endCoefficient = vo.getEndCoefficient();
			if (endCoefficient != PowerConsts.INVALID_PRICE_COEFFICIENT) {
				cdt = cdt.and(SimpleCondition.between("coefficient", startCoefficient, endCoefficient));
			} else {
				cdt = cdt.and(SimpleCondition.equal("coefficient", startCoefficient));
			}
		}
		SqlRequest request = new SqlRequest();
		request.setPage(new Page(vo.getStartPage(), vo.getPerPageCnt()));
		Order order = new Order();
		order.addOrder("coefficient", Order_Type.ASC);
		request.setOrder(order);
		request.setCdt(cdt);
		return request;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	PriceCoefficient convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		PriceCoefficientVo coefficientVo = (PriceCoefficientVo) vo;
		PriceCoefficient coefficient = (PriceCoefficient) dbo;
		coefficient.setCoefficient(coefficientVo.getCoefficient());
		coefficient.setName(coefficientVo.getName());
		return coefficient;
	}

	@Override
	PriceCoefficient convertVoToDoForCreate(Serializable vo) {
		PriceCoefficientVo coefficientVo = (PriceCoefficientVo) vo;
		PriceCoefficient coefficient = new PriceCoefficient();
		coefficient.setCoefficient(coefficientVo.getCoefficient());
		coefficient.setName(coefficientVo.getName());
		return coefficient;
	}
}
