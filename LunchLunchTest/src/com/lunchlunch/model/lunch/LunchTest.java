package com.lunchlunch.model.lunch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;

import com.lunchlunch.LunchBuddyConstants;
import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.model.person.Person;
import com.lunchlunch.model.person.PersonInterface;

public class LunchTest extends LunchBuddyTestCase {

	public void testImplementsInterface() throws Exception {
		assertEquals(LunchInterface.class, Lunch.class.getInterfaces()[0]);
		assertEquals(Parcelable.class, LunchInterface.class.getInterfaces()[0]);
	}

	public void testConstructorAndGets() throws Exception {
		Date dateTime = new Date(523523523);
		MockPerson person1 = new MockPerson();
		MockPerson person2 = new MockPerson();
		Lunch lunch = new Lunch(person1, person2, dateTime);

		assertEquals(person1, lunch.getPerson1());
		assertEquals(person2, lunch.getPerson2());
		assertEquals(dateTime, lunch.getDateTime());
	}

	public void testWriteToParcel() throws Exception {
		Date dateTime = new Date(523523523);
		Person person1 = new Person("1", "firstname", "lastname", "email");
		Person person2 = new Person("2", "firsto", "lesto", "smemail");
		Lunch lunch = new Lunch(person1, person2, dateTime);

		Parcel parcel = Parcel.obtain();
		lunch.writeToParcel(parcel, 0);

		parcel.setDataPosition(0);

		assertEquals(person1,
				parcel.readParcelable(PersonInterface.class.getClassLoader()));
		assertEquals(person2,
				parcel.readParcelable(PersonInterface.class.getClassLoader()));

		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				LunchBuddyConstants.JSON_DATE_FORMAT, Locale.getDefault());
		assertEquals(dateFormatter.format(dateTime), parcel.readString());
		parcel.recycle();
	}

	
	public void testToString() throws Exception {
		SimpleDateFormat dateMaker = new SimpleDateFormat("MM/dd/yyyy HH:mm",
				Locale.getDefault());
		MockPerson person1 = new MockPerson();
		MockPerson person2 = new MockPerson();
		Lunch lunch1 = new Lunch(person1, person2,
				dateMaker.parse("05/19/2102 14:20"));
		assertEquals("5/19/2102 at 2:20 PM", lunch1.toString());

		Lunch lunch2 = new Lunch(person1, person2,
				dateMaker.parse("12/09/2009 11:45"));
		assertEquals("12/9/2009 at 11:45 AM", lunch2.toString());
	}
}
