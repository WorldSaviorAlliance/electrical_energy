package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.RoleDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Sql_Operator;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.entity.Authority;
import com.warrior.eem.entity.Role;
import com.warrior.eem.entity.vo.RoleCdtVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.RoleService;
import com.warrior.eem.util.EntityValidator;
import com.warrior.eem.util.ToolUtil;

/**
 * 角色服务
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role>implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	IDao<Role> getDao() {
		return roleDao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		RoleCdtVo cdt = (RoleCdtVo) conditions[0];
		try {
			EntityValidator.checkEntity(cdt);
		} catch (IllegalAccessException | SecurityException e) {
			throw new EemException("角色列表查询条件解析失败");
		}
		SqlRequest req = new SqlRequest();
		Page page = new Page(cdt.getStartPage(), cdt.getPerPageCnt());
		req.setPage(page);
		Order order = new Order();
		order.addOrder("name", Order_Type.ASC);
		req.setOrder(order);
		LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
		if (!ToolUtil.isStringEmpty(cdt.getName())) {
			sqlCdt = sqlCdt.and(SimpleCondition.like("name", "%" + cdt.getName() + "%"));
		}
		req.setCdt(sqlCdt);
		return req;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	Role convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return null;
	}

	@Override
	Role convertVoToDoForCreate(Serializable vo) {
		return null;
	}

	@Override
	public boolean updateAuthorities(long roleId, List<Authority> authorities) {
		Role role = queryRole(roleId);
		role.getAuthorities().clear();
		role.getAuthorities().addAll(authorities);
		roleDao.updateDo(role);
		return true;
	}

	@Override
	public boolean checkExist(String name) {
		SqlRequest req = new SqlRequest();
		req.setCdt(new SimpleCondition("name", Sql_Operator.EQ, name));
		return roleDao.countDos(req) > 0;
	}

	private Role queryRole(long roleId) {
		Role role = roleDao.getReference(roleId);
		if (!role.isValid()) {// 从引用中获取的是一个虚拟的对象,去数据库查询
			role = roleDao.getEntity(roleId);
		}
		return role;
	}
}
