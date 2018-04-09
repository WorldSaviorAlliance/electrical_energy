package com.warrior.eem.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 实体自定义约束
 * @author seangan
 * @version 1.0.0
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface EntityUniqueConstraint {
	
	/**
	 * 唯一约束列数组
	 * @return
	 */
	public String[] columns() default {};
	
	/**
	 * 错误描述
	 * @return
	 */
	public String errorMessage() default "";
}
