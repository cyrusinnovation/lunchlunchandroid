package com.lunchlunch.webcomm.lunch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lunchlunch.LunchBuddyConstants;
import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.lunch.Lunch;
import com.lunchlunch.model.lunch.LunchInterface;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.model.person.Person;
import com.lunchlunch.model.person.PersonInterface;

public class LunchParserTest extends LunchBuddyTestCase {
	public void testImplementsInterface() throws Exception {
		assertEquals(LunchParserInterface.class,
				LunchParser.class.getInterfaces()[0]);
	}

	public void testIsASingleton() throws Exception {
		assertEquals(0, LunchParser.class.getConstructors().length);
		assertIsOfTypeAndGet(LunchParser.class, LunchParser.SINGLETON);
	}

	public void testWillParseJSONIntoLunches() throws Exception {
		JSONArray lunchesJSON = new JSONArray();
		Person porthos = new Person("id463463", "Porthos", "de Vallon",
				"pdv@gmail.com");
		Person athos = new Person("id23423432", "Athos", "Fere",
				"afere@yahoo.com");
		Person aramis = new Person("erhfkl43232423", "Aramis", "d'Herblay",
				"adherby@hotmail.com");
		String lunch1DateTimeString = "2017-11-01T20:00:00.000Z";
		String lunch2DateTimeString = "2013-04-11T14:00:00.000Z";
		String lunch3DateTimeString = "2019-02-15T10:00:00.000Z";
		lunchesJSON.put(buildLunchJSON(athos, porthos, lunch1DateTimeString));
		lunchesJSON.put(buildLunchJSON(aramis, porthos, lunch2DateTimeString));
		lunchesJSON.put(buildLunchJSON(aramis, athos, lunch3DateTimeString));

		List<LunchInterface> lunches = LunchParser.SINGLETON
				.parseLunches(lunchesJSON);
		assertEquals(3, lunches.size());
		SimpleDateFormat dateMaker = new SimpleDateFormat(
				LunchBuddyConstants.JSON_DATE_FORMAT, Locale.US);

		checkLunch(athos, porthos, dateMaker.parse(lunch1DateTimeString),
				lunches.get(0));
		checkLunch(aramis, porthos, dateMaker.parse(lunch2DateTimeString),
				lunches.get(1));
		checkLunch(aramis, athos, dateMaker.parse(lunch3DateTimeString),
				lunches.get(2));
	}

	public void testCreateLunchJSON() throws Exception {
		Person person1 = new Person("23525cxz", "Hank", "Venture",
				"hankinator@venture.net");
		Person person2 = new Person("vxfghsdfds", "Dean", "Venture",
				"leehontok@venture.net");
		Date lunchDate = new Date(235235235);
		Lunch lunch = new Lunch(person1, person2, lunchDate);
		SimpleDateFormat dateMaker = new SimpleDateFormat(
				LunchBuddyConstants.JSON_DATE_FORMAT, Locale.US);

		JSONObject lunchJson = LunchParser.SINGLETON.createLunchJSON(lunch);

		checkPersonJSON(person1, lunchJson.getJSONObject("person1"));
		checkPersonJSON(person2, lunchJson.getJSONObject("person2"));
		assertEquals(dateMaker.format(lunchDate),
				lunchJson.getString("dateTime"));

	}

	private void checkPersonJSON(Person person, JSONObject jsonObject)
			throws JSONException {
		assertEquals(person.getId(), jsonObject.getString("_id"));
		assertEquals(person.getFirstName(), jsonObject.getString("firstName"));
		assertEquals(person.getLastName(), jsonObject.getString("lastName"));
		assertEquals(person.getEmail(), jsonObject.getString("email"));
	}

	public void testWillSkipAnyLunchesWithBrokenJSON() throws Exception {

		JSONArray lunchesJSON = new JSONArray();
		Person heckle = new Person("id463463", "Heckle", "Hecklesworth",
				"pdv@gmail.com");
		Person jeckle = new Person("id23423432", "Jeckle", "Jeckyso",
				"afere@yahoo.com");
		String lunch1DateTimeString = "2019-02-15T10:00:00.000Z";
		lunchesJSON.put(buildLunchJSON(heckle, jeckle, lunch1DateTimeString));
		lunchesJSON.put(buildLunchWithBadPerson1());
		lunchesJSON.put(buildLunchWithBadPerson2());
		lunchesJSON.put(buildLunchWithBadDateTime());
		lunchesJSON.put(buildLunchSansField("person1"));
		lunchesJSON.put(buildLunchSansField("person2"));
		lunchesJSON.put(buildLunchSansField("dateTime"));

		List<LunchInterface> lunches = LunchParser.SINGLETON
				.parseLunches(lunchesJSON);
		assertEquals(1, lunches.size());
		SimpleDateFormat dateMaker = new SimpleDateFormat(
				LunchBuddyConstants.JSON_DATE_FORMAT, Locale.US);

		checkLunch(heckle, jeckle, dateMaker.parse(lunch1DateTimeString),
				lunches.get(0));
	}

	private void checkLunch(Person expectedPerson1, Person expectedPerson2,
			Date expectedDateTime, LunchInterface lunch) {
		assertEquals(expectedPerson1, lunch.getPerson1());
		assertEquals(expectedPerson2, lunch.getPerson2());
		assertEquals(expectedDateTime, lunch.getDateTime());

	}

	private JSONObject buildLunchJSON(PersonInterface person1,
			PersonInterface person2, String dateTimeString)
			throws JSONException {
		JSONObject person1JSON = buildPersonJSON(person1);
		JSONObject person2JSON = buildPersonJSON(person2);

		return buildLunchJSON(dateTimeString, person1JSON, person2JSON);
	}

	private JSONObject buildLunchWithBadPerson1() throws JSONException {
		return buildLunchJSON("2019-02-15T10:00:00.000Z",
				buildPersonSansField("firstName"),
				buildPersonJSON(new MockPerson()));
	}

	private JSONObject buildLunchWithBadPerson2() throws JSONException {
		return buildLunchJSON("2019-02-15T10:00:00.000Z",
				buildPersonJSON(new MockPerson()),
				buildPersonSansField("lastName"));

	}

	private JSONObject buildLunchWithBadDateTime() throws JSONException {
		return buildLunchJSON("wontparseasadate",
				buildPersonJSON(new MockPerson()),
				buildPersonJSON(new MockPerson()));
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

	private JSONObject buildLunchJSON(String dateTimeString,
			JSONObject person1JSON, JSONObject person2JSON)
			throws JSONException {
		JSONObject lunchJson = new JSONObject();
		lunchJson.accumulate("person1", person1JSON);
		lunchJson.accumulate("person2", person2JSON);
		lunchJson.accumulate("dateTime", dateTimeString);
		return lunchJson;
	}

	private JSONObject buildLunchSansField(String fieldToExclude)
			throws JSONException {
		JSONObject lunchJSON = buildLunchJSON("2019-02-15T10:00:00.000Z",
				buildPersonJSON(new MockPerson()),
				buildPersonJSON(new MockPerson()));
		lunchJSON.remove(fieldToExclude);
		return lunchJSON;
	}

	private JSONObject buildPersonJSON(PersonInterface person)
			throws JSONException {
		JSONObject personJSON = new JSONObject();
		personJSON.accumulate("_id", person.getId());
		personJSON.accumulate("firstName", person.getFirstName());
		personJSON.accumulate("lastName", person.getLastName());
		personJSON.accumulate("email", person.getEmail());
		return personJSON;
	}
}
