package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.BuyContractUserInfoDao;
import com.warrior.eem.entity.BuyContractUserInfo;

@Repository
public class BuyContractUserInfoDaoImpl extends AbstractDaoImpl<BuyContractUserInfo> implements BuyContractUserInfoDao {

	@Override
	protected Class<BuyContractUserInfo> getEntityClass() {
		return BuyContractUserInfo.class;
	}

}
