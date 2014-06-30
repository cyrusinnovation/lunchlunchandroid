package com.lunchlunch.model.lunch.location;

public class NullLocation implements LocationInterface {

	public static final NullLocation NULL = new NullLocation();

	private NullLocation() {
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getAddress() {
		return "";
	}

	@Override
	public String getZipCode() {
		return "";
	}

}
