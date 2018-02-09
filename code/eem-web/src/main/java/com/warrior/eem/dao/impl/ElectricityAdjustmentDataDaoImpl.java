package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.ElectricityAdjustmentDataDao;
import com.warrior.eem.entity.ElectricityAdjustmentData;

@Repository
public class ElectricityAdjustmentDataDaoImpl extends AbstractDaoImpl<ElectricityAdjustmentData>
		implements ElectricityAdjustmentDataDao {

	@Override
	protected Class<ElectricityAdjustmentData> getEntityClass() {
		return ElectricityAdjustmentData.class;
	}

}
