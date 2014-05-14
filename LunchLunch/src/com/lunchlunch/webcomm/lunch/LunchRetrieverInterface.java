package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.model.person.PersonInterface;

public interface LunchRetrieverInterface {

	public abstract void getLunches(PersonInterface person,
			LunchReceiver lunchReceiver);

}