package com.lunchlunch.model.lunch;

import java.util.Date;

import android.os.Parcel;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.person.Person;

public class LunchInterfaceTest extends LunchBuddyTestCase {

	public void testBuildUsingParcel_RoundTrip() throws Exception {
		Date dateTime = new Date(523523523);
		Person person1 = new Person("1", "firstname", "lastname", "email");
		Person person2 = new Person("2", "firsto", "lesto", "smemail");
		Lunch lunch = new Lunch(person1, person2, dateTime);

		Parcel parcel = Parcel.obtain();
		lunch.writeToParcel(parcel, 0);

		parcel.setDataPosition(0);

		Lunch parcelLunch = assertIsOfTypeAndGet(Lunch.class,
				LunchInterface.CREATOR.createFromParcel(parcel));
		assertEquals(person1, parcelLunch.getPerson1());
		assertEquals(person2, parcelLunch.getPerson2());
		assertEquals(dateTime, parcelLunch.getDateTime());

		parcel.recycle();
	}

	public void testBuildUsingParcels_MissingDataParcelsWillReturnNullLunch()
			throws Exception {
		Person person1 = new Person("1", "firstname", "lastname", "email");

		Parcel parcel = Parcel.obtain();
		parcel.writeParcelable(person1, 0);

		parcel.setDataPosition(0);

		assertEquals(NullLunch.NULL,
				LunchInterface.CREATOR.createFromParcel(parcel));

		parcel.recycle();
	}

	public void testBuildUsingParcels_BadDateFormatWillResultInNullLunch()
			throws Exception {
		Person person1 = new Person("1", "firstname", "lastname", "email");
		Person person2 = new Person("11", "firs2tname", "l2astname", "em42ail");

		Parcel parcel = Parcel.obtain();
		parcel.writeParcelable(person1, 0);
		parcel.writeParcelable(person2, 0);
		parcel.writeString("not a adate");

		parcel.setDataPosition(0);

		assertEquals(NullLunch.NULL,
				LunchInterface.CREATOR.createFromParcel(parcel));

		parcel.recycle();
	}
}
