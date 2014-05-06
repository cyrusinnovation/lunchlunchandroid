package com.lunchlunch;

import junit.framework.TestCase;

public class LunchBuddyConstantsTest extends TestCase {

	public void testServiceURL() throws Exception {
		assertEquals("http://10.0.2.2:3000", LunchBuddyConstants.SERVICE_URL);
	}
}
