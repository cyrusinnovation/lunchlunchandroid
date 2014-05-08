package com.lunchlunch.model;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.model.person.NullPerson;

public class LunchBuddySessionTest extends LunchBuddyTestCase {

	public void testIsSingleton() throws Exception {
		assertEquals(0, LunchBuddySession.class.getConstructors().length);
		assertIsOfTypeAndGet(LunchBuddySession.class, LunchBuddySession.SINGLETON);
	}

	public void testGetUserLoggedIn_StartsAsNull() throws Exception {
		assertEquals(NullPerson.NULL, LunchBuddySession.SINGLETON.getUserLoggedIn());
	}

	public void testSetUserLoggedIN() throws Exception {
		MockPerson expectedLoggedInPerson = new MockPerson();
		LunchBuddySession.SINGLETON.setLoggedInUser(expectedLoggedInPerson);
		assertEquals(expectedLoggedInPerson, LunchBuddySession.SINGLETON.getUserLoggedIn());;
		
	}

}
