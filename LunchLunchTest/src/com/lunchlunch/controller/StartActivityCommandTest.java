package com.lunchlunch.controller;

import com.lunchlunch.LunchTestCase;

public class StartActivityCommandTest extends LunchTestCase {

	public void testIsACommand() throws Exception {
		assertEquals(Command.class,
				StartActivityCommand.class.getInterfaces()[0]);
	}

	public void testConstructorGets() throws Exception {
		MockActivity baseActivity = new MockActivity();
		Class<MockActivity> targetActivityClass = MockActivity.class;
		MockActivityStarter activityStarter = new MockActivityStarter();
		StartActivityCommand startActivityCommand = new StartActivityCommand(
				baseActivity, targetActivityClass, activityStarter);
		assertEquals(baseActivity, startActivityCommand.getBaseActivity());
		assertEquals(targetActivityClass,
				startActivityCommand.getTargetActivity());
		assertEquals(activityStarter, startActivityCommand.getActivityStarter());
	}

	public void testExecute() throws Exception {
		MockActivity baseActivity = new MockActivity();
		Class<MockActivity> targetActivityClass = MockActivity.class;
		MockActivityStarter activityStarter = new MockActivityStarter();
		StartActivityCommand startActivityCommand = new StartActivityCommand(
				baseActivity, targetActivityClass, activityStarter);
		startActivityCommand.execute();
		assertEquals(baseActivity, activityStarter.getBaseActivity());
		assertEquals(targetActivityClass,
				activityStarter.getActivityClassToStart());
	}

}
