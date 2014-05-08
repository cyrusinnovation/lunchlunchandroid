package com.lunchlunch.model.lunch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;

import com.lunchlunch.LunchBuddyConstants;
import com.lunchlunch.model.person.PersonInterface;

public interface LunchInterface extends Parcelable {

	public PersonInterface getPerson1();

	public PersonInterface getPerson2();

	public Date getDateTime();

	public static final Parcelable.Creator<LunchInterface> CREATOR = new Parcelable.Creator<LunchInterface>() {

		public LunchInterface createFromParcel(Parcel in) {
			PersonInterface person1 = in.readParcelable(PersonInterface.class
					.getClassLoader());
			PersonInterface person2 = in.readParcelable(PersonInterface.class
					.getClassLoader());
			String dateTimeString = in.readString();
			if (personWasFound(person1) && personWasFound(person2)
					&& dateTimeString != null) {
				try {
					SimpleDateFormat dateMaker = new SimpleDateFormat(
							LunchBuddyConstants.JSON_DATE_FORMAT,
							Locale.getDefault());
					Date dateTime = dateMaker.parse(dateTimeString);
					return new Lunch(person1, person2, dateTime);
				} catch (ParseException e) {
					return NullLunch.NULL;
				}
			}
			return NullLunch.NULL;

		}

		private boolean personWasFound(PersonInterface person1) {
			return person1 != null;
		}

		public LunchInterface[] newArray(int size) {
			return new LunchInterface[size];
		}
	};
}
