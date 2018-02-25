package com.warrior.eem.dao.impl;

import org.springframework.stereotype.Repository;

import com.warrior.eem.dao.OperationLogDao;
import com.warrior.eem.entity.OperationLog;

/**
 * 操作日志的数据接口
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Repository
public class OperationLogDaoImpl extends AbstractDaoImpl<OperationLog>implements OperationLogDao {

	@Override
	protected Class<OperationLog> getEntityClass() {
		return OperationLog.class;
	}

}
