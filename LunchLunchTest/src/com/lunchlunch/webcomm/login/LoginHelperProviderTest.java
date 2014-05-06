package com.lunchlunch.webcomm.login;

import com.lunchlunch.LunchTestCase;
import com.lunchlunch.webcomm.HttpClientBuilder;
import com.lunchlunch.webcomm.person.PersonParser;

public class LoginHelperProviderTest extends LunchTestCase {

	public void testBuildLoginProvider() throws Exception {
		LoginHelper provider = assertIsOfTypeAndGet(LoginHelper.class,
				new LoginHelperProvider().provideLoginHelper());
		assertEquals(PersonParser.SINGLETON, provider.getPersonParser());
		assertEquals(HttpClientBuilder.SINGLETON,
				provider.getHttpClientBuilder());
	}

}
