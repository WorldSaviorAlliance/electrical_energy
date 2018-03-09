package com.warrior.eem.service;

import com.warrior.eem.entity.vo.ElectricityPackageVo;

/**
 * 套餐的服务接口
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface ElectricityPackageService extends IService {
	boolean initDefaultDataIfAbsent();
	
	ElectricityPackageVo getEntityVo(Long id);
}
