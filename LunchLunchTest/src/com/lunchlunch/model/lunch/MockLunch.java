package com.lunchlunch.model.lunch;

import java.util.Date;

import com.lunchlunch.model.person.PersonInterface;

public class MockLunch implements LunchInterface {

	@Override
	public PersonInterface getPerson1() {
		return null;
	}

	@Override
	public PersonInterface getPerson2() {
		return null;
	}

	@Override
	public Date getDateTime() {
		return null;
	}

}
