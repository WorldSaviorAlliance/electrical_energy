package com.warrior.eem.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * 权限
 * @author seangan
 *
 */
public class Permission implements Serializable {

	private static final long serialVersionUID = -2036229285266063549L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "name", length = 20)
	private String name;
	
	@ManyToMany(mappedBy = "ps")
	private Set<Role> roles;
	
	public Permission() {
		
	}
	
	public Permission(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
