package com.lunchlunch.webcomm.person;

import org.json.JSONObject;

import com.lunchlunch.model.person.PersonInterface;

public interface PersonParserInterface {
	PersonInterface buildPersonFromJSON(JSONObject personJson);

	JSONObject buildJSONFromPerson(PersonInterface person);
}
