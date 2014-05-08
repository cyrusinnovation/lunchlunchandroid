package com.lunchlunch.webcomm.person;

import org.json.JSONObject;

import com.lunchlunch.model.person.PersonInterface;

public class MockPersonParser implements PersonParserInterface {

	private JSONObject personJsonPassedIn;
	private PersonInterface personToReturn;
	private PersonInterface personForBuildJSON;
	private JSONObject jsonToReturn;

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

	@Override
	public JSONObject buildJSONFromPerson(PersonInterface person) {
		this.personForBuildJSON = person;
		return jsonToReturn;
	}

	public PersonInterface getPersonForBuildJSON() {
		return personForBuildJSON;
	}

	public void setJsonToReturn(JSONObject jsonToReturn) {
		this.jsonToReturn = jsonToReturn;
	}
}
