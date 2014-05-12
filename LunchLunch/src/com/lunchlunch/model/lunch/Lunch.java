package com.lunchlunch.model.lunch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Parcel;

import com.lunchlunch.LunchBuddyConstants;
import com.lunchlunch.model.person.PersonInterface;

public class Lunch implements LunchInterface {

	private final SimpleDateFormat lunchDateFormatter = new SimpleDateFormat(
			"M/d/yyyy 'at' h:mm a", Locale.getDefault());
	private PersonInterface person1;
	private PersonInterface person2;
	private Date dateTime;

	public Lunch(PersonInterface person1, PersonInterface person2, Date dateTime) {
		this.person1 = person1;
		this.person2 = person2;
		this.dateTime = dateTime;
	}

	@Override
	public PersonInterface getPerson1() {
		return person1;
	}

	@Override
	public PersonInterface getPerson2() {
		return person2;
	}

	@Override
	public Date getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
		return lunchDateFormatter.format(dateTime);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(person1, flags);
		dest.writeParcelable(person2, flags);
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				LunchBuddyConstants.JSON_DATE_FORMAT, Locale.getDefault());
		dest.writeString(dateFormatter.format(dateTime));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime * dateTime.hashCode() * person1.hashCode()
				* person2.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Lunch))
			return false;
		Lunch other = (Lunch) obj;
		return this.dateTime.equals(other.dateTime)
				&& this.person1.equals(other.person1)
				&& this.person2.equals(other.person2);
	}

}
