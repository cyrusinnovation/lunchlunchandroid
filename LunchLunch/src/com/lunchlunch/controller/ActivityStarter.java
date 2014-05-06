package com.lunchlunch.controller;

import android.app.Activity;
import android.content.Intent;

public class ActivityStarter implements ActivityStarterInterface {

	public static ActivityStarter SINGLETON = new ActivityStarter();

	private ActivityStarter() {
	}

	@Override
	public void startActivity(Activity baseActivity,
			Class<? extends Activity> activityClassToStart) {
		Intent intent = new Intent(baseActivity, activityClassToStart);
		baseActivity.startActivity(intent);
	}

}
