package com.lunchlunch.webcomm.lunch.location;

import org.json.JSONException;
import org.json.JSONObject;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.lunch.location.Location;
import com.lunchlunch.model.lunch.location.NullLocation;
import com.lunchlunch.webcomm.ExplodingJSONObject;

public class LocationParserTest extends LunchBuddyTestCase {

	public void testImplementsInterface() throws Exception {
		assertEquals(LocationParserInterface.class,
				LocationParser.class.getInterfaces()[0]);
	}

	public void testIsASingleton() throws Exception {
		assertEquals(0, LocationParser.class.getConstructors().length);
		assertIsOfTypeAndGet(LocationParser.class, LocationParser.SINGLETON);
	}

	public void testWillParseJSONIntoLocation() throws Exception {
		JSONObject locationJSON = new JSONObject();
		String expectedAddress = "301 S State St.";
		String expectedName = "La Marsa";
		String expectedZipCode = "48104";

		locationJSON.accumulate("name", expectedName);
		locationJSON.accumulate("address", expectedAddress);
		locationJSON.accumulate("zipCode", expectedZipCode);

		Location location = assertIsOfTypeAndGet(Location.class,
				LocationParser.SINGLETON.parseLocation(locationJSON));
		assertEquals(expectedAddress, location.getAddress());
		assertEquals(expectedName, location.getName());
		assertEquals(expectedZipCode, location.getZipCode());

	}

	public void testJSONExceptionsWillYieldANullLocation() throws Exception {
		ExplodingJSONObject explodingJSON = new ExplodingJSONObject();
		assertEquals(NullLocation.NULL,
				LocationParser.SINGLETON.parseLocation(explodingJSON));

	}

	public void testWillParseJSONThatIsMissingElementsWillYieldNullLocation()
			throws Exception {
		assertEquals(NullLocation.NULL,
				LocationParser.SINGLETON
						.parseLocation(buildLocationSansField("name")));
		assertEquals(NullLocation.NULL,
				LocationParser.SINGLETON
						.parseLocation(buildLocationSansField("zipCode")));
		assertEquals(NullLocation.NULL,
				LocationParser.SINGLETON
						.parseLocation(buildLocationSansField("address")));
	}

	private JSONObject buildLocationSansField(String fieldToExclude)
			throws JSONException {
		JSONObject locationJSON = new JSONObject();
		locationJSON.accumulate("name", "Chela's Taqueria");
		locationJSON.accumulate("address", "693 S Maple Rd");
		locationJSON.accumulate("zipCode", "48103");

		locationJSON.remove(fieldToExclude);
		return locationJSON;
	}
}
