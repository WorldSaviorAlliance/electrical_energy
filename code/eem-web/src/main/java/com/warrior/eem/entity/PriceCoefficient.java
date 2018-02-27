package com.warrior.eem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 电价系数实体
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Entity
@Table(name = "price_coefficient")
public class PriceCoefficient extends AbstractEntity {
	private static final long serialVersionUID = 48985656229981327L;

	@Column(name = "name")
	private String name;

	@Column(name = "coefficient")
	private int coefficient;

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
