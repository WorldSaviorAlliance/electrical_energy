package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.entity.City;

@Repository("cityDao")
public class CityDaoImpl extends AbstractDaoImpl<City> {

	@Override
	protected Class<City> getEntityClass() {
		return City.class;
	}
}
