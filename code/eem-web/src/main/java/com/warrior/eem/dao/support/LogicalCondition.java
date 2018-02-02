package com.warrior.eem.dao.support;

/**
 * 逻辑sql条件（or and)
 * 
 * @author seangan
 *
 */
public class LogicalCondition implements Condition {

	private static final long serialVersionUID = 4027306192277247381L;

	/**
	 * 左边条件
	 */
	private Condition lc;

	/**
	 * 操作
	 */
	private Sql_Operator operator;

	/**
	 * 右边条件
	 */
	private Condition rc;

	public LogicalCondition() {

	}

	public LogicalCondition(Condition lc, Sql_Operator opt, Condition rc) {
		this.lc = lc;
		this.rc = rc;
		this.operator = opt;
	}

	public Condition getLc() {
		return lc;
	}

	public Sql_Operator getOperator() {
		return operator;
	}

	public Condition getRc() {
		return rc;
	}

	public void setLc(Condition lc) {
		this.lc = lc;
	}

	public void setOperator(Sql_Operator operator) {
		this.operator = operator;
	}

	public void setRc(Condition rc) {
		this.rc = rc;
	}

	/**
	 * 恒false
	 * @return
	 */
	public static LogicalCondition emptyOfFalse() {
		return new LogicalCondition(new SimpleCondition("id", Sql_Operator.EQ, -1), Sql_Operator.AND,
				new SimpleCondition("id", Sql_Operator.EQ, -1));
	}
	
	/**
	 * 恒true
	 * @return
	 */
	public static LogicalCondition emptyOfTrue() {
		return new LogicalCondition(new SimpleCondition("id", Sql_Operator.GT, -1), Sql_Operator.AND,
				new SimpleCondition("id", Sql_Operator.GT, -1));
	}
	
	public LogicalCondition or(Condition cdt) {
		return new LogicalCondition(this, Sql_Operator.OR, cdt);
	}
	
	public LogicalCondition and(Condition cdt) {
		return new LogicalCondition(this, Sql_Operator.AND, cdt);
	}

	@Override
	public String toSqlString() {
		// TODO 待完善
		return null;
	}

}
