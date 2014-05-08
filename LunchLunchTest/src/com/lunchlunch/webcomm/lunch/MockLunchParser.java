package com.lunchlunch.webcomm.lunch;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.lunchlunch.model.lunch.LunchInterface;

public class MockLunchParser implements LunchParserInterface {

	private JSONArray lunchJSONPassedIn;
	private List<LunchInterface> lunchesToReturn = new ArrayList<LunchInterface>();

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
}
