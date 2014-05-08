package com.lunchlunch.webcomm.login;

import com.lunchlunch.webcomm.HttpClientBuilder;
import com.lunchlunch.webcomm.person.PersonParser;

public class LoginHelperProvider {

	public static LoginHelperProvider SINGLETON = new LoginHelperProvider();
	private LoginHelperInterface loginHelper;

	private LoginHelperProvider() {
		loginHelper = new LoginHelper(HttpClientBuilder.SINGLETON,
				PersonParser.SINGLETON);
	}

	public LoginHelperInterface provideLoginHelper() {

		return loginHelper;
	}

}
