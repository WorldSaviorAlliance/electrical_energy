package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.List;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.service.IService;

@SuppressWarnings("unchecked")
public abstract class AbstractServiceImpl<T extends Serializable> implements IService {
	
	@Override
	public void createEntity(Serializable e) {
		getDao().createDo((T) e);
	}

	@Override
	public void updateEntity(Serializable e) {
		getDao().updateDo((T) e);
	}

	@Override
	public void deleteEntity(Serializable id) {
		getDao().deleteDo(id);
	}

	@Override
	public Serializable getEntity(Serializable id) {
		return getDao().getEntity(id);
	}

	@Override
	public List<?> listEntities(Serializable condition) {
		return getDao().listDos(buildListSqlRequest(condition));
	}
	

	@Override
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
