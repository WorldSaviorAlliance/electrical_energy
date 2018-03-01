package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
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
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.constant.UserStatus;
import com.warrior.eem.entity.constant.UserType;
import com.warrior.eem.entity.ui.Base64AndMD5Util;
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
	private final Logger logger = Logger.getLogger(getClass());

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
	public User updateUser(UserVo vo) {
		try {
			EntityValidator.checkEntity(vo);
		} catch (IllegalAccessException | SecurityException e1) {
			throw new EemException("更新实体解析参数失败");
		}
		User user = queryUser(vo.getId());
		updateUser(user, vo);
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
		Page page = new Page(cdt.getStartPage(), cdt.getPerPageCnt());
		req.setPage(page);
		Order order = new Order();
		order.addOrder("id", Order_Type.ASC);
		req.setOrder(order);
		LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
		if (!ToolUtil.isStringEmpty(cdt.getName())) {
			sqlCdt = sqlCdt.and(SimpleCondition.like("name", "%" + cdt.getName() + "%"));
		}
		sqlCdt = sqlCdt.and(SimpleCondition.not("status", UserStatus.DISABLE));
		req.setCdt(sqlCdt);
		return req;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	User convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		User user = (User) dbo;
		updateUser(user, (UserVo) vo);
		return user;
	}

	@Override
	User convertVoToDoForCreate(Serializable vo) {
		User user = new User();
		Timestamp time = ToolUtil.getCurrentTime();
		user.setAddTime(time);
		user.setStatus(UserStatus.ACTIVE);
		updateUser(user, (UserVo) vo);
		return user;
	}

	private void updateUser(User user, UserVo vo) {
		if (!ToolUtil.isStringEmpty(vo.getName())) {
			user.setName(vo.getName());
		}
		if (vo.getCustomerId() != -1 && !user.containsCustomer(vo.getCustomerId())) {
			user.setCustomer(customerDao.getEntity(vo.getCustomerId()));
		}
	}

	@Override
	@Transactional
	public void deleteEntity(Serializable id) {
		if (id == null) {
			throw new EemException("id不能为空");
		}
		User user = (User) getEntity(id);
		if (null == user) {
			throw new EemException("未找到id（" + id + "）对应的数据");
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
		logger.info("creat admin user...");
		return checkExistAdminUser();
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

	private User queryUser(long userId) {
		User user = userDao.getReference(userId);
		if (ToolUtil.isStringEmpty(user.getName())) {
			user = userDao.getEntity(userId);
		}
		return user;
	}
}
