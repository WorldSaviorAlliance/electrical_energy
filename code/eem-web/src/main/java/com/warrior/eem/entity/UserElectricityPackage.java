package com.warrior.eem.entity;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户套餐映射实体
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Entity
@Table(name = "user_electricity_package")
public class UserElectricityPackage extends AbstractEntity {
	private static final long serialVersionUID = -6870110725472858535L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "pkg_id")
	private ElectricityPackage pkg;

	@Column(name = "add_time")
	private Timestamp addTime;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ElectricityPackage getPkg() {
		return pkg;
	}

	public void setPkg(ElectricityPackage pkg) {
		this.pkg = pkg;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
}
