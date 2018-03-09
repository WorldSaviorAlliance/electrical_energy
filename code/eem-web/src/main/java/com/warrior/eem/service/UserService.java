package com.warrior.eem.service;

import java.util.List;

import com.warrior.eem.common.LoginResult;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.vo.ElectricityPackageVo;
import com.warrior.eem.entity.vo.UserVo;

/**
 * 用户的服务接口
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface UserService extends IService {
	
	LoginResult login(String name, String pwd);
	
	boolean createAdminIfAbsent();

	User setRole(Long userId, Long roleId);

	void modifyPassword(Long userId, String oldPwd, String newPwd);

	User modifyName(Long userId, String newName);
	
	boolean containsElectricityPackage(Long userId, Long pkgId);
	
	void handleElectricityPackage(Long userId, Long pkg);
	
	void cancelElectricityPackage(Long userId, Long pkgId);
	
	List<ElectricityPackageVo> getElectricityPackages(Long userId);
	
	UserVo getEntityVo(Long id);
}
