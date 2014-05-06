package com.lunchlunch.webcomm.person;

import org.json.JSONException;
import org.json.JSONObject;

import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.model.person.Person;
import com.lunchlunch.model.person.PersonInterface;

public class PersonParser implements PersonParserInterface {

	public static final PersonParser SINGLETON = new PersonParser();

	private PersonParser() {
	}

	@Override
	public PersonInterface buildPersonFromJSON(JSONObject personJson) {
		try {
			return new Person(personJson.getString("_id"),
					personJson.getString("firstName"),
					personJson.getString("lastName"),
					personJson.getString("email"));
		} catch (JSONException e) {
			return NullPerson.NULL;
		}
	}

}
