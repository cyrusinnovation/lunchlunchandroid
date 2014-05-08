package com.lunchlunch.webcomm.login;

import com.lunchlunch.ReflectionHelper;

public class LoginHelperProviderTestUtility {

	private static LoginHelperInterface original;

	public static void setLoginHelperToProvide(LoginHelperInterface loginHelper)
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		original = LoginHelperProvider.SINGLETON.provideLoginHelper();
		ReflectionHelper.setValueForPrivateField(loginHelper, "loginHelper",
				LoginHelperProvider.class, LoginHelperProvider.SINGLETON);
	}

	public static void resetLoginHelperProvider()
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		ReflectionHelper.setValueForPrivateField(original, "loginHelper",
				LoginHelperProvider.class, LoginHelperProvider.SINGLETON);
	}
}
