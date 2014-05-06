package com.lunchlunch.controller;

import junit.framework.Test;
import junit.framework.TestSuite;

public class Suite {

	public static Test suite() {
		TestSuite suite = new TestSuite(Suite.class.getName());
		suite.addTestSuite(CommandDispatcherTest.class);
		suite.addTestSuite(CommandDispatcherProviderTest.class);
		suite.addTestSuite(StartActivityCommandTest.class);
		suite.addTestSuite(ActivityStarterTest.class);
		suite.addTestSuite(LoginCommandTest.class);
		return suite;
	}

}
