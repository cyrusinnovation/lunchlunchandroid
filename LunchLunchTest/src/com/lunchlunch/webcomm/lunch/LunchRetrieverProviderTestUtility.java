package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.ReflectionHelper;

public class LunchRetrieverProviderTestUtility {
	private static LunchRetrieverInterface original;

	public static void setLunchHelperToProvide(
			LunchRetrieverInterface lunchHelper) throws IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException {
		original = LunchRetrieverProvider.SINGLETON.provideLunchRetriever();
		ReflectionHelper.setValueForPrivateField(lunchHelper, "lunchHelper",
				LunchRetrieverProvider.class, LunchRetrieverProvider.SINGLETON);
	}

	public static void resetLunchHelperProvider()
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		ReflectionHelper.setValueForPrivateField(original, "lunchHelper",
				LunchRetrieverProvider.class, LunchRetrieverProvider.SINGLETON);
	}
}
