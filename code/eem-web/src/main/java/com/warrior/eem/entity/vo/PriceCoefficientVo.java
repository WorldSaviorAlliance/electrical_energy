package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 电价系数的页面数据模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class PriceCoefficientVo implements Serializable {
	private static final long serialVersionUID = 4223171191146882731L;

	private long id;

	@FieldChecker(name = "系数名", minLen = 1, maxLen = 20)
	private String name;

	@FieldChecker(name = "系数值", minVal = 1)
	private int coefficient;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
}
