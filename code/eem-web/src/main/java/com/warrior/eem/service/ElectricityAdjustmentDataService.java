package com.warrior.eem.service;

import com.warrior.eem.entity.vo.ElectricityAdjustmentDataUpdateVO;

public interface ElectricityAdjustmentDataService extends IService{
	
	void saveOrUpdateElectricityAdjustmentData(ElectricityAdjustmentDataUpdateVO vo);
	
}
