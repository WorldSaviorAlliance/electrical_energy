package com.warrior.eem.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.exception.EemException;

/**
 * @author seangan
 * @description 参数检查工具类,只是针对注解fieldchecker做的实体有效性验证
 */
public class EntityValidator {

	/**
	 * 
	 * @author seangan
	 *
	 */
	enum PARAM_TYPE {

		/**
		 * 引用类型参数
		 */
		REFERENCE_PARAMETER("引用类型", -1),

		/**
		 * 字符串参数
		 */
		STRING("字符串", 0),

		/**
		 * 简单基本数字类型
		 */
		NUMBER_SIMPLE("简单数字", 1),

		/**
		 * 数字对象类型
		 */
		NUMBER_OBJECT("数字对象", 2),

		/**
		 * 时间数据类型
		 */
		DATE("时间", 3),

		/**
		 * 枚举类型
		 */
		ENUM("枚举", 4);

		private String name;
		private int value;

		PARAM_TYPE(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public int getValue() {
			return value;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setValue(int value) {
			this.value = value;
		}

	};

	protected static final String PRIMARY_KEY_NAME_id = "id";
	protected static final String PRIMARY_KEY_NAME_Id = "Id";

	@SuppressWarnings("rawtypes")
	/**
	 * 验证并初始化实体属性值
	 * 
	 * @param targetObj：要赋值的对象
	 * @param paramObj:
	 *            参数对象
	 * @param isUpdate：是否为更新
	 *            更新需要验证实体id 有bug暂不启用
	 */
	@Deprecated
	public static Object initEntityProps(Object targetObj, Object paramObj) {
		if (paramObj == null) {
			throw new EemException("构建实体参数不能为空");
		}
		Field[] fs = targetObj.getClass().getDeclaredFields();
		if (fs != null) {
			try {
				if (paramObj instanceof Map) {
					Map m = (Map) paramObj;
					if (((Map) paramObj).size() == 0) {
						throw new EemException("构建实体参数不能为空");
					}
					for (Field f : fs) {
						if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) {
							continue;
						}
						f.setAccessible(true);
						setPropertyVal(targetObj, f, m.get(f.getName()));
					}
				}
				// else if(paramObj instanceof AbstractEntity) {
				// checkEntity(paramObj);
				// for (Field f : fs) {
				// if (Modifier.isStatic(f.getModifiers()) ||
				// Modifier.isFinal(f.getModifiers())) {
				// continue;
				// }
				// f.setAccessible(true);
				// Field of = null;
				// Object val = null;
				// try {
				// of = paramObj.getClass().getDeclaredField(f.getName());
				// } catch (NoSuchFieldException e) {
				//
				// }
				// if (of != null) {
				// of.setAccessible(true);
				// val = of.get(paramObj);
				// }
				// f.set(targetObj, val);
				// }
				// }
			} catch (IllegalAccessException | SecurityException e) {
				throw new EemException("构建实体参数失败");
			}
		} else {
			throw new EemException("目标实体没有属性");
		}
		return targetObj;
	}

	/**
	 * 设置实体属性值 带有有效验证
	 * 
	 * @param targetObject
	 *            需要赋值的对象
	 * @param f
	 *            实体对应的属性
	 * @param obj
	 *            属性参数值
	 * @param isUpdate
	 *            是否为更新修改实体（true 更新实体 false添加实体）
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 */
	@SuppressWarnings("rawtypes")
	private static void setPropertyVal(Object targetObject, Field f, Object val)
			throws IllegalAccessException, SecurityException {
		if (Modifier.isStatic(f.getModifiers())) {
			return;
		}
		f.setAccessible(true);
		FieldChecker fc = f.getDeclaredAnnotation(FieldChecker.class);
		if (fc == null) {
			return;
		}
		Class<?> fClass = f.getType();
		String simpleName = fClass.getSimpleName();
		int customType = getCustomPrimitiveType(fClass);
		if (customType != PARAM_TYPE.REFERENCE_PARAMETER.getValue()) { // 简单数据类型
			setValForSimpleProp(targetObject, f, val, customType, simpleName);
		} else {// 引用类型
			if (val != null) {
				if (val instanceof Collection) {
					for (Object item : (Collection) val) {
						initEntityProps(null, item);
					}
				} else if (val instanceof Map) {
					Set items = ((Map) val).entrySet();
					for (Object item : items) {
						initEntityProps(null, item);
					}
				} else if (val instanceof Array) {
					for (Object item : (Object[]) val) {
						initEntityProps(null, item);
					}
				}
			} else {
				if (!fc.isNull()) {
					throw new EemException(fc.name() + "不能为空");
				}
			}
		}
	}

