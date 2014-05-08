package com.lunchlunch.webcomm.login;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.webcomm.HttpClientBuilder;
import com.lunchlunch.webcomm.person.PersonParser;

public class LoginHelperProviderTest extends LunchBuddyTestCase {

	public void testIsASingleton() throws Exception {
		assertEquals(0, LoginHelperProvider.class.getConstructors().length);
		assertIsOfTypeAndGet(LoginHelperProvider.class,
				LoginHelperProvider.SINGLETON);
	}

	public void testBuildLoginHelper() throws Exception {
		LoginHelper provider = assertIsOfTypeAndGet(LoginHelper.class,
				LoginHelperProvider.SINGLETON.provideLoginHelper());
		assertEquals(PersonParser.SINGLETON, provider.getPersonParser());
		assertEquals(HttpClientBuilder.SINGLETON,
				provider.getHttpClientBuilder());
		assertSame(provider, LoginHelperProvider.SINGLETON.provideLoginHelper());
	}

}
