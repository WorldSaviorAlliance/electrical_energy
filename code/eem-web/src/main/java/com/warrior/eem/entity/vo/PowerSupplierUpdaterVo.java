package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.PowerSupplier;
import com.warrior.eem.interfaces.EntityConvertor;

/**
 * 电力提供商vo更新对象
 * @author seangan
 *
 */
public class PowerSupplierUpdaterVo implements EntityConvertor, Serializable {

	private static final long serialVersionUID = 8531812174486012714L;
	
	@FieldChecker(name = "id", minVal = 1, maxVal = Long.MAX_VALUE)
	private long id;

	@FieldChecker(name = "电源商名称", minLen = 1, maxLen = 30)
	private String name;
	
	@FieldChecker(name = "简称", minLen = 1, maxLen =10)
	private String nickName;
	
	@FieldChecker(name = "电源类型", maxVal = 10)
	private int powerType; 
	
	@FieldChecker(name = "年均发电量", maxVal = Double.MAX_VALUE)
	private BigDecimal capacity;
	
	@FieldChecker(name = "所在省份", minLen = 1, maxLen = 10)
	private String province;
	
	@FieldChecker(name = "所在城市", minLen = 1, maxLen = 10)
	private String city;
	
	@FieldChecker(name = "地址", minLen = 1, maxLen = 100)
	private String address;
	
	@FieldChecker(name = "企业性质", maxVal = 10)
	private int natureType;
	
	@FieldChecker(name = "联系人", minLen = 1, maxLen = 10)
	private String contactName;
	
	@FieldChecker(name = "联系人电话", minLen = 1, maxLen = 64)
	private String contactPhone;
	
	@FieldChecker(name = "联系人职位", minLen = 0, maxLen = 10)
	private String contactPosition;
	
	@FieldChecker(name = "联系人邮箱", minLen = 0, maxLen = 30)
	private String contactEmail;
	
	@FieldChecker(name = "传真", minLen = 0, maxLen = 10)
	private String fax;
	
	public PowerSupplierUpdaterVo() {
	}

	public String getName() {
		return name;
	}

	public String getNickName() {
		return nickName;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public int getNatureType() {
		return natureType;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public String getContactPosition() {
		return contactPosition;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public String getFax() {
		return fax;
	}

	public int getPowerType() {
		return powerType;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setPowerType(int powerType) {
		this.powerType = powerType;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNatureType(int natureType) {
		this.natureType = natureType;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public void setContactPosition(String contactPosition) {
		this.contactPosition = contactPosition;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

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
