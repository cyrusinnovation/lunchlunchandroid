package com.lunchlunch.webcomm.login;

import com.lunchlunch.webcomm.person.PersonReceiver;

public interface LoginHelperInterface {

	public abstract void login(String email, PersonReceiver personReceiver);

}