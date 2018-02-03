package com.warrior.eem.entity.vo;

import java.io.Serializable;


import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.PowerCustomer;

/**
 * 电力提供商vo更新对象
 * @author seangan
 *
 */
public class PowerSupplierUpdaterVo extends PowerSupplierVo {

	private static final long serialVersionUID = 8531812174486012714L;
	
	@FieldChecker(name = "id", minVal = 1, maxVal = Long.MAX_VALUE)
	private long id;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Serializable convertToVo() {
		return null;
	}

	@Override
	public Serializable convertToDo() {
		PowerCustomer pc = (PowerCustomer)super.convertToDo();
		pc.setId(this.getId());
		return pc;
	}
	
	@Override
	public Serializable mergeProps(Serializable targetEntity) {
		return null;
	}
}
