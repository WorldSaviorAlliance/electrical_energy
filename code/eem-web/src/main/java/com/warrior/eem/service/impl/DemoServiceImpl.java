package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.DemoDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.support.Joiner;
import com.warrior.eem.dao.support.MultiSelector;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.DemoDo;
import com.warrior.eem.entity.vo.DemoVo;
import com.warrior.eem.service.DemoService;

/**
 * 随便测试具体逻辑不必理会
 * 
 * @author seangan
 *
 */
@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao dDao;

	@Autowired
	private PowerCustomerDao pcdDao;

	@Transactional
	public void createDemo() {
		dDao.createDo(new DemoDo("ganbin"));
	}

	@Transactional(readOnly = true)
	public DemoVo getDemoVo(Serializable ID) {
		DemoDo dd = dDao.getEntity(1L);
		return dd == null ? null : new DemoVo(dd.getId(), dd.getName());
	}

	@Override
	public List<?> listDemoVo() {
		SqlRequest req = new SqlRequest();
		
		// 选择要返回的字段
		MultiSelector ms = new MultiSelector();
		ms.addSelectProp("id");
		ms.addSelectProp("creator.id");
		req.setSelect(ms);

		// 需要关联的表
		Joiner join = new Joiner();
		join.add("creator");
		req.setJoiner(join);

		// and equal
//		req.setCdt(new LogicalCondition(new SimpleCondition("name", Sql_Operator.EQ, "ganbin"), Sql_Operator.OR,
//				new SimpleCondition("id", Sql_Operator.EQ, 1)));
//
//		// like
//		req.setCdt(new SimpleCondition("name", Sql_Operator.LIKE, "ganbin%"));
//
//		// between
//		 req.setCdt(SimpleCondition.between("id", 1L, 10L));

		// in
		req.setCdt(SimpleCondition.in("id", new Object[] { 2, 3 }));
		List<?> ds = (List<?>) pcdDao.listDos(req);
		return ds;
	}

}
