package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.impl.UserDaoImpl;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Sql_Operator;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.constant.UserStatus;
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
public class UserServiceImpl extends AbstractServiceImpl<User>implements UserService {
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserDaoImpl userDao;

	@Override
	IDao<User> getDao() {
		return userDao;
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
		order.addOrder("nick_name", Order_Type.DESC);
		req.setOrder(order);
		LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
		if (!ToolUtil.isStringEmpty(cdt.getName())) {
			sqlCdt = sqlCdt.and(SimpleCondition.like("name", "%" + cdt.getName() + "%"));
		}
		if (!ToolUtil.isStringEmpty(cdt.getNickName())) {
			sqlCdt = sqlCdt.and(SimpleCondition.like("nick_name", "%" + cdt.getNickName() + "%"));
		}
		UserStatus status = UserStatus.convert2UserStatus(cdt.getStatus());
		if (UserStatus.ALL != status) {
			sqlCdt = sqlCdt.and(SimpleCondition.equal("status", status));
		}
		if (cdt.getRoleId() > 0) {
			sqlCdt = sqlCdt.and(SimpleCondition.equal("role_id", cdt.getRoleId()));
		}
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
		UserVo userVo = (UserVo) vo;
		user.setNickName(userVo.getNickName());
		user.setPassword(userVo.getPassword());
		user.setStatus(UserStatus.convert2UserStatus(userVo.getStatus()));
		// TODO:对object属性的如何设置?
		return user;
	}

	@Override
	User convertVoToDoForCreate(Serializable vo) {
		User user = new User();
		Timestamp time = ToolUtil.getCurrentTime();
		user.setAddTime(time);
		UserVo userVo = (UserVo) vo;
		user.setName(userVo.getName());
		user.setNickName(userVo.getNickName());
		user.setPassword(userVo.getPassword());
		user.setStatus(UserStatus.convert2UserStatus(userVo.getStatus()));
		// TODO:对object属性的如何设置?
		return user;
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
		admin.setPassword(Base64AndMD5Util.encodeByBase64AndMd5("admin"));
		admin.setNickName("admin");
		admin.setStatus(UserStatus.ACTIVE);
		Timestamp time = ToolUtil.getCurrentTime();
		admin.setAddTime(time);
		admin.setLastLoginTime(time);
		return admin;
	}
}
