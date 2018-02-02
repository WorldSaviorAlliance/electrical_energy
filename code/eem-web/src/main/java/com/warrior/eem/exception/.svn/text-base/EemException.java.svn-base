package com.warrior.eem.exception;

import com.warrior.eem.enums.CodeStatus;

/**
 * eem exception
 * @author seangan
 *
 */
public class EemException extends RuntimeException {

	private static final long serialVersionUID = 3508332405804472728L;

	private long code;
	private String mesage;
	
	public EemException() {
		
	}
	
	public EemException(String mesage) {
		super(mesage);
		this.mesage = mesage;
		this.code = CodeStatus.COMMON_EXCEPTION.getCode();
	}
	
	public EemException(long code, String message) {
		super(message);
		this.code = code;
		this.mesage = message;
	}

	public long getCode() {
		return code;
	}

	public String getMesage() {
		return mesage;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
}
