package com.warrior.eem.dao.support;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.JoinType;

public class Joiner implements Condition {

	public enum Join_Type {
		INNER(JoinType.INNER), LEFT(JoinType.LEFT), RIGHT(JoinType.LEFT);
		
		private JoinType joinType;
		
		Join_Type(JoinType joinType) {
			this.joinType = joinType;
		}

		public JoinType getJoinType() {
			return joinType;
		}
	}

	private static final long serialVersionUID = 2276525855336230691L;

	private String joinPorpName;
	
	private String alias;

	private Join_Type type = Join_Type.INNER;

	private final List<Joiner> js = new ArrayList<Joiner>();
	
	public Joiner() {
		
	}
	
	public Joiner(String joinPorpName) {
		this.joinPorpName = joinPorpName;
	}
	
	public Joiner(String joinPorpName, String alias) {
		this.joinPorpName = joinPorpName;
		this.alias = alias;
	}
	
	public Joiner(String joinPorpName, Join_Type type) {
		this.joinPorpName = joinPorpName;
		this.type = type;
	}
	
	public Joiner(String joinPorpName, String alias, Join_Type type) {
		this.joinPorpName = joinPorpName;
		this.alias = alias;
		this.type = type;
	}
	
	/**
	 * 默认内连接
	 * @param propName
	 */
	public void add(String propName) {
		this.getJs().add(new Joiner(propName));
	}

	public void add(String propName, Join_Type joinType) {
		this.getJs().add(new Joiner(propName, joinType));
	}

	public List<Joiner> getJs() {
		return js;
	}

	public String getJoinPorpName() {
		return joinPorpName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Join_Type getType() {
		return type;
	}

	public void setJoinPorpName(String joinPorpName) {
		this.joinPorpName = joinPorpName;
	}

	public void setType(Join_Type type) {
		this.type = type;
	}

	@Override
	public String toSqlString() {
		return null;
	}
}
