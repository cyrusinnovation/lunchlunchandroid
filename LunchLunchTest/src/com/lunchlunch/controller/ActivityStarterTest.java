package com.lunchlunch.controller;

import android.content.Intent;

import com.lunchlunch.LunchTestCase;

public class ActivityStarterTest extends LunchTestCase {

	public void testImplementsInterface() throws Exception {
		assertEquals(ActivityStarterInterface.class,
				ActivityStarter.class.getInterfaces()[0]);
	}

	public void testIsSingleton() throws Exception {
		assertEquals(0, ActivityStarter.class.getConstructors().length);
		assertIsOfTypeAndGet(ActivityStarter.class, ActivityStarter.SINGLETON);
	}

	public void testStartActivity() throws Exception {
		MockActivity baseActivity = new MockActivity();
		Class<MockActivity> targetActivityClass = MockActivity.class;
		ActivityStarter.SINGLETON.startActivity(baseActivity,
				targetActivityClass);
		Intent intent = assertIsOfTypeAndGet(Intent.class,
				baseActivity.getIntentToStart());
		assertEquals(baseActivity.getPackageName(), intent.getComponent()
				.getPackageName());
		assertEquals(targetActivityClass.getName(), intent.getComponent()
				.getClassName());
	}
}
