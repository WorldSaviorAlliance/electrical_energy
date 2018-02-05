package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.TradeTypeDao;
import com.warrior.eem.entity.TradeType;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Repository
public class TradeTypeDaoImpl extends AbstractDaoImpl<TradeType> implements TradeTypeDao {

	@Override
	protected Class<TradeType> getEntityClass() {
		return TradeType.class;
	}

}
