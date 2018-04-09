package com.warrior.eem.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warrior.eem.annotation.EntityUniqueConstraint;
import com.warrior.eem.exception.EemException;

/**
 * 售电合约
 * 
 * @author seangan
 *
 */
@Entity
@Table(name = "sell_power_agreement")
@EntityUniqueConstraint(columns = { "name" }, errorMessage = "名称不能重复")
public class SellPowerAgreement extends AbstractEntity {

	/**
	 * 售电合约电价类型
	 * 
	 * @author seangan
	 *
	 */
	public enum Sell_Power_Price_Type {
		Normal("常规直购电量交易价格"), Support("精准扶持直购电量交易价格"), Replace("自备替代直购电交易价格"), Margin("富于电量交易价格");

		private String name;

		Sell_Power_Price_Type(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private static final long serialVersionUID = 7741791027666246628L;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private PowerCustomer customer;

	@Column(name = "customer_no")
	private String customerNo;

	@Column(name = "name")
	private String name; // 名称

	@Column(name = "number")
	private String No; // 编号

	@Column(name = "valid_year")
	private String validYear; // 有效年

	@Column(name = "attachment")
	private String attachment; // 附件路径

	@Column(name = "valtage_type")
	private String voltageType;

	@Column(name = "trade_power_quantity")
	private BigDecimal tradePowerQuantity;

	@Column(name = "norma_trade_price")
	private BigDecimal normalTradePrice;

	@Column(name = "support_trade_price")
	private BigDecimal supportTradePrice;

	@Column(name = "replace_trade_price")
	private BigDecimal replaceTradePrice;

	@Column(name = "margin_trade_price")
	private BigDecimal marginTradePrice;

	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User creator;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "month_data_id")
	private SellPowerAgreementMonthData monthData;

	public SellPowerAgreement() {

	}

	public PowerCustomer getCustomer() {
		return customer;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public String getName() {
		return name;
	}

	public String getNo() {
		return No;
	}

	public String getValidYear() {
		return validYear;
	}

	public String getAttachment() {
		return attachment;
	}

	public String getVoltageType() {
		return voltageType;
	}

	public BigDecimal getTradePowerQuantity() {
		return tradePowerQuantity;
	}

	public BigDecimal getNormalTradePrice() {
		return normalTradePrice;
	}

	public BigDecimal getSupportTradePrice() {
		return supportTradePrice;
	}

	public BigDecimal getReplaceTradePrice() {
		return replaceTradePrice;
	}

	public BigDecimal getMarginTradePrice() {
		return marginTradePrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreator() {
		return creator;
	}

	public SellPowerAgreementMonthData getMonthData() {
		return monthData;
	}

	public void setMonthData(SellPowerAgreementMonthData monthData) {
		this.monthData = monthData;
	}

	public void setCustomer(PowerCustomer customer) {
		this.customer = customer;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNo(String no) {
		No = no;
	}

	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public void setVoltageType(String voltageType) {
		this.voltageType = voltageType;
	}

	public void setTradePowerQuantity(BigDecimal tradePowerQuantity) {
		this.tradePowerQuantity = tradePowerQuantity;
	}

	public void setNormalTradePrice(BigDecimal normalTradePrice) {
		this.normalTradePrice = normalTradePrice;
	}

	public void setSupportTradePrice(BigDecimal supportTradePrice) {
		this.supportTradePrice = supportTradePrice;
	}

	public void setReplaceTradePrice(BigDecimal replaceTradePrice) {
		this.replaceTradePrice = replaceTradePrice;
	}

	public void setMarginTradePrice(BigDecimal marginTradePrice) {
		this.marginTradePrice = marginTradePrice;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * 生成售电合约单价
	 * 
	 * @param sellTypeName
	 * @return
	 */
	public BigDecimal createUnitPriceBySellType(String sellTypeName) {

		if (Sell_Power_Price_Type.Margin.getName().equals(sellTypeName)) {
			return this.getMarginTradePrice();
		} else if (Sell_Power_Price_Type.Normal.getName().equals(sellTypeName)) {
			return this.getNormalTradePrice();
		} else if (Sell_Power_Price_Type.Replace.getName().equals(sellTypeName)) {
			return this.getReplaceTradePrice();
		} else if (Sell_Power_Price_Type.Support.getName().equals(sellTypeName)) {
			return this.getSupportTradePrice();
		}
		throw new EemException("无效的交易类型：" + sellTypeName);
	}

}
