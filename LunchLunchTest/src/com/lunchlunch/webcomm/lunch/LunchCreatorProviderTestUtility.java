package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.ReflectionHelper;

public class LunchCreatorProviderTestUtility {

	private static LunchCreatorInterface original;

	public static void setLunchCreatorToProvide(LunchCreatorInterface creator)
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		original = LunchCreatorProvider.SINGLETON.provideLunchCreator();
		ReflectionHelper.setValueForPrivateField(creator, "lunchCreator",
				LunchCreatorProvider.class, LunchCreatorProvider.SINGLETON);
	}

	public static void resetLunchCreatorProvider()
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		ReflectionHelper.setValueForPrivateField(original, "lunchCreator",
				LunchCreatorProvider.class, LunchCreatorProvider.SINGLETON);
	}
}
