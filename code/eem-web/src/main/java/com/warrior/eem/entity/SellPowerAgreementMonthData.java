package com.warrior.eem.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 售电合约
 * 
 * @author seangan
 *
 */
@Entity
@Table(name = "sell_power_agreement_month_data")
public class SellPowerAgreementMonthData extends AbstractEntity {

	private static final long serialVersionUID = 7741791027666246628L;

	@OneToOne
	@JoinColumn(name = "sell_agreement_id")
	private SellPowerAgreement sellPowerAgreement;

	@Column(name = "january")
	private String january;

	@Column(name = "february")
	private String february;

	@Column(name = "march")
	private String march;

	@Column(name = "april")
	private String april;

	@Column(name = "may")
	private String may;

	@Column(name = "june")
	private String june;

	@Column(name = "july")
	private String july;

	@Column(name = "august")
	private String august;

	@Column(name = "september")
	private String september;

	@Column(name = "october")
	private String october;

	@Column(name = "november")
	private String november;

	@Column(name = "december")
	private String december;

	public SellPowerAgreementMonthData() {

	}

	public SellPowerAgreement getSellPowerAgreement() {
		return sellPowerAgreement;
	}

	public String getJanuary() {
		return january;
	}

	public String getFebruary() {
		return february;
	}

	public String getMarch() {
		return march;
	}

	public String getApril() {
		return april;
	}

	public String getMay() {
		return may;
	}

	public String getJune() {
		return june;
	}

	public String getJuly() {
		return july;
	}

	public String getAugust() {
		return august;
	}

	public String getSeptember() {
		return september;
	}

	public String getOctober() {
		return october;
	}

	public String getNovember() {
		return november;
	}

	public String getDecember() {
		return december;
	}

	public void setSellPowerAgreement(SellPowerAgreement sellPowerAgreement) {
		this.sellPowerAgreement = sellPowerAgreement;
	}

	public void setJanuary(String january) {
		this.january = january;
	}

	public void setFebruary(String february) {
		this.february = february;
	}

	public void setMarch(String march) {
		this.march = march;
	}

	public void setApril(String april) {
		this.april = april;
	}

	public void setMay(String may) {
		this.may = may;
	}

	public void setJune(String june) {
		this.june = june;
	}

	public void setJuly(String july) {
		this.july = july;
	}

	public void setAugust(String august) {
		this.august = august;
	}

	public void setSeptember(String september) {
		this.september = september;
	}

	public void setOctober(String october) {
		this.october = october;
	}

	public void setNovember(String november) {
		this.november = november;
	}

	public void setDecember(String december) {
		this.december = december;
	}

}
