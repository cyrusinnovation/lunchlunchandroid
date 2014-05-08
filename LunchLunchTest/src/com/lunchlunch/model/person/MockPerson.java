package com.lunchlunch.model.person;

import android.os.Parcel;

public class MockPerson implements PersonInterface {

	@Override
	public String getFirstName() {
		return null;
	}

	@Override
	public String getLastName() {
		return null;
	}

	@Override
	public String getEmail() {
		return null;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}

}
