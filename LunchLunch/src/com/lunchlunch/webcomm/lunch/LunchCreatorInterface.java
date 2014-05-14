package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.model.lunch.LunchInterface;

public interface LunchCreatorInterface {

	public abstract void createLunch(LunchInterface lunch,
			LunchCreationHandler lunchCreationHandler);

}