	/**
	 * 针对简单的数据类型属性赋值
	 * 
	 * @param targetObject
	 *            需要赋值的对象
	 * @param f
	 *            需要需要赋值的对象属性field
	 * @param param
	 *            参数
	 * @param customType
	 *            自定义简单数据类型
	 * @param paramTypeSimpleName
	 *            额外的简单参数类型名（针对数字类型有多种）
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private static void setValForSimpleProp(Object targetObject, Field f, Object param, int customType,
			String paramTypeSimpleName) throws IllegalArgumentException, IllegalAccessException {
		// 简单类型
		// 简单数据类型
		FieldChecker fc = f.getDeclaredAnnotation(FieldChecker.class);
		if (customType == PARAM_TYPE.STRING.getValue()) {
			String fv = (String) param;
			if (fv == null && (!fc.isNull() || fc.minLen() > 0)) {
				throw new EemException(fc.name() + "不能为空");
			}
			if (fv != null) {
				fv = fv.trim();
				if (fv.length() < fc.minLen() || fv.length() > fc.maxLen()) {
					throw new EemException(fc.name() + "文本长度必须在（" + fc.minLen() + "~" + fc.maxLen() + "）之间");
				}
				Pattern pattern = Pattern.compile(fc.pattern());
				Matcher matcher = pattern.matcher(fv);
				if (matcher.find()) {
					throw new EemException(fc.name() + "不能包含特殊字符");
				}
			}
			f.set(targetObject, fv);
		} else if (customType == PARAM_TYPE.DATE.getValue()) {
			String format = (fc.pattern() != null && fc.pattern().trim().length() > 0) ? fc.pattern() : "yyyy-MM-dd";
			Date fv = null;
			if (param != null) {
				try {
					fv = new SimpleDateFormat(format).parse(String.valueOf(param));
				} catch (ParseException | IllegalArgumentException e1) {
					throw new EemException(fc.name() + "必须为（" + format + "）格式");
				}
			}
			if (fv == null && !fc.isNull()) {
				throw new EemException(fc.name() + "不能为空");
			}
			if (fv != null && fc.pattern() != null && fc.pattern().trim().length() > 0) {
				try {
					new SimpleDateFormat(fc.pattern()).format(fv);
				} catch (Exception e) {
					throw new EemException(fc.name() + "必须满足" + fc.pattern() + "格式");
				}
			}
			f.set(targetObject, fv);
		} else if (customType == PARAM_TYPE.NUMBER_SIMPLE.getValue()
				|| customType == PARAM_TYPE.NUMBER_OBJECT.getValue()) {
			if (customType == PARAM_TYPE.NUMBER_OBJECT.getValue()) {
				if (param == null && !fc.isNull()) {
					throw new EemException(fc.name() + "不允许为null");
				}
			}
			if (param != null) {
				if ("double".equalsIgnoreCase(paramTypeSimpleName) || "float".equalsIgnoreCase(paramTypeSimpleName)
						|| "bigdecimal".equalsIgnoreCase(paramTypeSimpleName)) {
					try {
						double fv = 0;
						Object val = null;
						if ("bigdecimal".equalsIgnoreCase(paramTypeSimpleName)) {
							val = new BigDecimal(String.valueOf(param));
							fv = ((BigDecimal) param).doubleValue();
						} else if ("float".equalsIgnoreCase(paramTypeSimpleName)) {
							fv = Float.valueOf(String.valueOf(param));
							val = Float.valueOf(String.valueOf(param));
						} else {
							fv = Double.valueOf(String.valueOf(param));
							val = Double.valueOf(String.valueOf(param));
						}
						if (fv < fc.minVal() || fv > fc.maxVal()) {
							throw new EemException(fc.name() + "必须在（" + BigDecimal.valueOf(fc.minVal()).toString() + "-"
									+ BigDecimal.valueOf(fc.maxVal()) + "）范围内");
						}
						f.set(targetObject, val);
					} catch (NumberFormatException e) {
						throw new EemException("属性（" + fc.name() + "）必须为数字");
					}
				} else if ("long".equalsIgnoreCase(paramTypeSimpleName) || "int".equalsIgnoreCase(paramTypeSimpleName)
						|| "integer".equalsIgnoreCase(paramTypeSimpleName)) {
					Object val = null;
					if ("long".equalsIgnoreCase(paramTypeSimpleName)) {
						val = Long.valueOf(String.valueOf(param));
					} else if ("int".equalsIgnoreCase(paramTypeSimpleName)
							|| "integer".equalsIgnoreCase(paramTypeSimpleName)) {
						val = Integer.valueOf(String.valueOf(param));
					}
					long fv = Long.valueOf(String.valueOf(param));
					if (fv < fc.minVal() || fv > fc.maxVal()) {
						throw new EemException(fc.name() + "必须在（" + BigDecimal.valueOf(fc.minVal()).toString() + "-"
								+ BigDecimal.valueOf(fc.maxVal()) + "）范围内");
					}
					f.set(targetObject, val);
				}
			}
		}
	}

	/**
	 * 检查实体属性的有效性 需要对传入实体属性添加@FieldChecker注解才能对该实体验证
	 * 
	 * @param obj
	 *            需要验证的实体
	 * @param customCheckMethodparams
	 *            实体自定义验证方法需要传入的参数(可以不填写)
	 * 
	 * @throws IllegalAccessException
	 * @throws EemException
	 * @author seangan
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static void checkEntity(Object obj, Object... customCheckMethodparams)
			throws IllegalAccessException, SecurityException {
		if (obj == null) {
			throw new EemException("实体参数不能为空");
		}
		Class<?> objc = obj.getClass();
		int customType = getCustomPrimitiveType(objc);
		FieldChecker fc = null;
		Class<?> fClass = null;
		String simpleName = null;
		if (customType != PARAM_TYPE.REFERENCE_PARAMETER.getValue()) { // 简单数据类型
			if (customCheckMethodparams != null && customCheckMethodparams.length > 0) {
				fc = (FieldChecker) (customCheckMethodparams[0]);
			}
			checkSimpleParam(obj, fc, customType, objc.getSimpleName());
			return;
		}
		Field[] fs = objc.getDeclaredFields(); // 实体引用类型
		for (Field f : fs) {
			if (Modifier.isStatic(f.getModifiers())) {
				continue;
			}
			f.setAccessible(true);
			fc = f.getDeclaredAnnotation(FieldChecker.class);
			if (fc == null) {
				continue;
			}
			fClass = f.getType();
			simpleName = fClass.getSimpleName();
			customType = getCustomPrimitiveType(fClass);
			if (customType != PARAM_TYPE.REFERENCE_PARAMETER.getValue()) { // 简单数据类型
				checkSimpleParam(f.get(obj), fc, customType, simpleName);
			} else {// 引用类型
				Object fv = f.get(obj);
				if (fv != null) {
					if (fv instanceof Collection) {
						for (Object item : (Collection) fv) {
							checkEntity(item, fc);
						}
					} else if (fv instanceof Map) {
						Set items = ((Map) fv).entrySet();
						for (Object item : items) {
							checkEntity(item, fc);
						}
					} else if (fv instanceof Array) {
						for (Object item : (Object[]) fv) {
							checkEntity(item, fc);
						}
					}
				} else {
					if (!fc.isNull()) {
						throw new EemException(fc.name() + "不能为空");
					}
				}
			}
		}
		fc = objc.getAnnotation(FieldChecker.class);
		if (fc != null) {
			if (fc.customCheckMethod() != null && fc.customCheckMethod().trim().length() > 0) {
				Class<?>[] paramCs = null;
				if (customCheckMethodparams != null && customCheckMethodparams.length > 0) {
					int len = customCheckMethodparams.length;
					paramCs = new Class<?>[len];
					for (int i = 0; i < len; i++) {
						paramCs[i] = customCheckMethodparams[i].getClass();
					}
				}
				Method m = null;
				try {
					m = getDeclaredMethod(objc, fc.customCheckMethod(), paramCs);
				} catch (NoSuchMethodException e) {
					try {
						if (objc.getSuperclass() != null) {
							m = getDeclaredMethod(objc.getSuperclass(), fc.customCheckMethod(), paramCs);
						}
					} catch (NoSuchMethodException e1) {
						throw new EemException("未找到对应的方法");
					}
				}
				try {
					if (m != null) {
						m.setAccessible(true);
						if (paramCs != null) {
							m.invoke(obj, customCheckMethodparams);
						} else {
							m.invoke(obj);
						}
					}
				} catch (InvocationTargetException e) {
					if (e.getTargetException() instanceof EemException) {
						throw new EemException(e.getTargetException().getMessage());
					} else {
						throw new EemException("执行方法" + m.getName() + "报错");
					}
				}
			}
		}
	}

	/**
	 * 获取自定义属性类型
	 * 
	 * @param fClass
	 *            需要检查的class
	 * @return -1:不是简单类型（非数字字符） 0：表示String 1：表示简单的数字 2：表示数字类型对应的class
	 * 
	 */
	private static int getCustomPrimitiveType(Class<?> fClass) {
		int type = PARAM_TYPE.REFERENCE_PARAMETER.getValue();
		if (String.class.equals(fClass)) {
			type = PARAM_TYPE.STRING.getValue();
		} else if (fClass.isPrimitive()) {
			type = PARAM_TYPE.NUMBER_SIMPLE.getValue();
		} else if (getPrimitiveClasses().contains(fClass)) {
			type = PARAM_TYPE.NUMBER_OBJECT.getValue();
		} else if (Date.class.equals(fClass) || Time.class.equals(fClass) || java.sql.Date.class.equals(fClass)) {
			type = PARAM_TYPE.DATE.getValue();
		} else if (Enum.class.equals(fClass)) {
			type = PARAM_TYPE.ENUM.getValue();
		}
		return type;
	}

