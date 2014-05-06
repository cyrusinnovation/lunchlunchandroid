package com.lunchlunch.view.login;

import javax.inject.Singleton;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;

import com.lunchlunch.LunchBuddyApp;
import com.lunchlunch.LunchTestCase;
import com.lunchlunch.R;
import com.lunchlunch.controller.ActivityStarter;
import com.lunchlunch.controller.CommandDispatcherInterface;
import com.lunchlunch.controller.CommandDispatcherProvider;
import com.lunchlunch.controller.LoginCommand;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.view.DialogHandlerInterface;
import com.lunchlunch.view.DialogHandlerProvider;
import com.lunchlunch.view.MockDialogHandler;
import com.lunchlunch.webcomm.PersonReceiver;
import com.lunchlunch.webcomm.login.LoginHelperInterface;
import com.lunchlunch.webcomm.login.LoginHelperProvider;
import com.lunchlunch.webcomm.login.MockLoginHelper;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;

public class LoginTest extends ActivityUnitTestCase<Login> {

	private static MockCommandDispatcher commandDispatcher;

	private static MockLoginHelper loginHelper;

	private static MockDialogHandler dialogHandler;

	public LoginTest() {
		super(Login.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		LunchBuddyApp application = new LunchBuddyApp();
		application.onCreate();
		setApplication(application);
		loginHelper = new MockLoginHelper();
		commandDispatcher = new MockCommandDispatcher();
		dialogHandler = new MockDialogHandler();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				Login.class);
		startActivity(intent, null, null);

		ObjectGraph.create(new TestModule()).inject(getActivity());
	}

	@Override
	protected void tearDown() throws Exception {
		loginHelper = null;
		commandDispatcher = null;
		super.tearDown();
	}

	@Module(includes = { LoginHelperProvider.class,
			CommandDispatcherProvider.class, DialogHandlerProvider.class }, injects = LoginTest.class, overrides = true, library = true)
	static class TestModule {

		@Provides
		@Singleton
		LoginHelperInterface provideLoginHelper() {
			return loginHelper;
		}

		@Provides
		@Singleton
		CommandDispatcherInterface provideCommandDispatcher() {
			return commandDispatcher;
		}

		@Provides
		@Singleton
		DialogHandlerInterface provideDialogHandler() {
			return dialogHandler;
		}
	}

	public void testIsAnActivity() throws Exception {
		assertEquals(Activity.class, Login.class.getSuperclass());
	}

	public void testImplementsPersonReceiver() throws Exception {
		assertEquals(PersonReceiver.class, Login.class.getInterfaces()[0]);
	}

	public void testLoginClickedWillSendEmailFromEmailTextFieldToLoginProvider()
			throws Exception {

		Login activity = getActivity();
		EditText emailTextField = LunchTestCase.assertIsOfTypeAndGet(
				EditText.class, activity.findViewById(R.id.emailTextField));
		String textFieldContents = "thisisinthetextfield";
		emailTextField.setText(textFieldContents);

		Button loginButton = LunchTestCase.assertIsOfTypeAndGet(Button.class,
				activity.findViewById(R.id.loginButton));
		activity.loginClicked(loginButton);
		assertEquals(textFieldContents, loginHelper.getEmailPassedToLogin());
		assertEquals(activity, loginHelper.getPersonReceiverPassedIn());
	}

	public void testWhenAPersonIsReturnedFromTheProviderTheLoginCommandIsFired()
			throws Exception {
		Login activity = getActivity();
		MockPerson personToReturn = new MockPerson();
		activity.personReceived(personToReturn);
		LoginCommand loginCommand = LunchTestCase.assertIsOfTypeAndGet(
				LoginCommand.class, commandDispatcher.getLastCommandExecuted());
		assertEquals(activity, loginCommand.getBaseLoginActivity());
		assertEquals(personToReturn, loginCommand.getPersonToLogIn());
		assertEquals(LunchBuddySession.SINGLETON, loginCommand.getSession());
		assertEquals(ActivityStarter.SINGLETON,
				loginCommand.getActivityStarter());
	}

	public void testWhenANullPersonIsReturnedFromTheProviderNoCommandIsFired()
			throws Exception {
		Login activity = getActivity();
		activity.personReceived(NullPerson.NULL);
		assertNull(commandDispatcher.getLastCommandExecuted());
	}

	public void testWhenANullPersonIsReturnedFromTheProviderShowAnErrorMessage()
			throws Exception {
		Login activity = getActivity();
		activity.personReceived(NullPerson.NULL);
		assertEquals(activity, dialogHandler.getBaseContextForLastErrorDialog());
		assertEquals(activity.getString(R.string.login_error),
				dialogHandler.getErrorMessageForLastErrorDialog());
	}
}
