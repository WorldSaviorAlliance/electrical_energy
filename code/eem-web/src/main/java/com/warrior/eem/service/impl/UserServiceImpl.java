package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.RoleDao;
import com.warrior.eem.dao.UserDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Sql_Operator;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.Role;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.constant.UserStatus;
import com.warrior.eem.entity.constant.UserType;
import com.warrior.eem.entity.ui.Base64AndMD5Util;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.entity.vo.UserCdtVo;
import com.warrior.eem.entity.vo.UserVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.UserService;
import com.warrior.eem.util.EntityValidator;
import com.warrior.eem.util.ToolUtil;

/**
 * 系统用户服务
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PowerCustomerDao customerDao;

	@Override
	IDao<User> getDao() {
		return userDao;
	}

	@Override
	@Transactional
	public User setRole(Long userId, Long roleId) {
		User user = userDao.getEntity(userId);
		if (null == user) {
			throw new EemException("无效的用户id：" + userId);
		}
		Role role = roleDao.getEntity(roleId);
		if (null == role) {
			throw new EemException("无效的权限id：" + userId);
		}
		user.setRole(role);
		userDao.updateDo(user);
		return user;
	}

	@Override
	@Transactional
	public void modifyPassword(Long userId, String oldPwd, String newPwd) {
		User user = (User) getEntity(userId);
		if (null == user) {
			throw new EemException("无效的用户id：" + userId);
		}
		if (!user.getPassword().equals(Base64AndMD5Util.encodeByBase64AndMd5(oldPwd))) {
			throw new EemException("密码不正确");
		}
		user.setPassword(Base64AndMD5Util.encodeByBase64AndMd5(newPwd));
		userDao.updateDo(user);
	}

	@Override
	@Transactional
	public User modifyName(Long userId, String newName) {
		User user = (User) getEntity(userId);
		if (null == user) {
			throw new EemException("无效的用户id：" + userId);
		}
		user.setName(newName);
		userDao.updateDo(user);
		return user;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		UserCdtVo cdt = (UserCdtVo) conditions[0];
		try {
			EntityValidator.checkEntity(cdt);
		} catch (IllegalAccessException | SecurityException e) {
			throw new EemException("用户列表查询条件解析失败");
		}
		SqlRequest req = new SqlRequest();
		req.setPage(new Page(cdt.getStartPage(), cdt.getPerPageCnt()));
		Order order = new Order();
		order.addOrder("id", Order_Type.ASC);
		req.setOrder(order);
		LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
		if (!ToolUtil.isStringEmpty(cdt.getName())) {
			sqlCdt = sqlCdt.and(SimpleCondition.like("name", "%" + cdt.getName() + "%"));
		}
		sqlCdt = sqlCdt.and(new SimpleCondition("status", Sql_Operator.EQ, UserStatus.ACTIVE));
		sqlCdt = sqlCdt.and(new SimpleCondition("name", Sql_Operator.NOT_EQ, "admin"));
		req.setCdt(sqlCdt);
		return req;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	User convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return null;
	}

	@Override
	User convertVoToDoForCreate(Serializable vo) {
		User user = new User();
		UserVo userVo = (UserVo) vo;
		user.setName(userVo.getName());
		user.setPassword(Base64AndMD5Util.encodeByBase64AndMd5(userVo.getPassword()));
		user.setType(UserType.convert(userVo.getType()));
		if (UserType.ELECTRICITY == user.getType()) {
			PowerCustomer customer = customerDao.getEntity(userVo.getCustomerId());
			if (null != customer) {
				user.setCustomer(customer);
			}
		}
		Timestamp time = ToolUtil.getCurrentTime();
		user.setAddTime(time);
		user.setStatus(UserStatus.ACTIVE);
		return user;
	}

	@Override
	@Transactional
	public void deleteEntity(Serializable id) {
		User user = (User) getEntity(id);
		if (null == user) {
			throw new EemException("无效的用户id：" + id);
		}
		user.setStatus(UserStatus.DISABLE);
		userDao.updateDo(user);
	}

	@Override
	@Transactional
	public boolean createAdminIfAbsent() {
		if (checkExistAdminUser()) {
			return true;
		}
		userDao.createDo(buildAdmin());
		return checkExistAdminUser();
	}

	@Override
	@Transactional
	public PageVo listEntities(Serializable... conditions) {
		PageVo pageVo = super.listEntities(conditions);
		List<UserVo> vos = new ArrayList<UserVo>();
		for (Object obj : pageVo.getDatas()) {
			vos.add(((User) obj).convert());
		}
		pageVo.setDatas(vos);
		return pageVo;
	}
	
	private boolean checkExistAdminUser() {
		SqlRequest req = new SqlRequest();
		SimpleCondition scdt = new SimpleCondition("name", Sql_Operator.EQ, "admin");
		req.setCdt(scdt);
		return userDao.countDos(req) > 0;
	}

	private User buildAdmin() {
		User admin = new User();
		admin.setName("admin");
		admin.setPassword(Base64AndMD5Util.encodeByBase64AndMd5("mirror-0"));
		admin.setStatus(UserStatus.ACTIVE);
		admin.setType(UserType.SYSTEM);
		Timestamp time = ToolUtil.getCurrentTime();
		admin.setAddTime(time);
		admin.setLastLoginTime(time);
		return admin;
	}
}
