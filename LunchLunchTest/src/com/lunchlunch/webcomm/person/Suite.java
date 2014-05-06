package com.lunchlunch.webcomm.person;

import junit.framework.Test;
import junit.framework.TestSuite;

public class Suite {

	public static Test suite() {
		TestSuite suite = new TestSuite(Suite.class.getName());
		suite.addTestSuite(PersonParserTest.class);
		return suite;
	}

}
