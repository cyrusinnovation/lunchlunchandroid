package com.lunchlunch.model.lunch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
}