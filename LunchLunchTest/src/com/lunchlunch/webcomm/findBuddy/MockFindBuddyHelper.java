package com.lunchlunch.webcomm.findBuddy;

import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.webcomm.person.PersonReceiver;

public class MockFindBuddyHelper implements FindBuddyHelperInterface {

	private PersonInterface buddySeekerPassedToFindLunchBuddy;
	private PersonReceiver personReceiverPassedToFindLunchBuddy;
	private boolean findLunchBuddyCalled;

	@Override
	public void findLunchBuddy(PersonInterface buddySeeker,
			PersonReceiver personReceiver) {
		findLunchBuddyCalled = true;
		this.buddySeekerPassedToFindLunchBuddy = buddySeeker;
		this.personReceiverPassedToFindLunchBuddy = personReceiver;

	}

	public PersonInterface getBuddySeekerPassedToFindLunchBuddy() {
		return buddySeekerPassedToFindLunchBuddy;
	}

	public PersonReceiver getPersonReceiverPassedToFindLunchBuddy() {
		return personReceiverPassedToFindLunchBuddy;
	}

	public boolean wasFindLunchBuddyCalled() {
		return findLunchBuddyCalled;
	}
}
