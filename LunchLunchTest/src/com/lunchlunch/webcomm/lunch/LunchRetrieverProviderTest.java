package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.webcomm.HttpClientBuilder;
import com.lunchlunch.webcomm.person.PersonParser;

public class LunchRetrieverProviderTest extends LunchBuddyTestCase {

	public void testIsASingleton() throws Exception {
		assertEquals(0, LunchRetrieverProvider.class.getConstructors().length);
		assertIsOfTypeAndGet(LunchRetrieverProvider.class,
				LunchRetrieverProvider.SINGLETON);
	}

	public void testProvideLunchRetriever() throws Exception {
			LunchRetriever helper = assertIsOfTypeAndGet(LunchRetriever.class,
					LunchRetrieverProvider.SINGLETON.provideLunchRetriever());
			assertSame(helper, LunchRetrieverProvider.SINGLETON.provideLunchRetriever());
			assertEquals(LunchParser.SINGLETON, helper.getLunchParser());
			assertEquals(PersonParser.SINGLETON, helper.getPersonParser());
			assertEquals(HttpClientBuilder.SINGLETON, helper.getHttpClientBuilder());
		}

}
