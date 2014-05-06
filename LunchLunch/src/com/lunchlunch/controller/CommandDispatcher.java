package com.lunchlunch.controller;

public class CommandDispatcher implements CommandDispatcherInterface {

	@Override
	public void execute(Command command) {
		command.execute();
	}

}
