package com.lunchlunch.view.lunches;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

import com.lunchlunch.LunchBuddyApp;
import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.controller.ActivityStarter;
import com.lunchlunch.controller.CommandDispatcherProviderTestUtility;
import com.lunchlunch.controller.ShowBuddyFoundCommand;
import com.lunchlunch.controller.ShowLunchDetailCommand;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.lunch.MockLunch;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.view.MockMenuItem;
import com.lunchlunch.view.login.MockCommandDispatcher;
import com.lunchlunch.webcomm.findBuddy.FindBuddyHelperProviderTestUtility;
import com.lunchlunch.webcomm.findBuddy.MockFindBuddyHelper;
import com.lunchlunch.webcomm.person.PersonReceiver;

public class LunchListActivityTest extends
		ActivityUnitTestCase<LunchListActivity> {

	private MockCommandDispatcher commandDispatcher;
	private MockFindBuddyHelper findLunchBuddyHelper;

	public LunchListActivityTest() {
		super(LunchListActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		commandDispatcher = new MockCommandDispatcher();
		CommandDispatcherProviderTestUtility
				.setCommandDispatcherToProvide(commandDispatcher);
		findLunchBuddyHelper = new MockFindBuddyHelper();
		FindBuddyHelperProviderTestUtility
				.setFindBuddyHelperToProvide(findLunchBuddyHelper);

		setApplication(new LunchBuddyApp());
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				LunchListActivity.class);
		startActivity(intent, null, null);
	}

	@Override
	protected void tearDown() throws Exception {
		CommandDispatcherProviderTestUtility.resetCommandDispatcherProvider();
		FindBuddyHelperProviderTestUtility.resetFindBuddyHelperProvider();
		super.tearDown();
	}

	public void testImplementsCorrectInterfaces() throws Exception {
		assertEquals(LunchListFragment.Callbacks.class,
				LunchListActivity.class.getInterfaces()[0]);
		assertEquals(PersonReceiver.class,
				LunchListActivity.class.getInterfaces()[1]);
	}

	public void testOnOptionItemSelect() throws Exception {
		MockPerson loggedInPerson = new MockPerson();
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);
		LunchListActivity activity = getActivity();
		activity.onOptionsItemSelected(new MockMenuItem(
				com.lunchlunch.R.id.suggest_buddy_button));
		assertEquals(loggedInPerson,
				this.findLunchBuddyHelper
						.getBuddySeekerPassedToFindLunchBuddy());
		assertEquals(activity,
				this.findLunchBuddyHelper
						.getPersonReceiverPassedToFindLunchBuddy());

	}

	public void testOnOptionItemSelect_DoesNothingIfItIsNotTheSuggestBuddyButton()
			throws Exception {
		MockPerson loggedInPerson = new MockPerson();
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);
		LunchListActivity activity = getActivity();
		activity.onOptionsItemSelected(new MockMenuItem(
				com.lunchlunch.R.id.emailTextField));
		assertFalse(this.findLunchBuddyHelper.wasFindLunchBuddyCalled());

	}

	public void testOnItemSelect() throws Exception {
		MockLunch lunch = new MockLunch();
		getActivity().onItemSelect(lunch);

		ShowLunchDetailCommand command = LunchBuddyTestCase
				.assertIsOfTypeAndGet(ShowLunchDetailCommand.class,
						commandDispatcher.getLastCommandExecuted());

		assertEquals(lunch, command.getLunch());
		assertEquals(getActivity(), command.getLunchListActivity());
		assertEquals(ActivityStarter.SINGLETON, command.getActivityStarter());
	}

	public void testPersonReceivedWillFireShowBuddyFoundCommand()
			throws Exception {

		MockPerson person = new MockPerson();
		getActivity().personReceived(person);
		ShowBuddyFoundCommand command = LunchBuddyTestCase
				.assertIsOfTypeAndGet(ShowBuddyFoundCommand.class,
						commandDispatcher.getLastCommandExecuted());

		assertEquals(person, command.getLunchBuddyFound());
		assertEquals(getActivity(), command.getOriginatingActivity());
		assertEquals(ActivityStarter.SINGLETON, command.getActivityStarter());
	}
}
