package com.lunchlunch.model.lunch;

import java.util.Date;

import com.lunchlunch.model.person.PersonInterface;

public interface LunchInterface {

	public PersonInterface getPerson1();

	public PersonInterface getPerson2();

	public Date getDateTime();
}
