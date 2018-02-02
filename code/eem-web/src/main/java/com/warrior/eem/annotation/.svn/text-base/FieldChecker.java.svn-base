package com.warrior.eem.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义实体属性验证器注解
 * @author seangan
 *
 */
@Documented
@Inherited
@Target(value={ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldChecker {
	
	/**
	 * 属性名描述
	 * @return
	 */
	public String name() default "";
	
	/**
	 * 是否为空
	 */
	public boolean isNull() default true;
	
	/**
	 * 字符文本最小长度
	 * @return
	 */
	public int minLen() default -1;
	
	/**
	 * 字符文本最大长度
	 * @return
	 */
	public int maxLen() default -1;
	
	/**
	 * 字符文本正则表达式
	 * .*(\\\"|'|<|>|&|%).*
	 * @return
	 */
	public String pattern() default "";
	
	/**
	 * 数字类型最小值
	 * @return
	 */
	public double minVal() default -999999999999d;
	
	/**
	 * 数字类型最小值
	 * @return
	 */
	public double maxVal() default 9999999999999d;
	
	/**
	 * 实体类自定义验证属性方法名 仅针对类作用域适用，配置在属性上不会起效果
	 * @return
	 */
	public String customCheckMethod() default "";
	
}
