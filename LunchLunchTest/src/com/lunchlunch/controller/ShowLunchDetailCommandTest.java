package com.lunchlunch.controller;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.lunch.MockLunch;
import com.lunchlunch.view.lunches.LunchDetailActivity;

public class ShowLunchDetailCommandTest extends LunchBuddyTestCase {
	public void testImplementsCommand() throws Exception {
		assertEquals(Command.class,
				ShowLunchDetailCommand.class.getInterfaces()[0]);
	}

	public void testCanGetConstructorArgs() throws Exception {
		MockLunch lunch = new MockLunch();
		MockActivity lunchListActivity = new MockActivity();
		MockActivityStarter activityStarter = new MockActivityStarter();
		ShowLunchDetailCommand showLunchDetailCommand = new ShowLunchDetailCommand(
				lunch, lunchListActivity, activityStarter);
		assertEquals(lunch, showLunchDetailCommand.getLunch());
		assertEquals(lunchListActivity,
				showLunchDetailCommand.getLunchListActivity());
		assertEquals(activityStarter,
				showLunchDetailCommand.getActivityStarter());
	}

	public void testExecute() throws Exception {
		MockLunch lunch = new MockLunch();
		MockActivity lunchListActivity = new MockActivity();
		MockActivityStarter activityStarter = new MockActivityStarter();
		ShowLunchDetailCommand showLunchDetailCommand = new ShowLunchDetailCommand(
				lunch, lunchListActivity, activityStarter);

		showLunchDetailCommand.execute();

		assertEquals(lunchListActivity, activityStarter.getBaseActivity());
		assertEquals(LunchDetailActivity.class,
				activityStarter.getActivityClassToStart());
		assertEquals(lunch, activityStarter.getExtraInformationPassedToStart()
				.get("lunch"));
	}
}
