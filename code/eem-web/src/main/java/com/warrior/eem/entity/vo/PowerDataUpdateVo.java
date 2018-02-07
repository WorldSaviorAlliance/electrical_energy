package com.warrior.eem.entity.vo;


import com.warrior.eem.annotation.FieldChecker;

/**
 * 电量更新vo
 * 
 * @author seangan
 *
 */
public class PowerDataUpdateVo extends PowerDataVo {

	private static final long serialVersionUID = -5716740087717124317L;

	@FieldChecker(name = "id", minLen = 1)
	private String id;

	public PowerDataUpdateVo() {

	}

}
