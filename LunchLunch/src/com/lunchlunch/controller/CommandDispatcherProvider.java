package com.lunchlunch.controller;

public class CommandDispatcherProvider {

	public static final CommandDispatcherProvider SINGLETON = new CommandDispatcherProvider();
	private CommandDispatcherInterface commandDispatcher;

	private CommandDispatcherProvider() {
		commandDispatcher = new CommandDispatcher();
	}

	public CommandDispatcherInterface provideCommandDispatcher() {
		return commandDispatcher;
	}

}
