package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.ElectricityPackageDao;
import com.warrior.eem.entity.ElectricityPackage;

/**
 * 套餐的数据接口实现
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Repository
public class ElectricityPackageDaoImpl extends AbstractDaoImpl<ElectricityPackage> implements ElectricityPackageDao {

	@Override
	protected Class<ElectricityPackage> getEntityClass() {
		return ElectricityPackage.class;
	}
}
