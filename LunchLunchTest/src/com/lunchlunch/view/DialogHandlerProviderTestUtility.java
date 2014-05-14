package com.lunchlunch.view;

import com.lunchlunch.ReflectionHelper;

public class DialogHandlerProviderTestUtility {
	private static DialogHandlerInterface original;

	public static void setDialogHandlerToProvide(
			DialogHandlerInterface dialogHandlerInterface)
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		original = DialogHandlerProvider.SINGLETON.provideDialogHandler();

		ReflectionHelper.setValueForPrivateField(dialogHandlerInterface,
				"dialogHandler", DialogHandlerProvider.class,
				DialogHandlerProvider.SINGLETON);
	}

	public static void resetDialogHandlerProvider()
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		ReflectionHelper.setValueForPrivateField(original, "dialogHandler",
				DialogHandlerProvider.class, DialogHandlerProvider.SINGLETON);
	}
}
