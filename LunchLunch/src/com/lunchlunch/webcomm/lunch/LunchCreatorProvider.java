package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.webcomm.HttpClientBuilder;

public class LunchCreatorProvider {

	public static final LunchCreatorProvider SINGLETON = new LunchCreatorProvider();
	private LunchCreatorInterface lunchCreator;

	private LunchCreatorProvider() {
		lunchCreator = new LunchCreator(HttpClientBuilder.SINGLETON,
				LunchParser.SINGLETON);
	}

	public LunchCreatorInterface provideLunchCreator() {
		return lunchCreator;
	}
}
