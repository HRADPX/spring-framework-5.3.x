package com.hr.test;

import org.springframework.util.ClassUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-12
 */
public class sourceTest {

	public static void main(String[] args){

		Class<?>[] paramTypes = new Class<?>[]{Number.class};
		Object[] arguments = new Object[] {1};
		int typeDifferenceWeight = getTypeDifferenceWeight(paramTypes, arguments);
		System.out.println(typeDifferenceWeight);

	}

	public static int getTypeDifferenceWeight(Class<?>[] paramTypes, Object[] args) {
		int result = 0;
		for (int i = 0; i < paramTypes.length; i++) {
			// 如果构造参数的类型和给定的不同，返回 Integer.MAX_VALUE，表示最不符合
			if (!ClassUtils.isAssignableValue(paramTypes[i], args[i])) {
				return Integer.MAX_VALUE;
			}
			if (args[i] != null) {
				Class<?> paramType = paramTypes[i];
				Class<?> superClass = args[i].getClass().getSuperclass();
				while (superClass != null) {
					// 如果是父类，result + 2，当前是子类
					if (paramType.equals(superClass)) {
						result = result + 2;
						superClass = null;
					} else if (ClassUtils.isAssignable(paramType, superClass)) {
						result = result + 2;
						superClass = superClass.getSuperclass();
					} else {
						superClass = null;
					}
				}
				if (paramType.isInterface()) {
					result = result + 1;
				}
			}
		}
		return result;
	}

}
