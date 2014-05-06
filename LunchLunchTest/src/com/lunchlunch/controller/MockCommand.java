package com.lunchlunch.controller;

public class MockCommand implements Command {

	private boolean executeCalled;

	public boolean executeCalled() {
		return executeCalled;
	}

	@Override
	public void execute() {
		executeCalled = true;
	}

}
