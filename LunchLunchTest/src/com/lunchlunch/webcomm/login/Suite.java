package com.lunchlunch.webcomm.login;

import junit.framework.Test;
import junit.framework.TestSuite;

public class Suite {

	public static Test suite() {
		TestSuite suite = new TestSuite(Suite.class.getName());
		suite.addTestSuite(LoginHelperTest.class);
		suite.addTestSuite(LoginHelperProviderTest.class);
		return suite;
	}

}
