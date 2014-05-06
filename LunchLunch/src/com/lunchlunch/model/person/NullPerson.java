package com.lunchlunch.model.person;

public class NullPerson implements PersonInterface {

	public static final NullPerson NULL = new NullPerson();
	private NullPerson() {
	}

	@Override
	public String getFirstName() {
		return "";
	}

	@Override
	public String getLastName() {
		return "";
	}

	@Override
	public String getEmail() {
		return "";
	}

	@Override
	public String getId() {
		return "";
	}

}
