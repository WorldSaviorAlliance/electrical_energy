package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.PowerSupplier;

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
		return null;
	}
	
	@Override
	public Serializable mergeProps(Serializable targetEntity) {
		PowerSupplier ps = (PowerSupplier)targetEntity;
		ps.setAddress(this.getAddress());
		ps.setCity(this.getCity());
		ps.setContactEmail(this.getContactEmail());
		ps.setContactName(this.getContactName());
		ps.setContactPhone(this.getContactPhone());
		ps.setContactPosition(this.getContactPosition());
		ps.setFax(this.getFax());
		ps.setName(this.getName());
		ps.setNatureType(this.getNatureType());
		ps.setNickName(this.getNickName());
		ps.setProvince(this.getProvince());
		ps.setPowerType(this.getPowerType());
		ps.setCapacity(this.getCapacity());
		return ps;
	}
}
