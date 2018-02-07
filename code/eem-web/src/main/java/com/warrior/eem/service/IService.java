package com.warrior.eem.service;

import java.io.Serializable;

import com.warrior.eem.entity.vo.PageVo;

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
	 * 此方法需要注意：
	 * 如果e为vo对象，实现entityconvertor接口，将vo对象merge为dbo对象
	 * 如果e为vo对象，未实现entityconvertor接口，那么需要实现service中的convertVoToDoForUpdate方法
	 * 如果e为dbo对象，那么不需要检查直接更新保存
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
	public PageVo listEntities(Serializable... conditions);
	
	/**
	 * 获取列表
	 * @return
	 */
	public long countEntity(Serializable... conditions);
	
	
}
