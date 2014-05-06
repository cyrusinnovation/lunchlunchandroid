package com.lunchlunch.webcomm;

import junit.framework.Test;
import junit.framework.TestSuite;

public class Suite {

	public static Test suite() {
		TestSuite suite = new TestSuite(Suite.class.getName());
		suite.addTestSuite(HttpClientBuilderTest.class);
		return suite;
	}

}
