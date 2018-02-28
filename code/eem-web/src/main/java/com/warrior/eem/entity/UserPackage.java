package com.warrior.eem.entity;

import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户套餐映射实体
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Entity
@Table(name = "user_package")
public class UserPackage extends AbstractEntity {
	private static final long serialVersionUID = -6870110725472858535L;

	private User user;
	
	private Package pkg;
	
	private Timestamp addTime;
}
