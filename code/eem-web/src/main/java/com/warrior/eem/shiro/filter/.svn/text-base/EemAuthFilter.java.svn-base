package com.warrior.eem.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;


/**
 * eem 认证filter
 * @author seangan
 *
 */
public class EemAuthFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (!isLoginRequest(request, response)) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String requestType = httpRequest.getHeader("Content-Type");
			if (requestType != null && requestType.contains("application/json")) {// 接口请求直接返回401错误，供其跳转到登录页面
				((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
				return false;
			}
		}
		return super.onAccessDenied(request, response);
	}

}
