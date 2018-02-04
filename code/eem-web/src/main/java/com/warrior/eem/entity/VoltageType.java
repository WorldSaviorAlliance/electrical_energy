package com.warrior.eem.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 电压类型实体
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Entity
@Table(name = "voltage_type")
public class VoltageType extends BaseType {
	private static final long serialVersionUID = 6348349028429363507L;
}
