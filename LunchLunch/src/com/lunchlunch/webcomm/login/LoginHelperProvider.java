package com.lunchlunch.webcomm.login;

import javax.inject.Singleton;

import com.lunchlunch.view.login.Login;
import com.lunchlunch.webcomm.HttpClientBuilder;
import com.lunchlunch.webcomm.person.PersonParser;

import dagger.Module;
import dagger.Provides;

@Module(injects = Login.class, complete = false)
public class LoginHelperProvider {

	@Provides
	@Singleton
	LoginHelperInterface provideLoginHelper() {
		return new LoginHelper(HttpClientBuilder.SINGLETON,
				PersonParser.SINGLETON);
	}

}
