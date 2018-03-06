package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.AuthorityDao;
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
import com.warrior.eem.entity.vo.RoleVo;
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

	@Autowired
	private AuthorityDao authorityDao;
	
	@Override
	IDao<Role> getDao() {
		return roleDao;
	}
	
	@Override
	@Transactional
	public boolean initAdminRole() {
		if (checkExist("管理员")) {
			return true;
		}
		Role admin = new Role();
		admin.setName("管理员");
		List<?> authorities = authorityDao.listDos(null);
		for (Object obj : authorities) {
			admin.addAuthority((Authority) obj);
		}
		roleDao.createDo(admin);
		return checkExist("管理员");
	}
	
	@Override
	@Transactional
	public Role queryAdminRole() {
		SqlRequest req = new SqlRequest();
		req.setCdt(new SimpleCondition("name", Sql_Operator.EQ, "管理员"));
		List<?> roles = roleDao.listDos(req);
		return roles.isEmpty() ? null : (Role) roles.get(0);
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
		RoleVo roleVo = (RoleVo) vo;
		Role role = (Role) dbo;
		updateAuthority(role, roleVo);
		return role;
	}

	@Override
	Role convertVoToDoForCreate(Serializable vo) {
		RoleVo roleVo = (RoleVo) vo;
		String name = roleVo.getName();
		if (checkExist(name)) {
			throw new EemException(name + "已存在");
		}
		Role role = new Role();
		role.setName(name);
		updateAuthority(role, roleVo);
		return null;
	}

	private void updateAuthority(Role role, RoleVo roleVo) {
		List<Long> authorityIds = roleVo.getAuthorities();
		role.getAuthorities().clear();
		if (null != authorityIds && !authorityIds.isEmpty()) {
			Authority authority;
			for (Long id : authorityIds) {
				authority = authorityDao.getEntity(id);
				if (null != authority) {
					role.getAuthorities().add(authority);
				}
			}
		}
	}

	private boolean checkExist(String name) {
		SqlRequest req = new SqlRequest();
		req.setCdt(new SimpleCondition("name", Sql_Operator.EQ, name));
		return roleDao.countDos(req) > 0;
	}
}
