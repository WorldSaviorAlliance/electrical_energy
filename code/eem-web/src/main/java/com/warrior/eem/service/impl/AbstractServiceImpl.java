package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.IService;
import com.warrior.eem.util.EntityValidator;


/**
 * 抽象服务
 * @author seangan
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class AbstractServiceImpl<T extends Serializable> implements IService {
	
	@Override
	@Transactional
	public void createEntity(Serializable e) {
		try {
			EntityValidator.checkEntity(e);
		} catch (IllegalAccessException | SecurityException e1) {
			throw new EemException("创建实体解析参数失败");
		} 
		getDao().createDo((T) e);
	}

	@Override
	@Transactional
	public void updateEntity(Serializable e) {
		try {
			EntityValidator.checkEntity(e);
		} catch (IllegalAccessException | SecurityException e1) {
			throw new EemException("更新实体解析参数失败");
		} 
		getDao().updateDo((T) e);
	}

	@Override
	@Transactional
	public void deleteEntity(Serializable id) {
		if(id == null) {
			throw new EemException("id不能为空");
		}
		if(getEntity(id) == null) {
			throw new EemException("未找到id（" + id + "）对应的数据");
		}
		getDao().deleteDo(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Serializable getEntity(Serializable id) {
		return getDao().getEntity(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<?> listEntities(Serializable condition) {
		return getDao().listDos(buildListSqlRequest(condition));
	}
	

	@Override
	@Transactional(readOnly = true)
	public long countEntity(Serializable condition) {
		return getDao().countDos(buildCountSqlRequest(condition));
	}
	
	/**
	 * 自定义dao
	 * @return
	 */
	abstract IDao<T> getDao();
	
	/**
	 * 构建list sql request
	 * @param condition
	 * @return
	 */
	abstract SqlRequest buildListSqlRequest(Serializable condition);
	
	/**
	 * 构建count sql request
	 * @param condition
	 * @return
	 */
	abstract SqlRequest buildCountSqlRequest(Serializable condition);

}
