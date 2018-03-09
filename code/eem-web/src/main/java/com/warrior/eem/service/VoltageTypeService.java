package com.warrior.eem.service;

import com.warrior.eem.entity.vo.BaseTypeVo;

/**
 * 电压等级的服务接口
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface VoltageTypeService extends IService {
	BaseTypeVo getEntityVo(Long id);
}
