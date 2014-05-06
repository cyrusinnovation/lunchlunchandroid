package com.lunchlunch.model;

import com.lunchlunch.model.person.PersonInterface;

public class MockSession implements SessionInterface {

	private PersonInterface loggedInPerson;

	@Override
	public PersonInterface getUserLoggedIn() {
		return loggedInPerson;
	}

	@Override
	public void setLoggedInUser(PersonInterface loggedInPerson) {
		this.loggedInPerson = loggedInPerson;

	}

}
