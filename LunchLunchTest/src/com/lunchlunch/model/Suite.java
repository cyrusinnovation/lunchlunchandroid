package com.lunchlunch.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class Suite {

	public static Test suite() {
		TestSuite suite = new TestSuite(Suite.class.getName());
		// $JUnit-BEGIN$
		suite.addTestSuite(LunchBuddySessionTest.class);
		// $JUnit-END$
		return suite;
	}

}
