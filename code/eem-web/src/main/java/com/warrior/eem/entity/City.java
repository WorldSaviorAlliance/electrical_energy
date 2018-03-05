package com.warrior.eem.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City extends AbstractEntity {
	private static final long serialVersionUID = 3336654576156264880L;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Province province;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (null == obj) {
			return false;
		}
		City city = (City) obj;
		return name.equals(city.name) && province.getId() == city.province.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, province.getId());
	}
}
