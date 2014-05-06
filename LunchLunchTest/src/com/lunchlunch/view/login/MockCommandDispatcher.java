package com.lunchlunch.view.login;

import com.lunchlunch.controller.Command;
import com.lunchlunch.controller.CommandDispatcherInterface;

public class MockCommandDispatcher implements CommandDispatcherInterface {

	private Command lastCommandExecuted;

	@Override
	public void execute(Command command) {
		lastCommandExecuted = command;
	}
	public Command getLastCommandExecuted() {
		return lastCommandExecuted;
	}

}
