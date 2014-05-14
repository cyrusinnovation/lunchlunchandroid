package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.ReflectionHelper;

public class LunchRetrieverProviderTestUtility {
	private static LunchRetrieverInterface original;

	public static void setLunchRetrieverToProvide(
			LunchRetrieverInterface lunchHelper) throws IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException {
		original = LunchRetrieverProvider.SINGLETON.provideLunchRetriever();
		ReflectionHelper.setValueForPrivateField(lunchHelper, "lunchRetriever",
				LunchRetrieverProvider.class, LunchRetrieverProvider.SINGLETON);
	}

	public static void resetLunchRetrieverProvider()
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		ReflectionHelper.setValueForPrivateField(original, "lunchRetriever",
				LunchRetrieverProvider.class, LunchRetrieverProvider.SINGLETON);
	}
}
