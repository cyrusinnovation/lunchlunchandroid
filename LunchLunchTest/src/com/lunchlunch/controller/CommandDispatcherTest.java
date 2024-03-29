package com.lunchlunch.controller;

import com.lunchlunch.LunchBuddyTestCase;

public class CommandDispatcherTest extends LunchBuddyTestCase {
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
