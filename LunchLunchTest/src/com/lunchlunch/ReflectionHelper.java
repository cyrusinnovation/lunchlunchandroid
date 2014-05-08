package com.lunchlunch;

import java.lang.reflect.Field;

public class ReflectionHelper {
	public static void setValueForPrivateField(Object valueToSet,
			String fieldName, Class<?> classForReflection,
			Object objectOnWhichToSetValue) throws NoSuchFieldException,
			IllegalAccessException {
		Field field = classForReflection.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(objectOnWhichToSetValue, valueToSet);
	}

}
