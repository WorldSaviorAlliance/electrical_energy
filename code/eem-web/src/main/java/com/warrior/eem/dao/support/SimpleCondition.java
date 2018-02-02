package com.warrior.eem.dao.support;

/**
 * 自定义sql条件(非 or and)
 * 
 * @author seangan
 *
 */
public class SimpleCondition implements Condition {

	private static final long serialVersionUID = 4027306192277247381L;

	private String propName;
	private Sql_Operator operator;
	private Object propVal;

	public SimpleCondition() {

	}

	public SimpleCondition(String propName, Sql_Operator operator, Object propVal) {
		this.propName = propName;
		this.operator = operator;
		this.propVal = propVal;
	}

	public static SimpleCondition empty() {
		return new SimpleCondition();
	}

	public static SimpleCondition in(String propName, Object... values) {
		return new SimpleCondition(propName, Sql_Operator.IN, values);
	}

	public static SimpleCondition equal(String propName, Object values) {
		return new SimpleCondition(propName, Sql_Operator.EQ, values);
	}

	public static SimpleCondition ge(String propName, Object values) {
		return new SimpleCondition(propName, Sql_Operator.GE, values);
	}

	public static SimpleCondition gt(String propName, Object values) {
		return new SimpleCondition(propName, Sql_Operator.GT, values);
	}

	public static SimpleCondition lt(String propName, Object values) {
		return new SimpleCondition(propName, Sql_Operator.LT, values);
	}

	public static SimpleCondition like(String propName, Object values) {
		return new SimpleCondition(propName, Sql_Operator.LIKE, values);
	}

	public static SimpleCondition exists(String propName, Object values) {
		return new SimpleCondition(propName, Sql_Operator.EXISTS, values);
	}

	public static SimpleCondition between(String propName, Object bgVal, Object endVal) {
		return new SimpleCondition(propName, Sql_Operator.BETWEEN, new Object[] { bgVal, endVal });
	}

	public static SimpleCondition not(String propName, Object values) {
		return new SimpleCondition(propName, Sql_Operator.BETWEEN, values);
	}

	public String getPropName() {
		return propName;
	}

	public Sql_Operator getOperator() {
		return operator;
	}

	public Object getPropVal() {
		return propVal;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public void setOperator(Sql_Operator operator) {
		this.operator = operator;
	}

	public void setPropVal(Object propVal) {
		this.propVal = propVal;
	}

	@Override
	public String toSqlString() {
		// 待完善
		return null;
	}

}
