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

	@Column(name = "peak")
	private float peak;

	@Column(name = "flat")
	private float flat;

	@Column(name = "trough")
	private float trough;

	public float getPeak() {
		return peak;
	}

	public void setPeak(float peak) {
		this.peak = peak;
	}

	public float getFlat() {
		return flat;
	}

	public void setFlat(float flat) {
		this.flat = flat;
	}

	public float getTrough() {
		return trough;
	}

	public void setTrough(float trough) {
		this.trough = trough;
	}
}
