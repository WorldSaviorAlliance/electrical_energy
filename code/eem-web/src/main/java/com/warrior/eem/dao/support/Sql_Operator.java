package com.warrior.eem.dao.support;



import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Subquery;



/**
 * 自定义sql条件
 * 
 * @author seangan
 *
 */
public enum Sql_Operator {

	EQ("等于", "equal", new Class[] { Expression.class, Object.class }), 
	GT_NUM("数字大于", "gt", new Class[] { Expression.class, Number.class }), 
	GE_NUM("数字大于等于", "ge", new Class[] { Expression.class, Number.class }), 
	LT_NUM("数字小于", "lt", new Class[] { Expression.class, Number.class }), 
	LE_NUM("数字小于等于", "le", new Class[] { Expression.class, Number.class }), 
	GE("大于等于", "greaterThanOrEqualTo", new Class[]{ Expression.class, Comparable.class }),
	GT("大于", "greaterThan", new Class[]{ Expression.class, Comparable.class }),
	LE("小于等于", "lessThanOrEqualTo", new Class[]{ Expression.class, Comparable.class }),
	LT("小于", "lessThan", new Class[]{ Expression.class, Comparable.class }),
	OR("或者", "or", null), // 需要根据真实情况来获取参数个数 
	AND("并且", "and", null),// 需要根据真实情况来获取参数个数 
	IN("在某个范围内", "in", new Class[] { Expression.class, Object[].class }), 
	NOT_IN("不在某个范围内", "not in", new Class[] { Expression.class, Object[].class }),
	BETWEEN("在某个区间", "between", new Class[] { Expression.class, Comparable.class, Comparable.class }), 
	EXISTS("存在", "exists", new Class[] { Subquery.class }), // 待优化
	NOT_EQ("不等于", "notEqual", new Class[] { Expression.class, Object.class }), 
	NOT("非", "not", new Class[] { Expression.class }), 
	LIKE("模糊like匹配", "like", new Class[] { Expression.class, String.class });

	String optName;
	String dbMethodName;
	Class<?>[] methodParamClasses;

	Sql_Operator(String optName, String dbMethodName, Class<?>[] methodParamClasses) {
		this.optName = optName;
		this.dbMethodName = dbMethodName;
		this.methodParamClasses = methodParamClasses;
	}

	public String getOptName() {
		return optName;
	}

	public String getDbMethodName() {
		return dbMethodName;
	}

	public Class<?>[] getMethodParamClasses() {
		return methodParamClasses;
	}
}