	/**
	 * 检查简单的数据类型
	 * 
	 * @param fvO
	 * @param fc
	 * @param customType
	 * @param paramTypeSimpleName
	 */
	private static void checkSimpleParam(Object fvO, FieldChecker fc, int customType, String paramTypeSimpleName) {
		// 简单类型
		// 简单数据类型
		if (customType == PARAM_TYPE.STRING.getValue()) {
			String fv = (String) fvO;
			if (fv == null && (!fc.isNull() || fc.minLen() > 0)) {
				throw new EemException(fc.name() + "不能为空");
			}
			if (fv != null) {
				fv = fv.trim();
				if (fv.length() < fc.minLen() || fv.length() > fc.maxLen()) {
					throw new EemException(fc.name() + "文本长度必须在（" + fc.minLen() + "~" + fc.maxLen() + "）之间");
				}
				if (fc.pattern() == null || fc.pattern().trim().length() == 0
						|| ".*(\\\"|'|<|>|&|%).*".equals(fc.pattern())) {
					Matcher matcher = Pattern.compile(".*(\\\"|'|<|>|&|%).*").matcher(fv);
					if (matcher.find()) {
						throw new EemException(fc.name() + "不能包含特殊字符");
					}
				} else {
					if (!Pattern.matches(fc.pattern(), fv)) {
						throw new EemException(fc.name() + "必须为" + fc.pattern());
					}
				}
			}
		} else if (customType == PARAM_TYPE.DATE.getValue()) {
			Date fv = (Date) fvO;
			if (fv == null && !fc.isNull()) {
				throw new EemException(fc.name() + "不能为空");
			}
			if (fv != null && fc.pattern() != null && fc.pattern().trim().length() > 0) {
				try {
					new SimpleDateFormat(fc.pattern()).format(fv);
				} catch (Exception e) {
					throw new EemException(fc.name() + "必须满足" + fc.pattern() + "格式");
				}
			}
		} else if (customType == PARAM_TYPE.NUMBER_SIMPLE.getValue()
				|| customType == PARAM_TYPE.NUMBER_OBJECT.getValue()) {
			if (customType == PARAM_TYPE.NUMBER_OBJECT.getValue()) {
				if (fvO == null && !fc.isNull()) {
					throw new EemException(fc.name() + "不允许为null");
				}
			}
			if (fvO != null) {
				if ("double".equalsIgnoreCase(paramTypeSimpleName) || "float".equalsIgnoreCase(paramTypeSimpleName)
						|| "bigdecimal".equalsIgnoreCase(paramTypeSimpleName)) {
					double fv = 0;
					if ("bigdecimal".equalsIgnoreCase(paramTypeSimpleName)) {
						fv = ((BigDecimal) fvO).doubleValue();
					} else {
						fv = Double.valueOf(String.valueOf(fvO));
					}
					if (fv < fc.minVal() || fv > fc.maxVal()) {
						throw new EemException(fc.name() + "（" + fv + "）必须在（" + BigDecimal.valueOf(fc.minVal()).toString() + "-"
								+ BigDecimal.valueOf(fc.maxVal()) + "）范围内");
					}
				} else if ("long".equalsIgnoreCase(paramTypeSimpleName) || "int".equalsIgnoreCase(paramTypeSimpleName)
						|| "integer".equalsIgnoreCase(paramTypeSimpleName)) {
					long fv = 0l;
					if (fvO != null) {
						fv = Long.valueOf(String.valueOf(fvO));
					}
					if (fv < fc.minVal() || fv > fc.maxVal()) {
						throw new EemException(fc.name() + "（" + fv + "）必须在（" + BigDecimal.valueOf(fc.minVal()).toString() + "-"
								+ BigDecimal.valueOf(fc.maxVal()) + "）范围内");
					}
				}
			}
		}
	}

	/**
	 * 简单数据类型object
	 * 
	 * @return
	 */
	private static List<Class<?>> getPrimitiveClasses() {
		List<Class<?>> cls = new ArrayList<Class<?>>();
		cls.add(String.class);
		cls.add(Integer.class);
		cls.add(Long.class);
		cls.add(BigDecimal.class);
		cls.add(Float.class);
		cls.add(Double.class);
		cls.add(Byte.class);
		cls.add(Boolean.class);
		cls.add(Character.class);
		return cls;
	}

	private static Method getDeclaredMethod(Class<?> objc, String mName, Class<?>... paramCs)
			throws NoSuchMethodException {
		if (paramCs != null) {
			return objc.getDeclaredMethod(mName, paramCs);
		} else {
			return objc.getDeclaredMethod(mName);
		}
	}
}
