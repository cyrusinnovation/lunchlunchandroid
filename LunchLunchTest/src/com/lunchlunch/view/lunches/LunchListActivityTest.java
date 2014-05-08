package com.lunchlunch.view.lunches;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

import com.lunchlunch.LunchBuddyApp;
import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.controller.ActivityStarter;
import com.lunchlunch.controller.CommandDispatcherProviderTestUtility;
import com.lunchlunch.controller.ShowLunchDetailCommand;
import com.lunchlunch.model.lunch.MockLunch;
import com.lunchlunch.view.login.MockCommandDispatcher;

public class LunchListActivityTest extends
		ActivityUnitTestCase<LunchListActivity> {

	private MockCommandDispatcher commandDispatcher;

	public LunchListActivityTest() {
		super(LunchListActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		commandDispatcher = new MockCommandDispatcher();
		CommandDispatcherProviderTestUtility
				.setCommandDispatcherToProvide(commandDispatcher);
		setApplication(new LunchBuddyApp());
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				LunchListActivity.class);
		startActivity(intent, null, null);
	}

	@Override
	protected void tearDown() throws Exception {
		CommandDispatcherProviderTestUtility.resetCommandDispatcherProvider();
		super.tearDown();
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

}
