package com.lunchlunch.controller;

import android.app.Activity;

import com.lunchlunch.model.SessionInterface;
import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.view.lunches.LunchListActivity;

public class LoginCommand implements Command {

	private Activity loginActivity;
	private PersonInterface personLoggedIn;
	private ActivityStarterInterface activityStarter;
	private SessionInterface session;

	public LoginCommand(PersonInterface personLoggedIn,
			SessionInterface session, Activity loginActivity,
			ActivityStarterInterface activityStarter) {
		this.personLoggedIn = personLoggedIn;
		this.session = session;
		this.loginActivity = loginActivity;
		this.activityStarter = activityStarter;
	}

	@Override
	public void execute() {
		session.setLoggedInUser(personLoggedIn);
		activityStarter.startActivity(loginActivity, LunchListActivity.class);
	}

	public PersonInterface getPersonToLogIn() {
		return personLoggedIn;
	}

	public SessionInterface getSession() {
		return session;
	}

	public Activity getBaseLoginActivity() {
		return loginActivity;
	}

	public ActivityStarterInterface getActivityStarter() {
		return activityStarter;
	}

}
