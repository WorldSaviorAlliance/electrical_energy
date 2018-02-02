package com.warrior.eem.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.warrior.eem.entity.constant.UserStatus;

/**
 * 用户实体
 * @author cold_blade
 * @version 1.0.0
 */

@Entity
@Table(name = "user")
public class User implements Serializable{
	private static final long serialVersionUID = 7127750241730901860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "password")
	private String password;
	
	@Column(name = "user_status")
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@JoinColumn(name = "role_id")
	private Role role;
	
	@Column(name = "add_time")
	private Timestamp addTime;
	
	@Column(name = "last_login_time")
	private Timestamp lastLoginTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	

	//TODO:clutomer:
}
