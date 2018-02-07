package com.warrior.eem.shiro.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.warrior.eem.dao.UserDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Sql_Operator;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.ui.Base64AndMD5Util;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.shiro.session.EemSession;

/**
 * 认证/授权realm
 * 
 * @author seangan
 *
 */
public class EemRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo az = new SimpleAuthorizationInfo();
		// TODO 具体权限
		// Set<String> ps = new HashSet<String>();
		// ps.add("demo:create");
		// ps.add("demo:get");
		// az.addStringPermissions(ps);
		return az;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException, EemException {
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		SqlRequest req = new SqlRequest();
		LogicalCondition c = LogicalCondition.emptyOfFalse();
		c = c.and(new SimpleCondition("name", Sql_Operator.EQ, upt.getUsername()));
		c = c.and(new SimpleCondition("password", Sql_Operator.EQ,
				Base64AndMD5Util.encodeByBase64AndMd5(String.valueOf(upt.getPassword()))));
		req.setCdt(c);
		List<User> users = (List<User>) userDao.listDos(req);
		if (users == null || users.size() == 0) {
			throw new EemException("用户名或者密码有误");
		}
		EemSession.setCurrentUser(users.get(0));
		return new SimpleAuthenticationInfo(upt.getUsername(), upt.getPassword(), getName());
	}
}
