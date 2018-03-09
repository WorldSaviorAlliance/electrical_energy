package com.warrior.eem.entity.vo;

import java.io.Serializable;

/**
 * 城市的界面数据模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public class CityVo implements Serializable {
	private static final long serialVersionUID = 4704215076688218789L;

	private long id;

	private String name;

	public CityVo() {
		this(-1, "");
	}

	public CityVo(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
