package com.warrior.eem.interfaces;

import java.io.Serializable;

/**
 * 将页面ui vo对象转化为数据库Do对象，或者反之
 * @author seangan
 *
 */
public interface EntityConvertor {
	
	/**
	 * 将do对象转为vo对象
	 * @return
	 */
	Serializable convertToVo();
	
	
	/**
	 * 将vo对象转为do对象
	 * @return
	 */
	Serializable convertToDo();
	
	/**
	 * 合并参数实体属性
	 * @param targetEntity 需要合并到目标实体（实体更新可用）
	 * @return
	 */
	Serializable mergeProps(Serializable targetEntity);
}
