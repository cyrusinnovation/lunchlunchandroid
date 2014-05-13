package com.lunchlunch.webcomm.findBuddy;

import com.lunchlunch.ReflectionHelper;

public class FindBuddyHelperProviderTestUtility {
	private static FindBuddyHelperInterface original;

	public static void setFindBuddyHelperToProvide(
			FindBuddyHelperInterface helper) throws IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException {
		original = FindBuddyHelperProvider.SINGLETON.provideFindBuddyHelper();
		ReflectionHelper.setValueForPrivateField(helper, "findBuddyHelper",
				FindBuddyHelperProvider.class,
				FindBuddyHelperProvider.SINGLETON);
	}

	public static void resetFindBuddyHelperProvider() throws IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException {
		ReflectionHelper.setValueForPrivateField(original, "findBuddyHelper",
				FindBuddyHelperProvider.class,
				FindBuddyHelperProvider.SINGLETON);
	}
}
