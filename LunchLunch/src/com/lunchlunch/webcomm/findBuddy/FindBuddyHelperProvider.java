package com.lunchlunch.webcomm.findBuddy;

import com.lunchlunch.webcomm.HttpClientBuilder;
import com.lunchlunch.webcomm.person.PersonParser;

public class FindBuddyHelperProvider {

	public static final FindBuddyHelperProvider SINGLETON = new FindBuddyHelperProvider();
	private FindBuddyHelperInterface findBuddyHelper;

	private FindBuddyHelperProvider() {
		findBuddyHelper = new FindBuddyHelper(HttpClientBuilder.SINGLETON,
				PersonParser.SINGLETON);
	}

	public FindBuddyHelperInterface provideFindBuddyHelper() {
		return findBuddyHelper;
	}

}
