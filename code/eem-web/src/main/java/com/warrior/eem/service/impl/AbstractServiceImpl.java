package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.annotation.EntityUniqueConstraint;
import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.support.Joiner;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.SimpleCondition;
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

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Transactional
	public void createEntity(Serializable e) {
		try {
			EntityValidator.checkEntity(e);
		} catch (IllegalAccessException | SecurityException e1) {
			throw new EemException("创建实体解析参数失败");
		}
		Serializable targetEntity = e;
		if (e instanceof EntityConvertor) {
			targetEntity = ((EntityConvertor) e).convertToDo();
		} else if (e.getClass().getName().contains("com.warrior.eem.entity.vo")) {
			targetEntity = convertVoToDoForCreate(e);
		}
		existSameUKValEntity(targetEntity);
		getDao().createDo((T) targetEntity);
	}

	/**
	 * 存在具有相同唯一约束实体
	 * 
	 * @param entity
	 */
	void existSameUKValEntity(Serializable entity) {
		Class<?> ec = entity.getClass();
		EntityUniqueConstraint euc = entity.getClass().getDeclaredAnnotation(EntityUniqueConstraint.class);
		if (euc != null) {
			String[] cols = euc.columns();
			if (cols == null || cols.length == 0) {
				return;
			}
			SqlRequest req = new SqlRequest();
			LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
			for (String col : cols) {
				try {
					Field f = ec.getDeclaredField(col.split("\\.")[0]);
					f.setAccessible(true);
					Object val = f.get(entity);
					if (f.getType().getName().contains("com.warrior.eem.entity")) {// 实体
						Joiner joiner = new Joiner();
						joiner.add(col.split("\\.")[0]);
						req.setJoiner(joiner);
						String propName = col.split("\\.")[1];
						f = "id".equals(propName) ? val.getClass().getSuperclass().getDeclaredField(propName)
								: val.getClass().getDeclaredField(propName);
						f.setAccessible(true);
						val = f.get(val);
					}
					sqlCdt = sqlCdt.and(SimpleCondition.equal(col, val));
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
						| IllegalAccessException e) {
					logger.error(e.getMessage(), e);
					throw new EemException("解析实体发生错误请联系管理员");
				}
			}
			req.setCdt(sqlCdt);
			if (countEntity(req) > 0) {
				throw new EemException(euc.errorMessage());
			}
		}
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
		if (dbEntity == null) {
			throw new EemException("未找到id（" + id + "）对应的信息");
		}
		boolean hasChangeUkProperty = isChangedOnAnyUKProperties(e, dbEntity);
		Serializable targetEntity = null;
		if (e instanceof EntityConvertor) {
			targetEntity = ((EntityConvertor) e).mergeProps(dbEntity);
		} else if (e.getClass().getName().contains("com.warrior.eem.entity.vo")) {
			targetEntity = convertVoToDoForUpdate(dbEntity, e);
		} else {
			targetEntity = e;
		}
		if (hasChangeUkProperty) {
			existSameUKValEntity(targetEntity);
		}
		getDao().updateDo((T) targetEntity);
	}

	/**
	 * 唯一约束属性是否有发生变化
	 * 
	 * @param uiData：
	 *            页面参数实体
	 * @param dbData：
	 *            数据库实体
	 * @return
	 */
	boolean isChangedOnAnyUKProperties(Serializable uiData, Serializable dbData) {
		EntityUniqueConstraint euc = dbData.getClass().getDeclaredAnnotation(EntityUniqueConstraint.class);
		if (euc != null) {
			String[] cols = euc.columns();
			if (cols == null || cols.length == 0) {
				return false;
			}
			for (String col : cols) {
				try {
					String[] propNames = col.split("\\.");
					Field uf = uiData.getClass().getDeclaredField(col.replace(".id", "Id"));
					Field df = propNames.length == 2 ? dbData.getClass().getSuperclass().getDeclaredField(propNames[1])
							: dbData.getClass().getDeclaredField(col);
					uf.setAccessible(true);
					df.setAccessible(true);
					if (uf != null && df != null) {
						if ((uf.get(uiData) != null && !uf.get(uiData).equals(df.get(dbData)))
								|| (uf.get(uiData) == null && df.get(dbData) != null)) {
							return true;
						}
					}
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
						| IllegalAccessException e) {
					logger.error(e.getMessage(), e);
					throw new EemException("解析实体发生错误请联系管理员!");
				}
			}
		}
		return false;
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
		if (conditions != null && conditions.length == 1 && conditions[0] instanceof SqlRequest) {
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
