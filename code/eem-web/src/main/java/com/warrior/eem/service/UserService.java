package com.warrior.eem.service;

import com.warrior.eem.entity.User;

/**
 * 用户的服务接口
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface UserService extends IService {
	boolean createAdminIfAbsent();

	User setRole(Long userId, Long roleId);

	void modifyPassword(Long userId, String oldPwd, String newPwd);

	User modifyName(Long userId, String newName);
}
