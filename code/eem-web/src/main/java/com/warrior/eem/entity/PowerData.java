package com.warrior.eem.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 电量数据
 * @author seangan
 *
 */
@Entity
@Table(name = "power_data")
public class PowerData extends AbstractEntity {
	
	private static final long serialVersionUID = -7384329405698576481L;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private PowerCustomer customer;
	
	// 用户户号
	@Column(name = "customer_no")
	private String customerNo;
	
	// 电表编号
	@Column(name = "em_no")
	private String emNo;
	
	// 月份
	@Column(name = "month")
	private String month;
	
	// 电压
	@Column(name = "voltage_type")
	private String voltageType;
	
	// 高峰用电量
	@Column(name = "peak_kwh")
	private BigDecimal peakKwh;
	
	// 平段用电量
	@Column(name = "flat_kwh")
	private BigDecimal flatKwh;
	
	// 低谷用电量
	@Column(name = "trough_kwh")
	private BigDecimal troughKwh;
	
	// 无功电量
	@Column(name = "idle_kwh")
	private BigDecimal idleKwh;
	
	// 交易品种
	@Column(name = "trade_type")
	private int tradeType;
	
	// 创建时间
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	
	// 创建人
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User creator;
	
	public PowerData() {
		
	}

	public PowerCustomer getCustomer() {
		return customer;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public String getEmNo() {
		return emNo;
	}

	public String getMonth() {
		return month;
	}

	public String getVoltageType() {
		return voltageType;
	}

	public BigDecimal getPeakKwh() {
		return peakKwh;
	}

	public BigDecimal getFlatKwh() {
		return flatKwh;
	}

	public BigDecimal getTroughKwh() {
		return troughKwh;
	}

	public BigDecimal getIdleKwh() {
		return idleKwh;
	}

	public int getTradeType() {
		return tradeType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreator() {
		return creator;
	}

	public void setCustomer(PowerCustomer customer) {
		this.customer = customer;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public void setEmNo(String emNo) {
		this.emNo = emNo;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setVoltageType(String voltageType) {
		this.voltageType = voltageType;
	}

	public void setPeakKwh(BigDecimal peakKwh) {
		this.peakKwh = peakKwh;
	}

	public void setFlatKwh(BigDecimal flatKwh) {
		this.flatKwh = flatKwh;
	}

	public void setTroughKwh(BigDecimal troughKwh) {
		this.troughKwh = troughKwh;
	}

	public void setIdleKwh(BigDecimal idleKwh) {
		this.idleKwh = idleKwh;
	}

	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}
