package com.lunchlunch.model.lunch.location;

import com.lunchlunch.LunchBuddyTestCase;

public class LocationTest extends LunchBuddyTestCase {

	public void testImplementsInterface() throws Exception {
		assertEquals(LocationInterface.class, Location.class.getInterfaces()[0]);
	}
	public void testConstructorAndGets() throws Exception {
		String zipCode = "48104";
		String address = "422 Detroit St";
		String name = "Zingerman's";
		Location location = new Location(name, address, zipCode);
		assertEquals(name, location.getName());
		assertEquals(address, location.getAddress());
		assertEquals(zipCode, location.getZipCode());
	}
	public void testEqualsAndHashCode() throws Exception {
		String zipCode = "48104";
		String address = " 216 S 4th Ave";
		String name = "Banditos";
		
		Location originalLocation = new Location(name, address, zipCode);
		Location equalLocation = new Location(name, address, zipCode);
		Location differentName = new Location("Eastern Flame", address, zipCode);
		Location differentAddress = new Location(name, "231 5th Ave", zipCode);
		Location differentZipcode = new Location(name, address, "48322");
		
		checkEqualsAndHashCode(originalLocation, equalLocation, differentAddress, differentName, differentZipcode, null, new Object());
		
		
	}
}
