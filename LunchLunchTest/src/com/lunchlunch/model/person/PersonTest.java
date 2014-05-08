package com.lunchlunch.model.person;

import android.os.Parcel;
import android.os.Parcelable;

import com.lunchlunch.LunchBuddyTestCase;

public class PersonTest extends LunchBuddyTestCase {

	public void testImplementsInterface() throws Exception {
		assertEquals(PersonInterface.class, Person.class.getInterfaces()[0]);
		assertEquals(Parcelable.class, PersonInterface.class.getInterfaces()[0]);
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

	public void testWriteToParcel() throws Exception {
		String email = "cbone@space.mutiny";
		String lastName = "Bonemeal";
		String firstName = "Crush";
		String id = "id";
		Person person = new Person(id, firstName, lastName, email);
		Parcel parcel = Parcel.obtain();
		person.writeToParcel(parcel, 0);

		parcel.setDataPosition(0);
		assertEquals(id, parcel.readString());
		assertEquals(firstName, parcel.readString());
		assertEquals(lastName, parcel.readString());
		assertEquals(email, parcel.readString());
		parcel.recycle();
	}

	
	public void testEqualsAndHashCode() throws Exception {
		String email = "cbone@space.mutiny";
		String lastName = "Bonemeal";
		String firstName = "Crush";
		String id = "id";
		Person person = new Person(id, firstName, lastName, email);
		Person equalPerson = new Person(id, firstName, lastName, email);
		Person differentIdPerson = new Person("@3123", firstName, lastName,
				email);
		Person differentFirstNamePerson = new Person(id, "Launch", lastName,
				email);
		Person differentLastNamePerson = new Person(id, firstName, "Deadlift",
				email);
		Person differentEmailPerson = new Person(id, firstName, lastName,
				"ghogogog@yahoo.com");

		checkEqualsAndHashCode(person, equalPerson, differentIdPerson,
				differentEmailPerson, differentFirstNamePerson,
				differentLastNamePerson, null, new Object());

	}

}
