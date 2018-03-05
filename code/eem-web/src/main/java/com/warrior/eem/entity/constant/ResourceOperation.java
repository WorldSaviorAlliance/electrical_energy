package com.warrior.eem.entity.constant;

/**
 * 资源操作
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public enum ResourceOperation {
	READ("read"), WRITE("write"), COM_CONTROL("complete_control");

	private String descipt;

	private ResourceOperation(String descipt) {
		this.descipt = descipt;
	}

	@Override
	public String toString() {
		return this.descipt;
	}
}
