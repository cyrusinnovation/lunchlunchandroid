package com.lunchlunch.controller;

import com.lunchlunch.LunchTestCase;

public class CommandDispatcherProviderTest extends LunchTestCase {

	public void testProvidesCommandDispatcher() throws Exception {
		CommandDispatcherProvider provider = new CommandDispatcherProvider();
		assertIsOfTypeAndGet(CommandDispatcher.class,
				provider.provideCommandDispatcher());
	}
}
