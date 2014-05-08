package com.lunchlunch.view.login;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;

import com.lunchlunch.LunchBuddyApp;
import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.R;
import com.lunchlunch.controller.ActivityStarter;
import com.lunchlunch.controller.CommandDispatcherProviderTestUtility;
import com.lunchlunch.controller.LoginCommand;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.view.DialogHandlerProviderTestUtility;
import com.lunchlunch.view.MockDialogHandler;
import com.lunchlunch.webcomm.login.LoginHelperProviderTestUtility;
import com.lunchlunch.webcomm.login.MockLoginHelper;
import com.lunchlunch.webcomm.person.PersonReceiver;

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
		loginHelper = new MockLoginHelper();
		commandDispatcher = new MockCommandDispatcher();
		dialogHandler = new MockDialogHandler();

		LoginHelperProviderTestUtility.setLoginHelperToProvide(loginHelper);
		CommandDispatcherProviderTestUtility
				.setCommandDispatcherToProvide(commandDispatcher);
		DialogHandlerProviderTestUtility
				.setDialogHandlerToProvide(dialogHandler);
		setApplication(new LunchBuddyApp());
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				Login.class);
		startActivity(intent, null, null);

	}

	@Override
	protected void tearDown() throws Exception {
		loginHelper = null;
		commandDispatcher = null;
		dialogHandler = null;
		CommandDispatcherProviderTestUtility.resetCommandDispatcherProvider();
		DialogHandlerProviderTestUtility.resetDialogHandlerProvider();
		LoginHelperProviderTestUtility.resetLoginHelperProvider();
		super.tearDown();
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
		EditText emailTextField = LunchBuddyTestCase.assertIsOfTypeAndGet(
				EditText.class, activity.findViewById(R.id.emailTextField));
		String textFieldContents = "thisisinthetextfield";
		emailTextField.setText(textFieldContents);

		Button loginButton = LunchBuddyTestCase.assertIsOfTypeAndGet(Button.class,
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
		LoginCommand loginCommand = LunchBuddyTestCase.assertIsOfTypeAndGet(
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
