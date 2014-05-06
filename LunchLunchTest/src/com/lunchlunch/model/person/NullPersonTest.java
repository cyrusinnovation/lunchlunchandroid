package com.lunchlunch.model.person;

import com.lunchlunch.LunchTestCase;

public class NullPersonTest extends LunchTestCase {
	
	public void testIsASingleton() throws Exception {
		assertEquals(0, NullPerson.class.getConstructors().length);
		assertEquals(NullPerson.class, NullPerson.NULL.getClass());
		assertSame(NullPerson.NULL, NullPerson.NULL);
	}

	public void testFieldsAreBlank() throws Exception {
		assertEquals("", NullPerson.NULL.getFirstName());
		assertEquals("", NullPerson.NULL.getLastName());
		assertEquals("", NullPerson.NULL.getEmail());
		assertEquals("", NullPerson.NULL.getId());
	}
}
