package com.warrior.eem.service;

import java.util.List;

import com.warrior.eem.entity.Authority;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface RoleService extends IService {
	boolean updateAuthorities(long roleId, List<Authority> authorities);
	
	boolean checkExist(String name);
}
