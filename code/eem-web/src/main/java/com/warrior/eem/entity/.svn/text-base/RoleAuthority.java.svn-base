package com.warrior.eem.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 角色权限映射表
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Entity
@Table(name = "role_authority")
public class RoleAuthority implements Serializable {
	private static final long serialVersionUID = -2932201937677520365L;

	@Id
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@Id
	@ManyToOne
	@JoinColumn(name = "authority_id")
	private Authority authority;

	public RoleAuthority() {}
	
	public RoleAuthority(Role role, Authority authority) {
		this.role = role;
		this.authority = authority;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof RoleAuthority)) {
			return false;
		}
		RoleAuthority that = (RoleAuthority) obj;
		return Objects.equals(role, that.role) && Objects.equals(authority, that.authority);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(role, authority);
	}
}
