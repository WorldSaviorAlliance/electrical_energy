package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.util.Date;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.interfaces.EntityConvertor;
import com.warrior.eem.shiro.session.EemSession;

/**
 * 电力客户vo创建对象
 * @author seangan
 *
 */
public class PowerCustomerVo implements EntityConvertor, Serializable {

	private static final long serialVersionUID = 8531812174486012714L;
	
	@FieldChecker(name = "电力用户名称", minLen = 1, maxLen = 30)
	private String name;
	
	@FieldChecker(name = "简称", minLen = 1, maxLen =10)
	private String nickName;
	
	@FieldChecker(name = "所属行业", maxVal = 10)
	private int industryType;
	
	@FieldChecker(name = "所在省份", minLen = 1, maxLen = 10)
	private String province;
	
	@FieldChecker(name = "所在城市", minLen = 1, maxLen = 10)
	private String city;
	
	@FieldChecker(name = "地址", minLen = 0, maxLen = 100)
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
	
	public PowerCustomerVo() {
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

	@Override
	public Serializable convertToVo() {
		return null;
	}

	@Override
	public Serializable convertToDo() {
		PowerCustomer pc = new PowerCustomer();
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
		pc.setCreateTime(new Date());
		pc.setCreator(EemSession.getCurrentUser());
		return pc;
	}

	@Override
	public Serializable mergeProps(Serializable targetEntity) {
		return null;
	}
}
