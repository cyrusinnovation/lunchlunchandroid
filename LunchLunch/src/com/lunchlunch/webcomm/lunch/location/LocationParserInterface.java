package com.lunchlunch.webcomm.lunch.location;

import org.json.JSONObject;

import com.lunchlunch.model.lunch.location.LocationInterface;

public interface LocationParserInterface {

	public LocationInterface parseLocation(JSONObject jsonObject);
}
