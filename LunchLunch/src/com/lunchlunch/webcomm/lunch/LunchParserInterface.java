package com.lunchlunch.webcomm.lunch;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lunchlunch.model.lunch.LunchInterface;

public interface LunchParserInterface {

	public List<LunchInterface> parseLunches(JSONArray lunchJson);

	public JSONObject createLunchJSON(LunchInterface lunch);
}
