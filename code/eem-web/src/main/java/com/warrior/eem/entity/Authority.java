package com.warrior.eem.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.warrior.eem.entity.constant.ResourceOperation;

/**
 * 权限
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Entity
@Table(name = "authority")
public class Authority extends AbstractEntity {
	private static final long serialVersionUID = -2284317545043121017L;

	@Column(name = "resource")
	private String res;

	@Column(name = "op")
	@Enumerated(EnumType.ORDINAL)
	private ResourceOperation op;

	@OneToMany(mappedBy = "authority", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoleAuthority> owners = new ArrayList<>();

	public Authority() {
	}

	public Authority(String res, ResourceOperation op) {
		this.res = res;
		this.op = op;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public ResourceOperation getOp() {
		return op;
	}

	public void setOp(ResourceOperation op) {
		this.op = op;
	}

	public List<RoleAuthority> getOwners() {
		return owners;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (null == obj) {
			return false;
		}
		Authority other = (Authority) obj;
		return other.res.equals(res) && other.op == op;
	}

	@Override
	public int hashCode() {
		return Objects.hash(res, op);
	}
	
	public boolean hasPermission(String res, ResourceOperation op) {
		if (this.res.equals(res) && null != op) {
			return this.op.ordinal() >= op.ordinal();
		}
		return false;
	}
}
