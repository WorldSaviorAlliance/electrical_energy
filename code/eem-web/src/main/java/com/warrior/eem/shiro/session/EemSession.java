package com.warrior.eem.shiro.session;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.warrior.eem.entity.Authority;
import com.warrior.eem.entity.User;

/**
 * eem session管理
 * @author seangan
 *
 */
public class EemSession {
	
	/**
	 * 当前登录用户mark
	 */
	public static final String CUR_USER = "current_logon_user";
	
	public static final String CUR_USER_PERMISSIONS = "cur_user_permissions";
	
	/**
	 * 获取session
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	/**
	 * 设置属性值
	 * @param name
	 * @param value
	 */
	public static void setAttribute(String name, Object value) {
		getSession().setAttribute(name, value);
	}
	
	/**
	 * 获取某个属性值
	 * @param name
	 * @return
	 */
	public static Object getAttribute(String name) {
		return getSession().getAttribute(name);
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static User getCurrentUser() {
		return (User)getSession().getAttribute(CUR_USER);
	}
	
	/**
	 * 获取当前用户的权限列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Authority> getCurrentUserPermissions() {
		return (List<Authority>) getSession().getAttribute(CUR_USER_PERMISSIONS);
	}
	
	/**
	 * 设置当前用户
	 * @param user
	 */
	public static void setCurrentUser(User user) {
		getSession().setAttribute(CUR_USER, user);
	}
	
	/**
	 * 设置当前用户的权限列表
	 * 
	 * @param authorities
	 */
	public static void setCurrentUserPermissions(List<Authority> authorities) {
		getSession().setAttribute(CUR_USER_PERMISSIONS, authorities);
	}
}
