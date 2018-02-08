
package com.warrior.eem.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 *
 * 购电合同用户映射实体
 *
 */
@Entity
@Table(name = "buy_electricity_contract_user_info")
public class BuyContractUserInfo extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private PowerCustomer customers;

	private BigDecimal tradeQuantity;

	public PowerCustomer getCustomers() {
		return customers;
	}

	public void setCustomers(PowerCustomer customers) {
		this.customers = customers;
	}

	public BigDecimal getTradeQuantity() {
		return tradeQuantity;
	}

	public void setTradeQuantity(BigDecimal tradeQuantity) {
		this.tradeQuantity = tradeQuantity;
	}

	@Override
	public String toString() {
		return "BuyContractUserInfo [customers=" + customers + ", tradeQuantity=" + tradeQuantity + "]";
	}
}
