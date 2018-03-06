package com.warrior.eem.shiro.session;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.warrior.eem.entity.User;
import com.warrior.eem.entity.constant.ResourceOperation;
import com.warrior.eem.exception.EemException;

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
	 * 设置当前用户
	 * @param user
	 */
	public static void setCurrentUser(User user) {
		getSession().setAttribute(CUR_USER, user);
	}
	
	/**
	 * 权限检测
	 * 
	 * @param res
	 * @param op
	 * @param id
	 */
	public static void checkPermission(String res, ResourceOperation op, Long id) {
		User user = getCurrentUser();
		if (null == user) {
			throw new EemException("Session expired");
		}
		user.checkPermission(res, op, id);
	}
}
