package com.lunchlunch.model.lunch;

import java.util.Date;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.person.NullPerson;

public class NullLunchTest extends LunchBuddyTestCase {

	public void testIsSingleton() throws Exception {
		assertEquals(0, NullLunch.class.getConstructors().length);
		assertIsOfTypeAndGet(NullLunch.class, NullLunch.NULL);
	}

	public void testGets() throws Exception {
		assertEquals(NullPerson.NULL, NullLunch.NULL.getPerson1());
		assertEquals(NullPerson.NULL, NullLunch.NULL.getPerson2());
		assertEquals(new Date(0), NullLunch.NULL.getDateTime());
	}
}
