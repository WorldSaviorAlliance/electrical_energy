package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.PriceCoefficientDao;
import com.warrior.eem.entity.PriceCoefficient;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Repository
public class PriceCoefficientDaoImple extends AbstractDaoImpl<PriceCoefficient> implements PriceCoefficientDao {

	@Override
	protected Class<PriceCoefficient> getEntityClass() {
		return PriceCoefficient.class;
	}

}
