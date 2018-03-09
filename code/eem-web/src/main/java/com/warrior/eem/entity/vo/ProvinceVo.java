package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.warrior.eem.entity.City;

/**
 * 省份的界面数据模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public class ProvinceVo implements Serializable {
	private static final long serialVersionUID = -1712325976619538103L;

	private long id;

	private String name;

	private List<CityVo> cities;

	public ProvinceVo(long id, String name) {
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

	public List<CityVo> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		if (null == cities || cities.isEmpty()) {
			return;
		}
		this.cities = new ArrayList<>(cities.size());
		for (City city : cities) {
			this.cities.add(new CityVo(city.getId(), city.getName()));
		}
	}
}
