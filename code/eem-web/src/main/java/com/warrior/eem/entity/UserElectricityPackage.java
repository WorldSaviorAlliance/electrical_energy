package com.warrior.eem.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class UserElectricityPackage implements Serializable {
	private static final long serialVersionUID = -6870110725472858535L;

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Id
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

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof UserElectricityPackage)) {
			return false;
		}
		UserElectricityPackage that = (UserElectricityPackage) obj;
		return Objects.equals(user, that.user) && Objects.equals(pkg, that.pkg);
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, pkg);
	}
}
