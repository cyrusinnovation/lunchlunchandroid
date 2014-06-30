package com.lunchlunch.webcomm.person;

import org.json.JSONException;
import org.json.JSONObject;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.model.person.Person;
import com.lunchlunch.webcomm.ExplodingJSONObject;

public class PersonParserTest extends LunchBuddyTestCase {
	public void testImplementsInterface() throws Exception {
		assertEquals(PersonParserInterface.class,
				PersonParser.class.getInterfaces()[0]);
	}

	public void testIsASingleton() throws Exception {
		assertEquals(0, PersonParser.class.getConstructors().length);
		assertIsOfTypeAndGet(PersonParser.class, PersonParser.SINGLETON);
	}

	public void testWillParseJSONIntoPerson() throws Exception {
		JSONObject personJSON = new JSONObject();
		String expectedId = "3325dscvf32";
		String expectedFirstName = "Crag";
		String expectedLastName = "Sideiron";
		String expectedEmail = "gsi@spacy.net";

		personJSON.accumulate("_id", expectedId);
		personJSON.accumulate("firstName", expectedFirstName);
		personJSON.accumulate("lastName", expectedLastName);
		personJSON.accumulate("email", expectedEmail);

		Person parsedPerson = assertIsOfTypeAndGet(Person.class,
				PersonParser.SINGLETON.buildPersonFromJSON(personJSON));

		assertEquals(expectedId, parsedPerson.getId());
		assertEquals(expectedFirstName, parsedPerson.getFirstName());
		assertEquals(expectedLastName, parsedPerson.getLastName());
		assertEquals(expectedEmail, parsedPerson.getEmail());
	}

	public void testBuildJSONFromPerson() throws Exception {
		String expectedId = "3252362";
		String expectedFirstName = "Brock";
		String expectedLastName = "Samson";
		String expectedEmail = "bsam@osi.gov";
		JSONObject json = PersonParser.SINGLETON
				.buildJSONFromPerson(new Person(expectedId, expectedFirstName,
						expectedLastName, expectedEmail));
		
		assertEquals(expectedId, json.get("_id"));
		assertEquals(expectedFirstName, json.get("firstName"));
		assertEquals(expectedLastName, json.get("lastName"));
		assertEquals(expectedEmail, json.get("email"));
	}

	public void testJSONExceptionsWillYieldANullPerson() throws Exception {
		ExplodingJSONObject personJSON = new ExplodingJSONObject();
		assertEquals(NullPerson.NULL,
				PersonParser.SINGLETON.buildPersonFromJSON(personJSON));

	}

	public void testWillParseJSONThatIsMissingElementsWillYieldNullPerson()
			throws Exception {
		assertEquals(NullPerson.NULL,
				PersonParser.SINGLETON
						.buildPersonFromJSON(buildPersonSansField("_id")));
		assertEquals(NullPerson.NULL,
				PersonParser.SINGLETON
						.buildPersonFromJSON(buildPersonSansField("firstName")));
		assertEquals(NullPerson.NULL,
				PersonParser.SINGLETON
						.buildPersonFromJSON(buildPersonSansField("lastName")));
		assertEquals(NullPerson.NULL,
				PersonParser.SINGLETON
						.buildPersonFromJSON(buildPersonSansField("email")));
	}

	private JSONObject buildPersonSansField(String fieldToExclude)
			throws JSONException {
		JSONObject personJSON = new JSONObject();
		personJSON.accumulate("_id", "3325dscvf32");
		personJSON.accumulate("firstName", "Crag");
		personJSON.accumulate("lastName", "Sideiron");
		personJSON.accumulate("email", "gsi@spacymuty.net");

		personJSON.remove(fieldToExclude);
		return personJSON;
	}

}
