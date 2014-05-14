package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.webcomm.HttpClientBuilder;

public class LunchCreatorProviderTest extends LunchBuddyTestCase {

	public void testIsASingleton() throws Exception {
		assertEquals(0, LunchCreatorProvider.class.getConstructors().length);
		assertIsOfTypeAndGet(LunchCreatorProvider.class,
				LunchCreatorProvider.SINGLETON);
	}

	public void testProvideLunchCreator() throws Exception {
		LunchCreator creator = assertIsOfTypeAndGet(LunchCreator.class,
				LunchCreatorProvider.SINGLETON.provideLunchCreator());
		assertSame(creator,
				LunchCreatorProvider.SINGLETON.provideLunchCreator());
		assertEquals(LunchParser.SINGLETON, creator.getLunchParser());
		assertEquals(HttpClientBuilder.SINGLETON,
				creator.getHttpClientBuilder());
	}
}
