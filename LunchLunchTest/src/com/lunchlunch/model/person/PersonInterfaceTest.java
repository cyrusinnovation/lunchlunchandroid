package com.lunchlunch.model.person;

import com.lunchlunch.LunchBuddyTestCase;

import android.os.Parcel;

public class PersonInterfaceTest extends LunchBuddyTestCase {

	public void testBuildUsingParcel_RoundTrip() throws Exception {
		String email = "cbone@space.mutiny";
		String lastName = "Bonemeal";
		String firstName = "Crush";
		String id = "id";
		Person person = new Person(id, firstName, lastName, email);
		Parcel parcel = Parcel.obtain();
		person.writeToParcel(parcel, 0);

		parcel.setDataPosition(0);

		Person parcelPerson = assertIsOfTypeAndGet(Person.class,
				PersonInterface.CREATOR.createFromParcel(parcel));
		assertEquals(email, parcelPerson.getEmail());
		assertEquals(lastName, parcelPerson.getLastName());
		assertEquals(firstName, parcelPerson.getFirstName());
		assertEquals(id, parcelPerson.getId());

		parcel.recycle();
	}

	public void testBuildUsingParcelThatIsMissingThingsWillReturnNullPerson()
			throws Exception {

		Parcel parcel = Parcel.obtain();
		parcel.writeString("IhaveJustanId");

		parcel.setDataPosition(0);

		assertEquals(NullPerson.NULL,
				PersonInterface.CREATOR.createFromParcel(parcel));

		parcel.recycle();
	}
}
