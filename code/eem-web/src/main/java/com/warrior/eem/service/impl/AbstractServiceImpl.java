package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.interfaces.EntityConvertor;
import com.warrior.eem.service.IService;
import com.warrior.eem.util.EntityValidator;

/**
 * 抽象服务
 * 
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
		Serializable targetEntity = e;
		if(e instanceof EntityConvertor) {
			targetEntity = ((EntityConvertor) e).convertToDo();
		} else if(e.getClass().getName().contains("com.warrior.eem.entity.vo")) {
			targetEntity = convertVoToDoForCreate(e);
		} 
		getDao().createDo((T)targetEntity);
	}

	@Override
	@Transactional
	public void updateEntity(Serializable e) {
		try {
			EntityValidator.checkEntity(e);
		} catch (IllegalAccessException | SecurityException e1) {
			throw new EemException("更新实体解析参数失败");
		}
		Long id = -1L;
		try {
			try {
				Field idF = e.getClass().getDeclaredField("id");
				idF.setAccessible(true);
				id = (Long) idF.get(e);
			} catch (NoSuchFieldException e2) {
				Field idF = e.getClass().getSuperclass().getDeclaredField("id");
				idF.setAccessible(true);
				id = (Long) idF.get(e);
			}
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			throw new EemException("解析id属性失败");
		}
		T dbEntity = (T) getEntity(id);
		if(dbEntity == null) {
			throw new EemException("未找到id（" + id + "）对应的信息");
		}
		Serializable targetEntity = null;
		if (e instanceof EntityConvertor) {
			targetEntity = ((EntityConvertor) e).mergeProps(dbEntity);
		} else if (e.getClass().getName().contains("com.warrior.eem.entity.vo")) {
			targetEntity = convertVoToDoForUpdate(dbEntity, e);
		} else {
			targetEntity = e;
		}
		getDao().updateDo((T) targetEntity);
	}

	@Override
	@Transactional
	public void deleteEntity(Serializable id) {
		if (id == null) {
			throw new EemException("id不能为空");
		}
		if (getEntity(id) == null) {
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
	public PageVo listEntities(Serializable... conditions) {
		SqlRequest req = buildListSqlRequest(conditions);
		return new PageVo(countEntity(req), getDao().listDos(req));
	}

	@Override
	@Transactional(readOnly = true)
	public long countEntity(Serializable... conditions) {
		SqlRequest req = null;
		if(conditions != null && conditions.length == 1 && conditions[0] instanceof SqlRequest) {
			req = (SqlRequest) conditions[0];
		} else {
			req = buildCountSqlRequest(conditions);
		}
		return getDao().countDos(req);
	}

	/**
	 * 自定义dao
	 * 
	 * @return
	 */
	abstract IDao<T> getDao();

	/**
	 * 构建list sql request
	 * 
	 * @param condition
	 * @return
	 */
	abstract SqlRequest buildListSqlRequest(Serializable... conditions);

	/**
	 * 构建count sql request
	 * 
	 * @param condition
	 * @return
	 */
	abstract SqlRequest buildCountSqlRequest(Serializable... conditions);

	/**
	 * 仅仅当需要在service里
	 * 将vo转为do对象时才实现，当vo对象实现了entityconvertor时，可以不实现此方法，或调用update方法的实体本来就是do对象
	 * 
	 * @param dbo
	 *            数据库对象
	 * @param vo
	 *            ui对象
	 * @return
	 */
	abstract T convertVoToDoForUpdate(Serializable dbo, Serializable vo);
	
	/**
	 * 仅仅当需要在service里
	 * 将vo转为do对象时才实现，当vo对象实现了entityconvertor时，可以不实现此方法，或调用create方法的实体本来就是do对象
	 * 
	 * @param dbo
	 *            数据库对象
	 * @param vo
	 *            ui对象
	 * @return
	 */
	abstract T convertVoToDoForCreate(Serializable vo);
}
