package com.warrior.eem.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 权限 
 * @author cold_blade
 * @version 1.0.0
 */

@Entity
@Table(name = "authority")
public class Authority implements Serializable {
	private static final long serialVersionUID = -2284317545043121017L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "func")
	private String func;
	
	@Column(name = "func_name")
	private String funcName;
	
	@OneToMany(mappedBy = "authority", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoleAuthority> owners = new ArrayList<>();

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public List<RoleAuthority> getOwners() {
		return owners;
	}
}
