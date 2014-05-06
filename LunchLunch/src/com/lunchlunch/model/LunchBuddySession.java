package com.lunchlunch.model;

import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.model.person.PersonInterface;

public class LunchBuddySession implements SessionInterface {

	public static final SessionInterface SINGLETON = new LunchBuddySession();
	private PersonInterface userLoggedIn;

	private LunchBuddySession() {
		userLoggedIn = NullPerson.NULL;
	}

	@Override
	public PersonInterface getUserLoggedIn() {
		return userLoggedIn;
	}

	@Override
	public void setLoggedInUser(PersonInterface loggedInPerson) {
		userLoggedIn = loggedInPerson;
	}

}
