package com.lunchlunch.controller;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.view.lunches.FindLunchBuddyActivity;

public class ShowBuddyFoundCommandTest extends LunchBuddyTestCase {

	public void testImplementsCommand() throws Exception {
		assertEquals(Command.class,
				ShowBuddyFoundCommand.class.getInterfaces()[0]);
	}

	public void testCanGetConstructorArguments() throws Exception {

		MockPerson lunchBuddyFound = new MockPerson();
		MockActivity lunchListActivity = new MockActivity();
		MockActivityStarter activityStarter = new MockActivityStarter();
		ShowBuddyFoundCommand showBuddyFoundCommand = new ShowBuddyFoundCommand(
				lunchBuddyFound, lunchListActivity, activityStarter);
		assertEquals(lunchBuddyFound,
				showBuddyFoundCommand.getLunchBuddyFound());
		assertEquals(lunchListActivity,
				showBuddyFoundCommand.getOriginatingActivity());
		assertEquals(activityStarter,
				showBuddyFoundCommand.getActivityStarter());
	}

	public void testExecute() throws Exception {
		MockPerson lunchBuddyFound = new MockPerson();
		MockActivity lunchListActivity = new MockActivity();
		MockActivityStarter activityStarter = new MockActivityStarter();
		ShowBuddyFoundCommand showBuddyFoundCommand = new ShowBuddyFoundCommand(
				lunchBuddyFound, lunchListActivity, activityStarter);

		showBuddyFoundCommand.execute();

		assertEquals(lunchListActivity, activityStarter.getBaseActivity());
		assertEquals(FindLunchBuddyActivity.class,
				activityStarter.getActivityClassToStart());
		assertEquals(
				lunchBuddyFound,
				activityStarter.getExtraInformationPassedToStart().get(
						FindLunchBuddyActivity.LUNCH_BUDDY_KEY));
	}
}
