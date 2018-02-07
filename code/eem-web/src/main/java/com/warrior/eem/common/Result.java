package com.warrior.eem.common;

import java.io.Serializable;

import com.warrior.eem.enums.CodeStatus;


/**
 * 系统rest响应结果模板
 * 
 * @author seangan
 *
 * @param <T>
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 8163083503779183957L;

	private long code = CodeStatus.OK.getCode();

	private long count = 0;
	
	private String msg;

	private T data;

	public Result() {
	}

	public Result(long code, String msg) {
		this.code = code;
		this.count = -1;
		this.msg = msg;
	}

	public Result(long count, T data, String msg) {
		this.count = count;
		this.data = data;
		this.msg = msg;
	}

	public Result(long code, long count, T data, String msg) {
		this.code = code;
		this.count = count;
		this.data = data;
		this.msg = msg;
	}

	public static Result<Object> success() {  // 常用
		return new Result<Object>(0, null, "");
	}

	public static Result<Object> success(String msg) { // 少用
		return new Result<Object>(0, null, msg);
	}

	public static <T> Result<T> success(T data) { // 常用
		return new Result<T>(0, data, CodeStatus.OK.getDesc());
	}
	
	public static <T> Result<T> success(long count, T data) { // 常用
		return new Result<T>(count, data, CodeStatus.OK.getDesc());
	}

	public static Result<Object> failure(long errCode, String msg) { // 常用
		return new Result<Object>(errCode, 0, null, msg);
	}
	
	public static Result<Object> failure(String msg) { 
		return new Result<Object>(CodeStatus.COMMON_EXCEPTION.getCode(), 0, null, msg);
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
