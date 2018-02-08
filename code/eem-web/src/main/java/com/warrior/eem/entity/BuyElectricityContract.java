package com.warrior.eem.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 购电合同实体
 * 
 */
@Entity
@Table(name = "buy_electricity_contract")
public class BuyElectricityContract extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String number;
	@Column(name = "valid_year")
	private String validYear;

	@Column(name = "attachment_name")
	private String attachmentName;
	@Column(name = "trade_electricity_quantity")
	private BigDecimal tradeQuantity;

	private BigDecimal price;

	@Column(name = "trade_type_id")
	private String tradeType;

	@Column(name = "voltage_type_id")
	private String voltageType;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User creator;
	
	@OneToOne
	@JoinColumn(name = "supplier_id")
	private PowerSupplier supplier;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "contract_id")
	private Set<BuyContractUserInfo> contractUserInfos = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PowerSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(PowerSupplier supplier) {
		this.supplier = supplier;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getValidYear() {
		return validYear;
	}

	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getVoltageType() {
		return voltageType;
	}

	public void setVoltageType(String voltageType) {
		this.voltageType = voltageType;
	}

	public BigDecimal getTradeQuantity() {
		return tradeQuantity;
	}

	public void setTradeQuantity(BigDecimal tradeQuantity) {
		this.tradeQuantity = tradeQuantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<BuyContractUserInfo> getContractUserInfos() {
		return contractUserInfos;
	}

	public void setContractUserInfos(Set<BuyContractUserInfo> contractUserInfos) {
		this.contractUserInfos = contractUserInfos;
	}

}
