package com.lunchlunch.webcomm.lunch;

import com.lunchlunch.LunchTestCase;
import com.lunchlunch.webcomm.HttpClientBuilder;
import com.lunchlunch.webcomm.person.PersonParser;

public class LunchHelperProviderTest extends LunchTestCase {

	public void testIsASingleton() throws Exception {
		assertEquals(0, LunchHelperProvider.class.getConstructors().length);
		assertIsOfTypeAndGet(LunchHelperProvider.class,
				LunchHelperProvider.SINGLETON);
	}

	public void testProvideLunchHelper() throws Exception {
		LunchHelper helper = assertIsOfTypeAndGet(LunchHelper.class,
				LunchHelperProvider.SINGLETON.provideLunchHelper());
		assertSame(helper, LunchHelperProvider.SINGLETON.provideLunchHelper());
		assertEquals(LunchParser.SINGLETON, helper.getLunchParser());
		assertEquals(PersonParser.SINGLETON, helper.getPersonParser());
		assertEquals(HttpClientBuilder.SINGLETON, helper.getHttpClientBuilder());
	}

}
