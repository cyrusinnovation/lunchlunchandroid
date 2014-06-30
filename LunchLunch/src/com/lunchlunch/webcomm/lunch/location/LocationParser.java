package com.lunchlunch.webcomm.lunch.location;

import org.json.JSONException;
import org.json.JSONObject;

import com.lunchlunch.model.lunch.location.Location;
import com.lunchlunch.model.lunch.location.LocationInterface;
import com.lunchlunch.model.lunch.location.NullLocation;

public class LocationParser implements LocationParserInterface {

	public static final LocationParser SINGLETON = new LocationParser();

	private LocationParser() {
	}

	@Override
	public LocationInterface parseLocation(JSONObject jsonObject) {

		try {
			return new Location(jsonObject.getString("name"),
					jsonObject.getString("address"),
					jsonObject.getString("zipCode"));
		} catch (JSONException e) {
			return NullLocation.NULL;
		}
	}

}
