package com.lunchlunch.webcomm.person;

import org.json.JSONObject;

import com.lunchlunch.model.person.PersonInterface;

public class MockPersonParser implements PersonParserInterface {

	private JSONObject personJsonPassedIn;
	private PersonInterface personToReturn;

	@Override
	public PersonInterface buildPersonFromJSON(JSONObject personJson) {
		this.personJsonPassedIn = personJson;
		return personToReturn;
	}

	public JSONObject getPersonJsonPassedIn() {
		return personJsonPassedIn;
	}

	public void setPersonToReturn(PersonInterface personToReturn) {
		this.personToReturn = personToReturn;
	}
}
