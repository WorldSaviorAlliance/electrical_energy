package com.warrior.eem.service;

import java.util.List;

import com.warrior.eem.entity.vo.ProvinceVo;

/**
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public interface LocationService extends IService {
	List<ProvinceVo> queryAll();
	
	ProvinceVo getEntityVo(Long id);
}
