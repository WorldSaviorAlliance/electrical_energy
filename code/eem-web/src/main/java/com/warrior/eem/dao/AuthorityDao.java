package com.warrior.eem.dao;

import java.util.List;

import com.warrior.eem.entity.Authority;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface AuthorityDao extends IDao<Authority> {
	List<Authority> queryAll();
}
