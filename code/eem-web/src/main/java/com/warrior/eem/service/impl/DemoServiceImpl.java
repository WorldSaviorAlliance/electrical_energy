package com.warrior.eem.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.DemoDao;
import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.support.MultiSelector;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.DemoDo;
import com.warrior.eem.service.DemoService;

/**
 * 随便测试具体逻辑不必理会
 * 
 * @author seangan
 *
 */
@Service
public class DemoServiceImpl extends AbstractServiceImpl<DemoDo> implements DemoService {

	@Autowired
	private DemoDao dDao;

	@Override
	IDao<DemoDo> getDao() {
		return dDao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... condition) {
		SqlRequest req = new SqlRequest();

		// 选择要返回的字段
		MultiSelector ms = new MultiSelector();
		ms.addSelectProp("id");
		req.setSelect(ms);

//		// 需要关联的表
//		Joiner join = new Joiner();
//		join.add("creator");
//		req.setJoiner(join);

		// and equal
		// req.setCdt(new LogicalCondition(new SimpleCondition("name",
		// Sql_Operator.EQ, "ganbin"), Sql_Operator.OR,
		// new SimpleCondition("id", Sql_Operator.EQ, 1)));
		//
		// // like
		// req.setCdt(new SimpleCondition("name", Sql_Operator.LIKE,
		// "ganbin%"));
		//
		// // between
		// req.setCdt(SimpleCondition.between("id", 1L, 10L));

		// in
		req.setCdt(SimpleCondition.in("id", new Object[] { 2, 3 }));
		return req;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... condition) {
		SqlRequest req = new SqlRequest();

		// 选择要返回的字段
		MultiSelector ms = new MultiSelector();
		ms.addSelectProp("id");
		req.setSelect(ms);

		// and equal
		// req.setCdt(new LogicalCondition(new SimpleCondition("name",
		// Sql_Operator.EQ, "ganbin"), Sql_Operator.OR,
		// new SimpleCondition("id", Sql_Operator.EQ, 1)));
		//
		// // like
		// req.setCdt(new SimpleCondition("name", Sql_Operator.LIKE,
		// "ganbin%"));
		//
		// // between
		// req.setCdt(SimpleCondition.between("id", 1L, 10L));

		// in
		req.setCdt(SimpleCondition.in("id", new Object[] { 2, 3 }));
		return req;
	}

	@Override
	DemoDo convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
