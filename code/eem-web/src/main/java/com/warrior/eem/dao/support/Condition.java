package com.warrior.eem.dao.support;

import java.io.Serializable;


/**
 * 自定义sql条件
 * 
 * @author seangan
 *
 */
public interface Condition extends Serializable {
	
	/**
	 * 将条件对象转换为sql条件语句
	 * @return
	 */
	String toSqlString();
}
