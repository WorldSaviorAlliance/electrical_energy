package com.warrior.eem.dao.support;

import java.util.ArrayList;
import java.util.List;

import com.warrior.eem.exception.EemException;

/**
 * 根据字段分组
 * @author seangan
 *
 */
public class GroupBy implements Condition {
	
	private static final long serialVersionUID = -4452681772502325108L;
	
	
	private final List<String> groupPropNames = new ArrayList<String>();
	
	public GroupBy() {
		
	}
	
	public void addGroupName(String propName) {
		if(propName == null || propName.trim().length() == 0) {
			throw new EemException("分组属性名不能为空");
		}
		groupPropNames.add(propName);
	}

	public List<String> getGroupPropNames() {
		return groupPropNames;
	}

	@Override
	public String toSqlString() {
		return null;
	}
}
