package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 套餐的界面数据模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class ElectricityPackageVo implements Serializable {
	private static final long serialVersionUID = -8691675239860556570L;

	private long id;

	@FieldChecker(name = "套餐名称", minLen = 1, maxLen = 20)
	private String name;

	private String type;

	private int extPrice;

	private float minElecticity;

	private String desc;

	private boolean handled;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getExtPrice() {
		return extPrice;
	}

	public void setExtPrice(int extPrice) {
		this.extPrice = extPrice;
	}

	public float getMinElecticity() {
		return minElecticity;
	}

	public void setMinElecticity(float minElecticity) {
		this.minElecticity = minElecticity;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isHandled() {
		return handled;
	}

	public void setHandled(boolean handled) {
		this.handled = handled;
	}
}
