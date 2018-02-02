package com.warrior.eem.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 电力用户
 * @author seangan
 *
 */
public class PowerCustomer extends AbstractEntity {

	private static final long serialVersionUID = 8531812174486012714L;

	// 电力用户名称
	@Column(name = "name")
	private String name;
	
	// 简称
	@Column(name = "nick_name")
	private String nickName;
	
	// 所属行业
	@Column(name = "industry_type")
	private int industryType;
	
	// 所在省份
	@Column(name = "province")
	private String province;
	
	// 所在城市
	@Column(name = "city")
	private String city;
	
	// 地址
	@Column(name = "address")
	private String address;
	
	// 企业性质
	@Column(name = "nature_type")
	private int natureType;
	
	// 联系人
	@Column(name = "contact_name")
	private String contactName;
	
	// 联系人电话
	@Column(name = "contact_phone")
	private String contactPhone;
	
	// 联系人职位
	@Column(name = "contact_position")
	private String contactPosition;
	
	// 联系人邮箱
	@Column(name = "contact_email")
	private String contactEmail;
	
	// 传真
	@Column(name = "fax")
	private String fax;
	
	// 录入时间
	@Column(name = "create_time")
	private LocalTime createTime;
	
	// 录入人
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User creator;
	
	public PowerCustomer() {
		
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

	public LocalTime getCreateTime() {
		return createTime;
	}

	public User getCreator() {
		return creator;
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

	public void setCreateTime(LocalTime createTime) {
		this.createTime = createTime;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}
