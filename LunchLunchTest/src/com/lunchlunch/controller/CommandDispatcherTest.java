package com.lunchlunch.controller;

import com.lunchlunch.LunchTestCase;

public class CommandDispatcherTest extends LunchTestCase {
	public void testImplementsInterface() throws Exception {
		assertEquals(CommandDispatcherInterface.class,
				CommandDispatcher.class.getInterfaces()[0]);
	}

	public void testExecute() throws Exception {
		CommandDispatcher commandDispatcher = new CommandDispatcher();
		MockCommand command = new MockCommand();
		commandDispatcher.execute(command);
		assertTrue(command.executeCalled());
	}
}
