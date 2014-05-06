package com.lunchlunch.model.person;

import android.test.AndroidTestCase;

public class PersonTest extends AndroidTestCase {

	public void testImplementsInterface() throws Exception {
		assertEquals(PersonInterface.class, Person.class.getInterfaces()[0]);
	}

	public void testConstructorAndGets() throws Exception {
		String email = "cbone@space.mutiny";
		String lastName = "Bonemeal";
		String firstName = "Crush";
		String id = "id";
		Person person = new Person(id, firstName, lastName, email);
		assertEquals(email, person.getEmail());
		assertEquals(lastName, person.getLastName());
		assertEquals(firstName, person.getFirstName());
		assertEquals(id, person.getId());
	}
}
