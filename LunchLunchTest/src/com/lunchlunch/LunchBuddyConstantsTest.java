package com.lunchlunch;

import junit.framework.TestCase;

public class LunchBuddyConstantsTest extends TestCase {

	public void testServiceURL() throws Exception {
		assertEquals("http://10.0.2.2:3000", LunchBuddyConstants.SERVICE_URL);
	}
	public void testJSONDateFormat() throws Exception {
		assertEquals("yyyy-dd-MM'T'HH:mm:ss.SSS'Z'", LunchBuddyConstants.JSON_DATE_FORMAT
				);
	}
}
