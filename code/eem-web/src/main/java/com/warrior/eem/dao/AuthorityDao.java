package com.warrior.eem.dao;

import com.warrior.eem.entity.Authority;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface AuthorityDao extends IDao<Authority> {

	/**
	 * 优先从缓存中查找实体,若缓存中不存在,再从数据库查询
	 * 
	 * @param id
	 * @return 对应的实例  or null
	 */
	Authority queryAuthority(long id);
}
