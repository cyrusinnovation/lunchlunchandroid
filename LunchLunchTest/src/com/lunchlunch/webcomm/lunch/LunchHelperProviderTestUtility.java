package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.ReflectionHelper;

public class LunchHelperProviderTestUtility {
	private static LunchHelperInterface original;

	public static void setLunchHelperToProvide(LunchHelperInterface lunchHelper)
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		original = LunchHelperProvider.SINGLETON.provideLunchHelper();
		ReflectionHelper.setValueForPrivateField(lunchHelper, "lunchHelper",
				LunchHelperProvider.class, LunchHelperProvider.SINGLETON);
	}

	public static void resetLunchHelperProvider()
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		ReflectionHelper.setValueForPrivateField(original, "lunchHelper",
				LunchHelperProvider.class, LunchHelperProvider.SINGLETON);
	}
}
