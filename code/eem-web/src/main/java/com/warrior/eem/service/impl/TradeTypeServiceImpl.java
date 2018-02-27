package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.TradeTypeDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.TradeType;
import com.warrior.eem.entity.vo.BaseTypeCdtVo;
import com.warrior.eem.entity.vo.BaseTypeVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.TradeTypeService;
import com.warrior.eem.shiro.session.EemSession;
import com.warrior.eem.util.EntityValidator;
import com.warrior.eem.util.ToolUtil;

/**
 * 交易单元的服务实现
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Service
public final class TradeTypeServiceImpl extends AbstractServiceImpl<TradeType>implements TradeTypeService {

	@Autowired
	private TradeTypeDao dao;

	@Override
	IDao<TradeType> getDao() {
		return dao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		BaseTypeCdtVo vo = (BaseTypeCdtVo) conditions[0];
		try {
			EntityValidator.checkEntity(vo);
		} catch (IllegalAccessException | SecurityException e) {
			throw new EemException("交易单元查询或搜索条件解析失败");
		}
		String name = vo.getName();
		LogicalCondition cdt = LogicalCondition.emptyOfTrue();
		if (!ToolUtil.isStringEmpty(name)) {
			cdt = cdt.and(SimpleCondition.like("name", "%" + name + "%"));
		}
		SqlRequest request = new SqlRequest();
		request.setPage(vo.getPage());
		Order order = new Order();
		order.addOrder("name", Order.Order_Type.ASC);
		request.setOrder(order);
		request.setCdt(cdt);
		return request;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	TradeType convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		BaseTypeVo typeVo = (BaseTypeVo) vo;
		TradeType type = (TradeType) dbo;
		type.setName(typeVo.getName());
		return type;
	}

	@Override
	TradeType convertVoToDoForCreate(Serializable vo) {
		BaseTypeVo typeVo = (BaseTypeVo) vo;
		TradeType type = new TradeType();
		type.setName(typeVo.getName());
		type.setCreateDate(new Date());
		type.setCreator(EemSession.getCurrentUser());
		return type;
	}
}
