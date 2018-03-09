package com.warrior.eem.shiro.realm;

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

import com.warrior.eem.common.LoginResult;
import com.warrior.eem.service.UserService;
import com.warrior.eem.shiro.session.EemSession;

/**
 * 认证/授权realm
 * 
 * @author seangan
 *
 */
public class EemRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

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

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		LoginResult result = userService.login(upt.getUsername(), String.valueOf(upt.getPassword()));
		EemSession.setCurrentUser(result.getUser());
		EemSession.setCurrentUserPermissions(result.getAuthorities());
		return new SimpleAuthenticationInfo(upt.getUsername(), upt.getPassword(), getName());
	}
}
