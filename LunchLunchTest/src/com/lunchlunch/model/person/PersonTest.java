package com.lunchlunch.model.person;

import com.lunchlunch.LunchTestCase;

public class PersonTest extends LunchTestCase {

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

	public void testEqualsAndHashCode() throws Exception {
		String email = "cbone@space.mutiny";
		String lastName = "Bonemeal";
		String firstName = "Crush";
		String id = "id";
		Person person = new Person(id, firstName, lastName, email);
		Person equalPerson = new Person(id, firstName, lastName, email);
		Person differentIdPerson = new Person("@3123", firstName, lastName, email);
		Person differentFirstNamePerson = new Person(id, "Launch", lastName,
				email);
		Person differentLastNamePerson = new Person(id, firstName, "Deadlift",
				email);
		Person differentEmailPerson = new Person(id, firstName, lastName, "ghogogog@yahoo.com");

		checkEqualsAndHashCode(person, equalPerson, differentIdPerson,
				differentEmailPerson, differentFirstNamePerson,
				differentLastNamePerson, null, new Object());

	}

}
