package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.SellPowerAgreementMonthDataDao;
import com.warrior.eem.entity.SellPowerAgreementMonthData;

@Repository
public class SellPowerAgreementMonthDataImpl extends AbstractDaoImpl<SellPowerAgreementMonthData> implements SellPowerAgreementMonthDataDao {

	@Override
	protected Class<SellPowerAgreementMonthData> getEntityClass() {
		return SellPowerAgreementMonthData.class;
	}

}
