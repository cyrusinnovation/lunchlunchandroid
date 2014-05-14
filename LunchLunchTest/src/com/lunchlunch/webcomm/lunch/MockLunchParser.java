package com.lunchlunch.webcomm.lunch;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lunchlunch.model.lunch.LunchInterface;

public class MockLunchParser implements LunchParserInterface {

	private JSONArray lunchJSONPassedIn;
	private List<LunchInterface> lunchesToReturn = new ArrayList<LunchInterface>();
	private LunchInterface lunchUsedToCreateJSON;
	private JSONObject lunchJSONToReturn;

	@Override
	public List<LunchInterface> parseLunches(JSONArray lunchJson) {
		this.lunchJSONPassedIn = lunchJson;
		return lunchesToReturn;
	}

	public void setLunchesToReturn(List<LunchInterface> lunchesToReturn) {
		this.lunchesToReturn = lunchesToReturn;
	}

	public JSONArray getLunchJSONPassedIn() {
		return lunchJSONPassedIn;
	}

	@Override
	public JSONObject createLunchJSON(LunchInterface lunch) {
		this.lunchUsedToCreateJSON = lunch;
		return lunchJSONToReturn;
	}

	public void setLunchJSONToReturn(JSONObject lunchJSONToReturn) {
		this.lunchJSONToReturn = lunchJSONToReturn;
	}

	public LunchInterface getLunchUsedToCreateJSON() {
		return lunchUsedToCreateJSON;
	}
}
