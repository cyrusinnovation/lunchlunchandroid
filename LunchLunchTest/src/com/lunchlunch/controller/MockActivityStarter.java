package com.lunchlunch.controller;

import android.app.Activity;

public class MockActivityStarter implements ActivityStarterInterface {

	private Activity baseActivity;
	private Class<? extends Activity> activityClassToStart;

	@Override
	public void startActivity(Activity baseActivity,
			Class<? extends Activity> activityClassToStart) {
		this.baseActivity = baseActivity;
		this.activityClassToStart = activityClassToStart;

	}

	public Class<? extends Activity> getActivityClassToStart() {
		return activityClassToStart;
	}

	public Activity getBaseActivity() {
		return baseActivity;
	}

}
