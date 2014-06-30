package com.lunchlunch.model.lunch.location;

public class Location implements LocationInterface {

	private String name;
	private String address;
	private String zipCode;

	public Location(String name, String address, String zipCode) {
		this.name = name;
		this.address = address;
		this.zipCode = zipCode;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Location) {
			Location that = (Location) o;
			return that.address.equals(this.address)
					&& that.name.equals(this.name)
					&& that.zipCode.equals(this.zipCode);
		}
		return false;
	}
	@Override
	public int hashCode() {
		return 7 * this.address.hashCode() * name.hashCode() * zipCode.hashCode();
	}
}
