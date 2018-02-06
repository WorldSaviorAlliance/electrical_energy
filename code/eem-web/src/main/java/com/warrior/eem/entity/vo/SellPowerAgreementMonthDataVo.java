package com.warrior.eem.entity.vo;


import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;


/**
 * 售电合约月数据
 * 
 * @author seangan
 *
 */
public class SellPowerAgreementMonthDataVo implements Serializable {

	private static final long serialVersionUID = 7741791027666246628L;

	private Long sellPowerAgreementId;

	@FieldChecker(name = "一月", minLen = 18, maxLen = 164)
	private String january;

	@FieldChecker(name = "二月", minLen = 18, maxLen = 164)
	private String february;

	@FieldChecker(name = "三月", minLen = 18, maxLen = 164)
	private String march;

	@FieldChecker(name = "四月", minLen = 18, maxLen = 164)
	private String april;

	@FieldChecker(name = "五月", minLen = 18, maxLen = 164)
	private String may;

	@FieldChecker(name = "六月", minLen = 18, maxLen = 164)
	private String june;

	@FieldChecker(name = "七月", minLen = 18, maxLen = 164)
	private String july;

	@FieldChecker(name = "八月", minLen = 18, maxLen = 164)
	private String august;

	@FieldChecker(name = "九月", minLen = 18, maxLen = 164)
	private String september;

	@FieldChecker(name = "10月", minLen = 18, maxLen = 164)
	private String october;

	@FieldChecker(name = "11月", minLen = 18, maxLen = 164)
	private String november;

	@FieldChecker(name = "12月", minLen = 18, maxLen = 164)
	private String december;

	public SellPowerAgreementMonthDataVo() {

	}

	public Long getSellPowerAgreement() {
		return sellPowerAgreementId;
	}

	public Long getSellPowerAgreementId() {
		return sellPowerAgreementId;
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

	public void setSellPowerAgreementId(Long sellPowerAgreementId) {
		this.sellPowerAgreementId = sellPowerAgreementId;
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
