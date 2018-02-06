package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.PowerDataDao;
import com.warrior.eem.entity.PowerData;

@Repository
public class PowerDataDaoImpl extends AbstractDaoImpl<PowerData> implements PowerDataDao {

	@Override
	protected Class<PowerData> getEntityClass() {
		return PowerData.class;
	}

}
