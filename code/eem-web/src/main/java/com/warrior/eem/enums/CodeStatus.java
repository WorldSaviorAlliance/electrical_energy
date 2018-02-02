package com.warrior.eem.enums;


/**
 * 返回状态status参数code信息配置
 * @author seangan
 *
 */
public enum CodeStatus {
	
	OK(0, "OK"),    // ok
	REDIRECT(20170001L, "重定向"), // redirect
	FORWARD(20170002L, "内部跳转"),   
	NOT_FOUND(20170003L, "未找到资源"),  // 404
	BAD_REQUEST(20170004L, "不合法的参数"), // 400
	UNAUTHORIZED(20170005L, "未登录"),  // 401
	FORBIDDEN(20170006L, "未授权"), // 403
	BAD_REQ_TYPE(20170007L, "请求类型不匹配"), // 405
	BAD_REQ_MEDIE_TYPE(20170008L, "请求多媒体类型不匹配"), // 415
	INTERNAL_SERVER_ERROR(20170009L, "系统发生异常，请联系管理员"), // 500
	COMMON_EXCEPTION(20170010L, "系统通用异常code，错误详情见异常描述");
	
	private long code;
	private String desc;
	
	CodeStatus(long code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public long getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
