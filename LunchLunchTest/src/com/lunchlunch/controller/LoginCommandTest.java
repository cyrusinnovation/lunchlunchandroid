package com.lunchlunch.controller;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.controller.Command;
import com.lunchlunch.model.MockSession;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.view.login.LoginActivity;
import com.lunchlunch.view.lunches.LunchListActivity;

public class LoginCommandTest extends LunchBuddyTestCase {

	public void testIsACommand() throws Exception {
		assertEquals(Command.class, LoginCommand.class.getInterfaces()[0]);
	}

	public void testCanGetConstructorArguments() throws Exception {
		MockPerson personLoggedIn = new MockPerson();
		MockSession session = new MockSession();
		LoginActivity loginActivity = new LoginActivity();
		MockActivityStarter activityStarter = new MockActivityStarter();
		LoginCommand loginCommand = new LoginCommand(personLoggedIn, session,
				loginActivity, activityStarter);
		assertEquals(personLoggedIn, loginCommand.getPersonToLogIn());
		assertEquals(session, loginCommand.getSession());
		assertEquals(loginActivity, loginCommand.getBaseLoginActivity());
		assertEquals(activityStarter, loginCommand.getActivityStarter());
	}

	public void testExecute() throws Exception {
		MockPerson personLoggedIn = new MockPerson();
		MockSession session = new MockSession();
		LoginActivity loginActivity = new LoginActivity();
		MockActivityStarter activityStarter = new MockActivityStarter();
		LoginCommand loginCommand = new LoginCommand(personLoggedIn, session,
				loginActivity, activityStarter);

		loginCommand.execute();

		assertEquals(personLoggedIn, session.getUserLoggedIn());

		assertEquals(loginActivity, activityStarter.getBaseActivity());
		assertEquals(LunchListActivity.class,
				activityStarter.getActivityClassToStart());
	}

}
