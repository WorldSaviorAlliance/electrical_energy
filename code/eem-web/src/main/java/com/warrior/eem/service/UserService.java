package com.warrior.eem.service;

/**
 * 用户的服务接口
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface UserService extends IService {
	boolean createAdminIfAbsent();
}
