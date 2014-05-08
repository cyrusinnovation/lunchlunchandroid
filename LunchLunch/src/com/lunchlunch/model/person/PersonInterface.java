package com.lunchlunch.model.person;

import android.os.Parcel;
import android.os.Parcelable;

public interface PersonInterface extends Parcelable {

	String getFirstName();

	String getLastName();

	String getEmail();

	String getId();

	public static final Parcelable.Creator<PersonInterface> CREATOR = new Parcelable.Creator<PersonInterface>() {

		public PersonInterface createFromParcel(Parcel in) {
			String id = in.readString();
			String firstName = in.readString();
			String lastName = in.readString();
			String email = in.readString();
			if (id != null && firstName != null && lastName != null
					&& email != null) {
				return new Person(id, firstName, lastName, email);
			} else {
				return NullPerson.NULL;
			}
		}

		public PersonInterface[] newArray(int size) {
			return new PersonInterface[size];
		}
	};
}
