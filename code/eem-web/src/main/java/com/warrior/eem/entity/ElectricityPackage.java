package com.warrior.eem.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.warrior.eem.entity.constant.ElectricityPackageType;
import com.warrior.eem.entity.vo.ElectricityPackageVo;

/**
 * 套餐实体
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Entity
@Table(name = "electricity_package")
public class ElectricityPackage extends AbstractEntity {
	private static final long serialVersionUID = -7297933367668416632L;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	@Enumerated(EnumType.ORDINAL)
	private ElectricityPackageType type;

	@Column(name = "expect_price")
	private int extPrice;

	@Column(name = "min_electricity")
	private float minElecticity;

	@Column(name = "desc")
	private String desc;

	@OneToMany(mappedBy = "pkg", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserElectricityPackage> owners = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ElectricityPackageType getType() {
		return type;
	}

	public void setType(ElectricityPackageType type) {
		this.type = type;
	}

	public int getExtPrice() {
		return extPrice;
	}

	public void setExtPrice(int extPrice) {
		this.extPrice = extPrice;
	}

	public float getMinElecticity() {
		return minElecticity;
	}

	public void setMinElecticity(float minElecticity) {
		this.minElecticity = minElecticity;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<UserElectricityPackage> getOwners() {
		return owners;
	}

	public ElectricityPackageVo convert() {
		ElectricityPackageVo vo = new ElectricityPackageVo();
		vo.setId(getId());
		vo.setName(name);
		vo.setType(type.toString());
		vo.setExtPrice(extPrice);
		vo.setMinElecticity(minElecticity);
		vo.setDesc(desc);
		return vo;
	}
}
