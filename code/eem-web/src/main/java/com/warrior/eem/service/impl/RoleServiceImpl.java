package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.warrior.eem.entity.ElectricityAdjustmentData;
import com.warrior.eem.entity.ElectricityPackage;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.PowerData;
import com.warrior.eem.entity.Role;
import com.warrior.eem.entity.SellPowerAgreement;
import com.warrior.eem.entity.SellPowerAgreementMonthData;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.constant.ResourceOperation;
import com.warrior.eem.entity.vo.AuthorityVo;
import com.warrior.eem.entity.vo.PageVo;
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
public class RoleServiceImpl extends AbstractServiceImpl<Role> implements RoleService {
	private static final String ROLE_SYSTEM = "管理员";
	private static final String ROLE_COMMON = "#普通用户#";
	private static final List<String> COMM_ROLE_RES = new ArrayList<String>();
	static {
		COMM_ROLE_RES.add(User.class.getSimpleName());
		COMM_ROLE_RES.add(PowerCustomer.class.getSimpleName());
		COMM_ROLE_RES.add(PowerData.class.getSimpleName());
		COMM_ROLE_RES.add(SellPowerAgreement.class.getSimpleName());
		COMM_ROLE_RES.add(SellPowerAgreementMonthData.class.getSimpleName());
		COMM_ROLE_RES.add(ElectricityAdjustmentData.class.getSimpleName());
		COMM_ROLE_RES.add(ElectricityPackage.class.getSimpleName());
	}

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	IDao<Role> getDao() {
		return roleDao;
	}

	@Override
	@Transactional(readOnly = true)
	public PageVo listEntities(Serializable... conditions) {
		PageVo pageVo = super.listEntities(conditions);
		int cnt = pageVo.getCount().intValue();
		if (cnt > 0) {
			List<RoleVo> vos = new ArrayList<>(cnt);
			for (Object obj : pageVo.getDatas()) {
				vos.add(((Role) obj).convert());
			}
			pageVo.setDatas(vos);
		}
		return pageVo;
	}

	@Override
	@Transactional
	public boolean initAdminRole() {
		if (checkExist(ROLE_SYSTEM)) {
			return true;
		}
		Role admin = new Role();
		admin.setName(ROLE_SYSTEM);
		SqlRequest request = new SqlRequest();
		request.setCdt(SimpleCondition.equal("op", ResourceOperation.COM_CONTROL));
		List<?> authorities = authorityDao.listDos(request);
		for (Object obj : authorities) {
			admin.addAuthority((Authority) obj);
		}
		roleDao.createDo(admin);
		return checkExist(ROLE_SYSTEM);
	}

	@Override
	@Transactional
	public boolean initCommonRole() {
		if (checkExist(ROLE_COMMON)) {
			return true;
		}
		Role common = new Role();
		common.setName(ROLE_COMMON);
		SqlRequest request = new SqlRequest();
		request.setCdt(SimpleCondition.equal("op", ResourceOperation.COM_CONTROL));
		List<?> authorities = authorityDao.listDos(request);
		Authority authority;
		for (String res : COMM_ROLE_RES) {
			for (Object obj : authorities) {
				authority = (Authority) obj;
				if (authority.getRes().equals(res)) {
					common.addAuthority(authority);
				}
			}
		}
		roleDao.createDo(common);
		return checkExist(ROLE_COMMON);
	}

	@Override
	@Transactional
	public Role queryAdminRole() {
		SqlRequest req = new SqlRequest();
		req.setCdt(new SimpleCondition("name", Sql_Operator.EQ, ROLE_SYSTEM));
		List<?> roles = roleDao.listDos(req);
		return roles.isEmpty() ? null : (Role) roles.get(0);
	}
	
	@Override
	@Transactional
	public Role queryCommonRole() {
		SqlRequest req = new SqlRequest();
		req.setCdt(new SimpleCondition("name", Sql_Operator.EQ, ROLE_COMMON));
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
		sqlCdt = sqlCdt.and(SimpleCondition.notEqual("name", ROLE_COMMON));
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
		return updateAuthority(role, roleVo);
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
		return updateAuthority(role, roleVo);
	}

	private Role updateAuthority(Role role, RoleVo roleVo) {
		List<Long> authorityIds = roleVo.getAuthorityIds();
		if (authorityIds.isEmpty()) {
			return role;
		}
		if (!role.getAuthorities().isEmpty()) {
			List<Authority> willRemoveAuthorities = new ArrayList<>();
			boolean isFind;
			// 统计将要被移除的权限列表
			for (Authority authority : role.getAuthorities()) {
				isFind = false;
				for (Long id : authorityIds) {
					if (id.longValue() == authority.getId().longValue()) {
						isFind = true;
						break;
					}
				}
				if (!isFind) {
					willRemoveAuthorities.add(authority);
				}
			}
			// 移除不存在于UI端发送过来的权限列表中的权限
			for (Authority authority : willRemoveAuthorities) {
				role.removeAuthority(authority);
			}
			// 过滤掉已存在于角色权限列表中的权限
			for (int i = authorityIds.size() - 1; i >= 0; --i) {
				if (role.containsAuthority(authorityIds.get(i))) {
					authorityIds.remove(i);
				}
			}
		}
		// 将新增的权限加入角色的权限列表中
		Authority authority;
		for (Long id : authorityIds) {
			authority = authorityDao.getEntity(id);
			if (null != authority) {
				role.addAuthority(authority);
			}
		}
		return role;
	}

	private boolean checkExist(String name) {
		SqlRequest req = new SqlRequest();
		req.setCdt(new SimpleCondition("name", Sql_Operator.EQ, name));
		return roleDao.countDos(req) > 0;
	}

	@Override
	@Transactional(readOnly = true)
	public PageVo listAuthorities() {
		PageVo pageVo = new PageVo();
		List<Authority> authorities = authorityDao.queryAll();
		if (null == authorities || authorities.isEmpty()) {
			pageVo.setCount(Long.valueOf(0));
		} else {
			int size = authorities.size();
			List<AuthorityVo> vos = new ArrayList<>(size);
			for (Authority authority : authorities) {
				vos.add(authority.convert());
			}
			pageVo.setCount(Long.valueOf(size));
			pageVo.setDatas(vos);
		}
		return pageVo;
	}

	@Override
	@Transactional(readOnly = true)
	public RoleVo getEntityVo(Long id) {
		Role role = (Role) roleDao.getEntity(id);
		if (null == role) {
			throw new EemException("未找到id（" + id + "）对应的数据");
		}
		return role.convert2Desc();
	}
}
