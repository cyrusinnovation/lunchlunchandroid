package com.lunchlunch;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		suite.addTest(com.lunchlunch.view.login.Suite.suite());
		suite.addTest(com.lunchlunch.model.person.Suite.suite());
		suite.addTest(com.lunchlunch.webcomm.Suite.suite());
		suite.addTest(com.lunchlunch.webcomm.person.Suite.suite());
		suite.addTest(com.lunchlunch.webcomm.login.Suite.suite());
		suite.addTest(com.lunchlunch.model.Suite.suite());
		suite.addTest(com.lunchlunch.controller.Suite.suite());
		return suite;
	}

}
