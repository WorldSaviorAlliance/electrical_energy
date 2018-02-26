package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.VoltageTypeDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.VoltageType;
import com.warrior.eem.entity.vo.BaseTypeCdtVo;
import com.warrior.eem.entity.vo.BaseTypeVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.VoltageTypeService;
import com.warrior.eem.shiro.session.EemSession;
import com.warrior.eem.util.EntityValidator;
import com.warrior.eem.util.ToolUtil;

/**
 * 电压类型服务的实现
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
		BaseTypeCdtVo cdt = (BaseTypeCdtVo) conditions[0];
		try {
			EntityValidator.checkEntity(cdt);
		} catch (IllegalAccessException | SecurityException e) {
			throw new EemException("电压类型搜索查询条件解析失败");
		}
		LogicalCondition lcdt = LogicalCondition.emptyOfTrue();
		String name = cdt.getName();
		if (!ToolUtil.isStringEmpty(name)) {
			lcdt = lcdt.and(SimpleCondition.like("name", "%" + name + "%"));
		}
		SqlRequest request = new SqlRequest();
		request.setPage(cdt.getPage());
		request.setCdt(lcdt);
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
}
