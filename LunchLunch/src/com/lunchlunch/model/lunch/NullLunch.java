package com.lunchlunch.model.lunch;

import java.util.Date;

import android.os.Parcel;

import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.model.person.PersonInterface;

public class NullLunch implements LunchInterface {

	public static final NullLunch NULL = new NullLunch();

	private NullLunch() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}

	@Override
	public PersonInterface getPerson1() {
		return NullPerson.NULL;
	}

	@Override
	public PersonInterface getPerson2() {
		return NullPerson.NULL;
	}

	@Override
	public Date getDateTime() {
		return new Date(0);
	}

}
