package com.lunchlunch.model.lunch.location;

import com.lunchlunch.LunchBuddyTestCase;

public class NullLocationTest extends LunchBuddyTestCase {
	public void testImplementsInterface() throws Exception {
		assertEquals(LocationInterface.class,
				NullLocation.class.getInterfaces()[0]);
	}

	public void testIsASingleton() throws Exception {
		assertEquals(0, NullLocation.class.getConstructors().length);
		assertIsOfTypeAndGet(NullLocation.class, NullLocation.NULL);
	}
	
	public void testBlanksForGets() throws Exception {
		assertEquals("", NullLocation.NULL.getAddress());
		assertEquals("", NullLocation.NULL.getName());
		assertEquals("", NullLocation.NULL.getZipCode());
	}
}
