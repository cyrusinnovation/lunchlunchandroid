package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.model.lunch.LunchInterface;

public class MockLunchCreator implements LunchCreatorInterface {

	private LunchInterface lunchForCreate;
	private LunchCreationHandler lunchCreationHandlerForCreate;

	@Override
	public void createLunch(LunchInterface lunch,
			LunchCreationHandler lunchCreationHandler) {
		this.lunchForCreate = lunch;
		this.lunchCreationHandlerForCreate = lunchCreationHandler;

	}

	public LunchCreationHandler getLunchCreationHandlerForCreate() {
		return lunchCreationHandlerForCreate;
	}

	public LunchInterface getLunchForCreate() {
		return lunchForCreate;
	}
}
