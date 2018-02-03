package com.warrior.eem.entity.vo;

import java.io.Serializable;


import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.PowerCustomer;

/**
 * 电力客户vo更新对象
 * @author seangan
 *
 */
public class PowerCustomerUpdaterVo extends PowerCustomerVo {

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
		return null;
	}
	
	@Override
	public Serializable mergeProps(Serializable targetEntity) {
		PowerCustomer pc = (PowerCustomer)targetEntity;
		pc.setAddress(this.getAddress());
		pc.setCity(this.getCity());
		pc.setContactEmail(this.getContactEmail());
		pc.setContactName(this.getContactName());
		pc.setContactPhone(this.getContactPhone());
		pc.setContactPosition(this.getContactPosition());
		pc.setFax(this.getFax());
		pc.setName(this.getName());
		pc.setNatureType(this.getNatureType());
		pc.setNickName(this.getNickName());
		pc.setProvince(this.getProvince());
		return pc;
	}
}
