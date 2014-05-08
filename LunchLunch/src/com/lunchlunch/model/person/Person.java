package com.lunchlunch.model.person;

import android.os.Parcel;

public class Person implements PersonInterface {

	private String id;
	private String firstName;
	private String lastName;
	private String email;

	public Person(String id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;

		return this.email.equals(other.email)
				&& this.firstName.equals(other.firstName)
				&& this.id.equals(other.id)
				&& this.lastName.equals(other.lastName);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(id);
		dest.writeString(firstName);
		dest.writeString(lastName);
		dest.writeString(email);
	}

}
