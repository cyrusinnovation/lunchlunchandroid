package com.lunchlunch.model.person;

import android.os.Parcel;

public class NullPerson implements PersonInterface {

	public static final NullPerson NULL = new NullPerson();

	private NullPerson() {
	}

	@Override
	public String getFirstName() {
		return "";
	}

	@Override
	public String getLastName() {
		return "";
	}

	@Override
	public String getEmail() {
		return "";
	}

	@Override
	public String getId() {
		return "";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}

}
