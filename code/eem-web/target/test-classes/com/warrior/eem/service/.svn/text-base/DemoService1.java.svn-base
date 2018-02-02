package com.warrior.eem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.DemoDao;
import com.warrior.eem.entity.DemoDo;


/**
 * 暂时不要使用 有bug
 * @author seangan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/eem-context.xml")
public class DemoService1 {
	
	@Autowired
	private DemoDao demoDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() {
		demoDao.createDo(new DemoDo("demo"));
	}
	
	@Test
	@Transactional
	public void testUpdate() {
		try {
			demoDao.updateDo(new DemoDo(1l, "demo"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	public void testSelect() {
		Object d = demoDao.getEntity(0l);
		d = demoDao.getEntity(1l);
	}
}
