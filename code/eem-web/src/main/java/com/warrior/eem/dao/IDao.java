package com.warrior.eem.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import com.warrior.eem.dao.support.Condition;
import com.warrior.eem.dao.support.SqlRequest;

/**
 * base dao interface
 * 
 * @author seangan
 *
 */
public interface IDao<T> {

	/**
	 * 获取entitymanager
	 * 
	 * @return
	 */
	EntityManager getEntityManager();

	/**
	 * 获取某个实体
	 * 
	 * @param pk
	 * @return
	 */
	T getEntity(Serializable pk);

	/**
	 * 获取实体引用（如果缓存中没有，则不会立刻从数据库获取数据，自动生成一个只有pk属性的实体，当获取非pk属性才会去数据发送请求）
	 * 
	 * @param pk
	 * @return
	 */
	T getReference(Serializable pk);

	/**
	 * 创建do实体
	 * 
	 * @param obj
	 */
	void createDo(T obj);

	/**
	 * 更新实体
	 * 
	 * @param obj
	 */
	void updateDo(T obj);

	/**
	 * 移除主键对应的实体
	 * 
	 * @param pk
	 */
	void deleteDo(Serializable pk);

	/**
	 * 根据条件获取实体列表
	 * 
	 * @param cdt
	 */
	List<?> listDos(SqlRequest req);
	
	/**
	 * 通过sql语句获取实体
	 * @param sql
	 * @return
	 */
	List<?> listDosBySql(String sql);

	/**
	 * 根据条件统计实体个数
	 * 
	 * @param cdt
	 */
	long countDos(Condition cdt);

}
