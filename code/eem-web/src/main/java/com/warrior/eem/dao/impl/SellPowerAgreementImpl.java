package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.SellPowerAgreementDao;
import com.warrior.eem.entity.SellPowerAgreement;

@Repository
public class SellPowerAgreementImpl extends AbstractDaoImpl<SellPowerAgreement> implements SellPowerAgreementDao {

	@Override
	protected Class<SellPowerAgreement> getEntityClass() {
		return SellPowerAgreement.class;
	}

}
