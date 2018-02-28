package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.VoltageTypeDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.BaseType;
import com.warrior.eem.entity.VoltageType;
import com.warrior.eem.entity.vo.BaseTypeCdtVo;
import com.warrior.eem.entity.vo.BaseTypeVo;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.VoltageTypeService;
import com.warrior.eem.shiro.session.EemSession;
import com.warrior.eem.util.EntityValidator;
import com.warrior.eem.util.ToolUtil;

/**
 * 电压等级服务的实现
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Service
public class VoltageTypeServiceImpl extends AbstractServiceImpl<VoltageType>implements VoltageTypeService {

	@Autowired
	private VoltageTypeDao dao;

	@Override
	IDao<VoltageType> getDao() {
		return dao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		BaseTypeCdtVo vo = (BaseTypeCdtVo) conditions[0];
		try {
			EntityValidator.checkEntity(vo);
		} catch (IllegalAccessException | SecurityException e) {
			throw new EemException("电压类型搜索查询条件解析失败");
		}
		LogicalCondition cdt = LogicalCondition.emptyOfTrue();
		String name = vo.getName();
		if (!ToolUtil.isStringEmpty(name)) {
			cdt = cdt.and(SimpleCondition.like("name", "%" + name + "%"));
		}
		SqlRequest request = new SqlRequest();
		request.setPage(vo.getPage());
		Order order = new Order();
		order.addOrder("id", Order.Order_Type.ASC);
		request.setOrder(order);
		request.setCdt(cdt);
		return request;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	VoltageType convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		BaseTypeVo typeVo = (BaseTypeVo) vo;
		VoltageType voltage = (VoltageType) dbo;
		voltage.setName(typeVo.getName());
		return voltage;
	}

	@Override
	VoltageType convertVoToDoForCreate(Serializable vo) {
		BaseTypeVo typeVo = (BaseTypeVo) vo;
		VoltageType voltage = new VoltageType();
		voltage.setCreateDate(new Date());
		voltage.setName(typeVo.getName());
		voltage.setCreator(EemSession.getCurrentUser());
		return voltage;
	}
	
	@Override
	public PageVo listEntities(Serializable... conditions) {
		PageVo pageVo = super.listEntities(conditions);
		List<BaseTypeVo> vos = new ArrayList<>();
		for (Object obj : pageVo.getDatas()) {
			vos.add(((BaseType)obj).convert());
		}
		pageVo.setDatas(vos);
		return pageVo;
	}
}
