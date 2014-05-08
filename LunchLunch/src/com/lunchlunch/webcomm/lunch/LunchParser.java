package com.lunchlunch.webcomm.lunch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lunchlunch.LunchBuddyConstants;
import com.lunchlunch.model.lunch.Lunch;
import com.lunchlunch.model.lunch.LunchInterface;
import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.webcomm.person.PersonParser;

public class LunchParser implements LunchParserInterface {

	public static final LunchParser SINGLETON = new LunchParser();

	private LunchParser() {
	}

	@Override
	public List<LunchInterface> parseLunches(JSONArray lunchesJSON) {
		ArrayList<LunchInterface> lunches = new ArrayList<LunchInterface>();

		for (int i = 0; i < lunchesJSON.length(); i++) {
			try {
				JSONObject lunchJSON = lunchesJSON.getJSONObject(i);
				JSONObject person1JSON = lunchJSON.getJSONObject("person1");
				JSONObject person2JSON = lunchJSON.getJSONObject("person2");
				SimpleDateFormat dateFormatter = new SimpleDateFormat(
						LunchBuddyConstants.JSON_DATE_FORMAT,
						Locale.getDefault());
				PersonInterface person1 = PersonParser.SINGLETON
						.buildPersonFromJSON(person1JSON);
				PersonInterface person2 = PersonParser.SINGLETON
						.buildPersonFromJSON(person2JSON);
				Date date = dateFormatter
						.parse(lunchJSON.getString("dateTime"));
				if (!person1.equals(NullPerson.NULL)
						&& !person2.equals(NullPerson.NULL)) {
					lunches.add(new Lunch(person1, person2, date));
				}
			} catch (JSONException | ParseException e) {
			}
		}
		return lunches;
	}

}
