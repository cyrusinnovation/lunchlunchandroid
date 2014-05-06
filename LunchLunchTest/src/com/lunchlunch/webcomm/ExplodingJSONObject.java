package com.lunchlunch.webcomm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExplodingJSONObject extends JSONObject {
	@Override
	public Object get(String name) throws JSONException {
		throw new JSONException("boom goes the dynamite");
	}

	@Override
	public boolean getBoolean(String name) throws JSONException {
		throw new JSONException("boom goes the dynamite");
	}

	@Override
	public double getDouble(String name) throws JSONException {
		throw new JSONException("boom goes the dynamite");
	}
	@Override
	public int getInt(String name) throws JSONException {
		throw new JSONException("boom goes the dynamite");
	}
	@Override
	public String getString(String name) throws JSONException {
		throw new JSONException("boom goes the dynamite");
	}
	@Override
	public JSONArray getJSONArray(String name) throws JSONException {
		throw new JSONException("boom goes the dynamite");
	}
	@Override
	public JSONObject getJSONObject(String name) throws JSONException {
		throw new JSONException("boom goes the dynamite");
	}
	@Override
	public long getLong(String name) throws JSONException {
		throw new JSONException("boom goes the dynamite");
	}
}

