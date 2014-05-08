package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.webcomm.HttpClientBuilder;
import com.lunchlunch.webcomm.person.PersonParser;

public class LunchHelperProvider {

	public static final LunchHelperProvider SINGLETON = new LunchHelperProvider();
	private LunchHelperInterface lunchHelper;

	private LunchHelperProvider() {
		lunchHelper = new LunchHelper(HttpClientBuilder.SINGLETON,
				PersonParser.SINGLETON, LunchParser.SINGLETON);
	}

	public LunchHelperInterface provideLunchHelper() {

		return lunchHelper;
	}

}
