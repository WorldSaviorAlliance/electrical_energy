package com.warrior.eem.service;

/**
 * 套餐的服务接口
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface ElectricityPackageService extends IService {
	boolean initDefaultDataIfAbsent();

	/**
	 * 办理套餐
	 * 
	 * @param pkgId
	 * @param userId
	 * @return
	 */
	boolean handleElectricityPackage(long pkgId, long userId);

	/**
	 * 取消套餐
	 * 
	 * @param pkgId
	 * @param userId
	 * @return
	 */
	boolean cancelElectricityPackage(long pkgId, long userId);
}
