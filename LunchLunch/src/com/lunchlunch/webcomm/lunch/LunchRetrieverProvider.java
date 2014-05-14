package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.webcomm.HttpClientBuilder;
import com.lunchlunch.webcomm.person.PersonParser;

public class LunchRetrieverProvider {

	public static final LunchRetrieverProvider SINGLETON = new LunchRetrieverProvider();
	private LunchRetrieverInterface lunchHelper;

	private LunchRetrieverProvider() {
		lunchHelper = new LunchRetriever(HttpClientBuilder.SINGLETON,
				PersonParser.SINGLETON, LunchParser.SINGLETON);
	}

	public LunchRetrieverInterface provideLunchRetriever() {

		return lunchHelper;
	}

}
