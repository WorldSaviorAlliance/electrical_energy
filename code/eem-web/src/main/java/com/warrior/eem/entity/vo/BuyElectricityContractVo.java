package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class BuyElectricityContractVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;

	private String name;
	
	private String number;
	
	private Long supplier;
	
	private String validYear;
	
	private String voltageLevel;
	
	private BigDecimal quantity;
	
	private String tradeType;
	
	private BigDecimal price;

	private String createTime;
	
	private Set<BuyContractUserInfoUpdateVo> userInfo = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getSupplier() {
		return supplier;
	}

	public void setSupplier(Long supplier) {
		this.supplier = supplier;
	}

	public String getValidYear() {
		return validYear;
	}

	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}

	public String getVoltageLevel() {
		return voltageLevel;
	}

	public void setVoltageLevel(String voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Set<BuyContractUserInfoUpdateVo> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Set<BuyContractUserInfoUpdateVo> userInfo) {
		this.userInfo = userInfo;
	}
	
}
