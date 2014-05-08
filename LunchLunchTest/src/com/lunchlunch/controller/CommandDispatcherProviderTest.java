package com.lunchlunch.controller;

import com.lunchlunch.LunchTestCase;

public class CommandDispatcherProviderTest extends LunchTestCase {

	public void testIsASingleton() throws Exception {
		assertEquals(0,
				CommandDispatcherProvider.class.getConstructors().length);
		assertIsOfTypeAndGet(CommandDispatcherProvider.class,
				CommandDispatcherProvider.SINGLETON);
	}

	public void testProvidesCommandDispatcher() throws Exception {
		assertIsOfTypeAndGet(CommandDispatcher.class,
				CommandDispatcherProvider.SINGLETON.provideCommandDispatcher());
		assertSame(
				CommandDispatcherProvider.SINGLETON.provideCommandDispatcher(),
				CommandDispatcherProvider.SINGLETON.provideCommandDispatcher());
	}
}
