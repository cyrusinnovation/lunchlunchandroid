package com.lunchlunch.webcomm.findBuddy;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.webcomm.HttpClientBuilder;
import com.lunchlunch.webcomm.person.PersonParser;

public class FindBuddyHelperProviderTest extends LunchBuddyTestCase {

	public void testIsASingleton() throws Exception {
		assertEquals(0, FindBuddyHelperProvider.class.getConstructors().length);
		assertIsOfTypeAndGet(FindBuddyHelperProvider.class,
				FindBuddyHelperProvider.SINGLETON);
	}

	public void testBuildFindBuddyHelper() throws Exception {
		FindBuddyHelper helper = assertIsOfTypeAndGet(FindBuddyHelper.class,
				FindBuddyHelperProvider.SINGLETON.provideFindBuddyHelper());
		assertEquals(PersonParser.SINGLETON, helper.getPersonParser());
		assertEquals(HttpClientBuilder.SINGLETON, helper.getHttpClientBuilder());
		assertSame(helper,
				FindBuddyHelperProvider.SINGLETON.provideFindBuddyHelper());
	}

}
