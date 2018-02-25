package com.warrior.eem.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 * 操作日志表
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Entity
@Table(name = "operation_log")
public class OperationLog extends AbstractEntity {
	private static final long serialVersionUID = 5224724942608380641L;

	@ManyToOne(fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@JoinColumn(name = "user_id")
	private User owner;

	@Column(name = "produce_time")
	private Timestamp produceTime;

	@Column(name = "content")
	private String content;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Timestamp getProduceTime() {
		return produceTime;
	}

	public void setProduceTime(Timestamp produceTime) {
		this.produceTime = produceTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
