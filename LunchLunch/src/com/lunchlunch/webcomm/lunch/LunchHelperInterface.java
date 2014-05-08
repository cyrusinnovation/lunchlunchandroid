package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.model.person.PersonInterface;

public interface LunchHelperInterface {

	public abstract void getLunches(PersonInterface person,
			LunchReceiver lunchReceiver);

}