package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.ElectricityPackageDao;
import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.UserDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.ElectricityPackage;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.constant.EntityFactory;
import com.warrior.eem.entity.vo.ElectricityPackageVo;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.service.ElectricityPackageService;

/**
 * 套餐的服务接口实现
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Service
public class ElectricityPackageServiceImpl extends AbstractServiceImpl<ElectricityPackage>
		implements ElectricityPackageService {

	@Autowired
	private ElectricityPackageDao dao;

	@Autowired
	private UserDao userDao;

	@Override
	IDao<ElectricityPackage> getDao() {
		return dao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	ElectricityPackage convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return null;
	}

	@Override
	ElectricityPackage convertVoToDoForCreate(Serializable vo) {
		return null;
	}

	@Override
	public PageVo listEntities(Serializable... conditions) {
		PageVo pageVo = super.listEntities(conditions);
		List<ElectricityPackageVo> vos = new ArrayList<>();
		for (Object obj : pageVo.getDatas()) {
			vos.add(((ElectricityPackage) obj).convert());
		}
		pageVo.setDatas(vos);
		return pageVo;
	}

	@Override
	@Transactional
	public boolean initDefaultDataIfAbsent() {
		if (dao.countDos(null) > 0) {
			return true;
		}
		for (ElectricityPackage pkg : EntityFactory.getDefaultElectricityPackages()) {
			dao.createDo(pkg);
		}
		return dao.countDos(null) > 0;
	}

	@Override
	public boolean handleElectricityPackage(long pkgId, long userId) {
		User user = userDao.getEntity(userId);
		if (null == user || user.containsElectricityPackage(pkgId)) {
			return false;
		}
		ElectricityPackage pkg = dao.getEntity(pkgId);
		if (null == pkg) {
			return false;
		}
		user.handleElectricityPackage(pkg);
		return true;
	}

	@Override
	public boolean cancelElectricityPackage(long pkgId, long userId) {
		User user = userDao.getEntity(userId);
		if (null == user) {
			return false;
		}
		return user.cancelElectricityPackage(pkgId);
	}
}
