package com.warrior.eem.dao;

import java.util.List;

import com.warrior.eem.entity.Province;

/**
 * 省份的数据接口
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface ProvinceDao extends IDao<Province> {
	List<Province> queryAll();
}
