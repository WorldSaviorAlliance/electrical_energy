package com.warrior.eem.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 认证/授权realm
 * 
 * @author seangan
 *
 */
public class EemRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo az = new SimpleAuthorizationInfo();
		// TODO 具体权限
//		Set<String> ps = new HashSet<String>();
//		ps.add("demo:create");
//		ps.add("demo:get");
//		az.addStringPermissions(ps);
		return az;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO 完善认证流程
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		return new SimpleAuthenticationInfo(upt.getUsername(), upt.getPassword(), getName());
	}

}
