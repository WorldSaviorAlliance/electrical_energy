package com.warrior.eem.service;

import com.warrior.eem.entity.Role;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface RoleService extends IService {
	/**
	 * 初始化一个管理员角色
	 * 
	 * @return 成功返回true,失败返回false
	 * @throws IOException
	 */
	boolean initAdminRole();

	/**
	 * 查询管理员角色
	 * 
	 * @return
	 */
	Role queryAdminRole();
}
