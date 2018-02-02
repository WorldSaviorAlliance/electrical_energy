package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.DemoDao;
import com.warrior.eem.entity.DemoDo;

@Repository
public class DemoDaoImpl extends AbstractDaoImpl<DemoDo> implements DemoDao {

	@Override
	protected Class<DemoDo> getEntityClass() {
		return DemoDo.class;
	}

}
