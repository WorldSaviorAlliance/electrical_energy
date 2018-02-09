package com.warrior.eem.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "electricity_adjustment_data")
public class ElectricityAdjustmentData extends AbstractEntity implements Serializable {

	public enum AdjustmentType {
		ASC, DESC
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "customer_number")
	private String customerNumber;
	@Column(name = "agreement_number")
	private String contractNumber;
	@Column(name = "valid_year")
	private String validYear;
	private BigDecimal adjustment;

	@Enumerated(EnumType.ORDINAL)
	private AdjustmentType adjustmentType;
	private BigDecimal price;
	private String month;
	@Column(name = "create_time")
	private Date createTime;

	@OneToOne
	@JoinColumn(name = "customer_id")
	private PowerCustomer customer;

	@OneToOne
	@JoinColumn(name = "agreement_id")
	private SellPowerAgreement sellAgreement;

	private String tradeType;

	private String voltageType;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User creator;

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getValidYear() {
		return validYear;
	}

	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}

	public BigDecimal getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(BigDecimal adjustment) {
		this.adjustment = adjustment;
	}

	public AdjustmentType getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(AdjustmentType adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PowerCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(PowerCustomer customer) {
		this.customer = customer;
	}

	public SellPowerAgreement getSellAgreement() {
		return sellAgreement;
	}

	public void setSellAgreement(SellPowerAgreement sellAgreement) {
		this.sellAgreement = sellAgreement;
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

}
