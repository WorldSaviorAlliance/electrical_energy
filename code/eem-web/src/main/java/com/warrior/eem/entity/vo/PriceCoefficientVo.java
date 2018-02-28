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

	@FieldChecker(name = "peak", minVal = 0)
	private float peak;

	@FieldChecker(name = "flat", minVal = 0)
	private float flat;

	@FieldChecker(name = "trough", minVal = 0)
	private float trough;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
