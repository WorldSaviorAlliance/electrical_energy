package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.BuyElectricityContractDao;
import com.warrior.eem.entity.BuyElectricityContract;

@Repository
public class BuyElectricityContractDaoImpl extends AbstractDaoImpl<BuyElectricityContract> implements BuyElectricityContractDao{

	@Override
	protected Class<BuyElectricityContract> getEntityClass() {
		return BuyElectricityContract.class;
	}

}
