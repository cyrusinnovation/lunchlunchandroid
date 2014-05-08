package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.model.person.PersonInterface;

public class MockLunchHelper implements LunchHelperInterface {

	private PersonInterface personPassedIn;
	private LunchReceiver lunchReceiverPassedIn;

	@Override
	public void getLunches(PersonInterface person, LunchReceiver lunchReceiver) {
		this.personPassedIn = person;
		this.lunchReceiverPassedIn = lunchReceiver;

	}

	public LunchReceiver getLunchReceiverPassedIn() {
		return lunchReceiverPassedIn;
	}

	public PersonInterface getPersonPassedIn() {
		return personPassedIn;
	}
}
