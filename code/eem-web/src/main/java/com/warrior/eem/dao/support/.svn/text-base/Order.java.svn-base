package com.warrior.eem.dao.support;

import java.util.ArrayList;
import java.util.List;

import com.warrior.eem.exception.EemException;

/**
 * 排序对象
 * 
 * @author seangan
 *
 */
public class Order implements Condition {

	private static final long serialVersionUID = 7044189012900420542L;

	/**
	 * 属性排序类型
	 * 
	 * @author seangan
	 *
	 */
	public enum Order_Type {
		ASC, DESC
	}

	private final List<String> propNames = new ArrayList<>();
	private final List<Order_Type> types = new ArrayList<>();

	public Order() {

	}

	public Order(List<String> propNames, List<Order_Type> types) {
		if (propNames == null || types == null || propNames.size() == 0 || propNames.size() != types.size()) {
			throw new EemException("排序参数有误");
		}
		this.propNames.addAll(propNames);
		this.types.addAll(types);
	}

	public List<String> getPropNames() {
		return propNames;
	}

	public List<Order_Type> getTypes() {
		return types;
	}

	/**
	 * 添加排序属性
	 * 
	 * @param orderName
	 * @param type
	 */
	public void addOrder(String orderName, Order_Type type) {
		if (orderName == null || orderName.trim().length() == 0) {
			throw new EemException("排序属性名不能为空");
		}
		propNames.add(orderName);
		types.add(type == null ? Order_Type.ASC : type);
	}

	@Override
	public String toSqlString() {
		// TODO 待完善
		return null;
	}

}
