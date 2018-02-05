package com.warrior.eem.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name = "province")
public class Province extends AbstractEntity {
	private static final long serialVersionUID = 4287677328080080278L;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private List<City> cities = new ArrayList<City>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public boolean addCity(City city) {
		if (cities.contains(city)) {
			return false;
		}
		cities.add(city);
		return true;
	}

	public boolean removeCity(City city) {
		return cities.remove(city);
	}
}
