package com.warrior.eem.dao.support;

import java.util.ArrayList;
import java.util.List;

import com.warrior.eem.exception.EemException;

/**
 * 需要查询的字段属性s
 * @author seangan
 *
 */
public class MultiSelector implements Condition {
	
	private static final long serialVersionUID = -322727138340530504L;
	
	/**
	 * 需要查询的属性名
	 */
	private final List<String> propNames = new ArrayList<String>();
	
	public MultiSelector() {
		
	}
	
	public MultiSelector(List<String> propNames) {
		if(propNames == null || propNames.size() == 0) {
			throw new EemException("初始化需要查询的列名不能为空");
		}
		this.propNames.addAll(propNames);
	}

	public List<String> getPropNames() {
		return propNames;
	}
	
	public void addSelectProp(String propName) {
		if(propName == null || propName.trim().length() == 0) {
			throw new EemException("属性名不能为空");
		}
		this.propNames.add(propName);
	}

	@Override
	public String toSqlString() {
		// TODO 待完善
		return null;
	}
	
}
