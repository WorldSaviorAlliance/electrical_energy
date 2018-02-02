package com.warrior.eem.service;

import java.io.Serializable;
import java.util.List;

/**
 * 抽象service
 * @author seangan
 *
 */
public interface IService {
	
	/**
	 * 创建实体
	 * @param e：参数数据
	 */
	public void createEntity(Serializable e);
	
	/**
	 * 更新
	 * @param e
	 */
	public void updateEntity(Serializable e);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteEntity(Serializable id);
	
	/**
	 * 获取详情
	 * @param id
	 * @return
	 */
	public Serializable getEntity(Serializable id);
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<?> listEntities(Serializable... conditions);
	
	/**
	 * 获取列表
	 * @return
	 */
	public long countEntity(Serializable... conditions);
	
	
}
