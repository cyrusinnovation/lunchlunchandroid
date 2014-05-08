package com.lunchlunch.controller;

import com.lunchlunch.ReflectionHelper;

public class CommandDispatcherProviderTestUtility {
	private static CommandDispatcherInterface original;

	public static void setCommandDispatcherToProvide(
			CommandDispatcherInterface commandDispatcher)
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		original = CommandDispatcherProvider.SINGLETON
				.provideCommandDispatcher();
		ReflectionHelper.setValueForPrivateField(commandDispatcher,
				"commandDispatcher", CommandDispatcherProvider.class,
				CommandDispatcherProvider.SINGLETON);
	}

	public static void resetCommandDispatcherProvider()
			throws IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException {
		ReflectionHelper.setValueForPrivateField(original, "commandDispatcher",
				CommandDispatcherProvider.class,
				CommandDispatcherProvider.SINGLETON);
	}
}
