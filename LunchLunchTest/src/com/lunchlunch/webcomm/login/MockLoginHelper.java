package com.lunchlunch.webcomm.login;

import com.lunchlunch.webcomm.person.PersonReceiver;

public class MockLoginHelper implements LoginHelperInterface {

	private String emailPassedToLogin;
	private PersonReceiver personReceiverPassedIn;

	@Override
	public void login(String email, PersonReceiver personReceiver) {
		this.emailPassedToLogin = email;
		this.personReceiverPassedIn = personReceiver;
	}

	public PersonReceiver getPersonReceiverPassedIn() {
		return personReceiverPassedIn;
	}

	public String getEmailPassedToLogin() {
		return emailPassedToLogin;
	}
}
