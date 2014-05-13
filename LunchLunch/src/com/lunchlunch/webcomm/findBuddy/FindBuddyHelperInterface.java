package com.lunchlunch.webcomm.findBuddy;

import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.webcomm.person.PersonReceiver;

public interface FindBuddyHelperInterface {

	public void findLunchBuddy(PersonInterface buddySeeker,
			PersonReceiver personReceiver);
}
